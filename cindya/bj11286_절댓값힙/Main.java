package cindya.bj11286_절댓값힙;

import java.io.*;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // 절댓값 비교 후 절댓값이 같으면 작은 값이 맨 앞으로 오도록 우선순위 큐 생성
        PriorityQueue<Integer> pq = new PriorityQueue<>((Integer n1,Integer n2) -> Math.abs(n1) - Math.abs(n2) == 0 ? n1 - n2 : Math.abs(n1) - Math.abs(n2));
        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            int x = Integer.parseInt(br.readLine());

            if(x != 0) // x가 0이 아닌 경우 우선순위 큐에 삽입
                pq.offer(x);
            else if(!pq.isEmpty()) // x가 0이고 우선순위 큐가 비어있지 않은 경우 맨 앞의 값 출력
                bw.write(pq.poll() + "\n");
            else // x가 0이고 우선순위 큐가 비어있는 경우 0 출력
                bw.write("0\n");
        }

        bw.flush();

        br.close();
        bw.close();
    }
}
