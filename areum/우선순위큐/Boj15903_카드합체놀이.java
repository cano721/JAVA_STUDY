package 우선순위큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj15903_카드합체놀이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 카드의 개수
        int M = Integer.parseInt(st.nextToken()); // 카드 합체 횟수

        PriorityQueue<Long> queue = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<N; i++) {
            queue.add(Long.parseLong(st.nextToken()));
        }

        for(int i=0; i<M; i++) {
            // 가장 작은 카드 2개 뽑기
            Long a = queue.poll();
            Long b = queue.poll();

            // a와 b를 합으로 변경하는 것이기 때문에 두 번 넣어줌
            queue.add(a+b);
            queue.add(a+b);
        }

        long sum = 0;
        while(!queue.isEmpty()) {
            sum += queue.poll();
        }

        System.out.print(sum);
    }
}
