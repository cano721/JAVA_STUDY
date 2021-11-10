package cindya.bj2075_N번째큰수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 큰 수가 앞으로 오도록 우선순위 큐 생성
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int n = Integer.parseInt(br.readLine());

        // 우선순위 큐에 모든 수 넣기
        for(int i = 0; i < n; i++){
            for(String s : br.readLine().split(" ")){
                pq.offer(Integer.parseInt(s));
            }
        }

        br.close();

        // n - 1번째 큰 수 까지 pq에서 빼기
        for(int i = 0; i < n - 1; i++){
            pq.poll();
        }
        // n번째 큰 수 출력
        System.out.println(pq.poll());
    }
}
