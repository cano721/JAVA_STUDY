package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
2
4
40 30 30 50
15
1 21 3 4 5 35 5 4 3 5 98 21 14 17 32


300
826

*/
public class Boj13975_파일합치기3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine()); // 테스트 데이터 수
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            PriorityQueue<Long> pq = new PriorityQueue<>();
            int k = Integer.parseInt(br.readLine()); // 장의 수
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < k; j++) {
                pq.add(Long.parseLong(st.nextToken()));
            }
            long sum = 0;
            while (pq.size() > 1) { // 마지막 남은 최종 결과값은 1개이므로 1개 일때 종료
                long a = pq.poll();
                long b = pq.poll();
                sum += a + b;
                pq.add(a + b);
            }
            sb.append(sum).append("\n");
        }
        System.out.println(sb.toString());

    }
}
