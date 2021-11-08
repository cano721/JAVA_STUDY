package cindya.bj11279_최대힙;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 우선순위큐 기준이 역순이 되도록 생성
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            int x = Integer.parseInt(br.readLine());
            if(x > 0) // x가 자연수이면 우선순위큐에 삽입
                pq.offer(x);
            else if(!pq.isEmpty()) // x가 0이고 우선순위큐가 비어있지 않으면 맨 앞의 값 출력
                bw.write(pq.poll() + "\n");
            else // x가 0이고 우선순위큐가 비어있으면 0 출력
                bw.write("0\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
