package 우선순위큐;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj19638_센티와마법의뿅망치 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 거인 나라 인구 수
        int H = Integer.parseInt(st.nextToken()); // 센티의 키
        int T = Integer.parseInt(st.nextToken()); // 뿅망치 횟수 제한

        // 거인들 키 우선순위 큐에 넣기
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(Collections.reverseOrder());
        for(int n=0; n<N; n++){
            int tall = Integer.parseInt(br.readLine());
            queue.add(tall);
        }

        int cnt = 0;
        while(T-- > 0) {
            // 가장 큰 거인의 키가 센티의 키보다 작거나 1이면
            if(queue.peek() == 1 || queue.peek() < H) break;

            cnt++;
            int tall = queue.poll();
            queue.add(tall/2);
        }

        if(queue.peek() < H) { // 센티가 성공했다면
            System.out.println("YES");
            System.out.println(cnt);
        } else { // 실패했다면
            System.out.println("NO");
            System.out.println(queue.peek());
        }
    }
}
