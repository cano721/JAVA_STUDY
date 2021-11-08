package cindya.bj1927_최소힙;

import java.io.*;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());

        // n번 루프
        for(int i = 0; i < n; i++){
            int x = Integer.parseInt(br.readLine());
            if(x > 0) // x가 0 이사이면 우선순위큐에 x 넣기
                pq.offer(x);
            else if(!pq.isEmpty()) // 0이고 pq가 비어있지 않으면 맨 앞 숫자를 꺼내 출력
                bw.write(pq.poll() + "\n");
            else // 0이고 pq가 비어있으면 0 출력
                bw.write("0\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
