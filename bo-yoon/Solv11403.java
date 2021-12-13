package algo.study.sol11403;

import java.io.*;
import java.util.StringTokenizer;

// 플로이드 와샬 : 모든 정점에서 모든 정점으로 최단 경로 구하기
public class Solv11403 {
    static final int MAX = 100;
    static int N;
    static int graph[][];

    static void floyd() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (graph[i][k] == 1 && graph[k][j] == 1) {
                        graph[i][j] = 1;
                    }
                }
            }
        }

    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder res = new StringBuilder();


        N = Integer.parseInt(st.nextToken());

        graph = new int[MAX][MAX];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        floyd();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                res.append(graph[i][j] + " ");
            }
            res.append("\n");
        }

        bw.write(res.toString());
        bw.flush();
        bw.close();
        br.close();

    }
}

