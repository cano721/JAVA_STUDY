package 우선순위큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj13975_파일합치기3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스

        for(int t=0; t<T; t++) {
            PriorityQueue<Long> queue = new PriorityQueue<>();
            int K = Integer.parseInt(br.readLine()); // 장의 수
            st = new StringTokenizer(br.readLine());

            for(int k=0; k<K; k++) {
                queue.add(Long.parseLong(st.nextToken()));
            }

            long sum = 0;
            while(queue.size() > 1) {
                long a = queue.poll();
                long b = queue.poll();

                sum += (a+b);
                queue.add(a+b);
            }

            System.out.println(sum);
        }

    }
}
