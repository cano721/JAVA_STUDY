package algo.study.sol2660;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Solv2660 {

    static final int INF = 9999999;  // 인티저 맥스로 하면 더하면 인트범위 초과로 익셉션 생성
    static int N;
    static int graph[][];
    static int dist[];

    static void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);

                }
            }
        }

    }

    public static void main(String[] args) throws NoSuchElementException, NumberFormatException, IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder res = new StringBuilder();


        N = Integer.parseInt(br.readLine());

        graph = new int[N+ 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if ( i != j) {
                    graph[i][j] = INF;
                }
            }
        }

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int user1 = Integer.parseInt(st.nextToken());
            int user2 = Integer.parseInt(st.nextToken());
            if ( user1 == -1 && user2 == -1 ) {
                break;
            }
            graph[user1][user2] = 1;
            graph[user2][user1] = 1;
        }

        floyd();

        dist = new int[N+1];
        int candidate = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            int max = 0;
            for (int j = 1; j <= N; j++) {
                max = Math.max(max, graph[i][j]);
            }
            dist[i] = max;
            candidate = Math.min(max, candidate);
        }

        int cnt = 0;
        for (int i =1; i <= N; i++) {
            if(dist[i] == candidate) {
                cnt++;
            }
        }
        bw.write(candidate + " " + cnt + "\n");

        for (int i =1; i <= N; i++) {
            if(dist[i] == candidate) {
                res.append(i + " ");
            }
        }

        bw.write(res.toString());
        bw.flush();
        bw.close();
        br.close();

    }
}
