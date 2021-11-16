package cindya.bj15961_회전초밥;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken(" ")), d = Integer.parseInt(st.nextToken(" ")),
                k = Integer.parseInt(st.nextToken(" ")), c = Integer.parseInt(st.nextToken());
        int[] belt = new int[n];
        Map<Integer, Integer> check = new HashMap<>();
        int end = k, max = 0;

        // belt 초기화 +  k개는 개수 체크하는 맵에 저장
        for(int i = 0; i < n; i++){
            belt[i] = Integer.parseInt(br.readLine());
            if(i < k){
                check.put(belt[i], check.getOrDefault(belt[i], 0) + 1);
            }
        }
        br.close();

        // n번 루프
        for(int start = 0; start < n; start++){
            // check에 쿠폰번호가 포함되어 있다면 1을 빼고 현재 초밥 개수와 max 중 많은 것 선택
            max = Math.max(max, check.size() - (check.containsKey(c) ? 1 : 0));
            // 다음 초밥 check에 삽입
            check.put(belt[end], check.getOrDefault(belt[end], 0) + 1);
            // check에서 맨 앞의 초밥 빼기
            if(check.get(belt[start]) > 1) // 같은 초밥 개수가 1 이상이면
                check.replace(belt[start], check.get(belt[start]) - 1); // 개수에서 1을 뺌
            else // 1이면
                check.remove(belt[start]); // check에서 해당 초밥 삭제
            end = ++end % n; // end를 다음 인덱스로 변경
        }
        // 가장 많은 초밥 종류 수  + 무료 초밥
        System.out.println(max + 1);
    }
}