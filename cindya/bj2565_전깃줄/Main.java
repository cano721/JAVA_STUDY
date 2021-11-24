package cindya.bj2565_전깃줄;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Integer> aTob = new HashMap<>();
        List<Integer> keys, answer = new ArrayList<>();
        int n = Integer.parseInt(br.readLine());

        // map에 <a, b>로 저장
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken(" ")), b = Integer.parseInt(st.nextToken());
            aTob.put(a, b);
        }
        br.close();
        keys = new ArrayList<>(aTob.keySet());
        Collections.sort(keys); // key(a) 정렬
        answer.add(aTob.get(keys.get(0))); // answer에 a1과 연결된 b 저장
        // 전깃줄 수만큼 루프
        for(int key : keys){
            int j = answer.size() - 1;
            for(; j >= 0 && aTob.get(key) < answer.get(j); j--); // j를 key와 연결된 b보다 작을 때까지 감소
            j++; // j보다 큰 값 중 가장 작은 값과 바꿔야하므로 j를 한 번 증가
            if(answer.size() == j) // j가 answer의 크기와 같으면
                answer.add(aTob.get(key)); // 맨 뒤에 추가
            else // 중간에 있으면
                answer.set(j, aTob.get(key)); // j번째 b를 key번째 b로 교체
        }
        System.out.println(n - answer.size()); // answer은 연결된 전깃줄의 수이므로 전체 전깃줄 수에서 연결된 전깃줄 수를 빼서 출력
    }
}
