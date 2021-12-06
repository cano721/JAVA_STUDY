package cindya.bj1495_기타리스트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken(" ")), s = Integer.parseInt(st.nextToken(" ")), m = Integer.parseInt(st.nextToken());
        int[] v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Set<Integer> set = new HashSet<>();

        br.close();
        set.add(s); // 시작 볼륨 삽입

        for(int i = 0; i < n; i++){
            Set<Integer> nextSet = new HashSet<>();
            for(int org : set){ // set의 원소로 루프를 돌면서
                if(org + v[i] <= m) // 합이 m 이하라면 nextSet에 삽입
                    nextSet.add(org + v[i]);
                if(org - v[i] >= 0) // 차가 0 이상이라면 nextSet에 삽입
                    nextSet.add(org - v[i]);
            }
            set = nextSet; // nextSet을 set으로 변경
            if(set.isEmpty()) break; // set이 비었다면 중지
        }
        int answer;
        if(set.isEmpty()) answer = -1; // set이 비었다면 볼륨 조절이 불가능해진 것이므로 -1 출력
        else answer = set.stream().max(Comparator.comparing(i -> i)).orElseThrow(); // set에서 가장 큰 값 출력
        System.out.println(answer);
    }
}
