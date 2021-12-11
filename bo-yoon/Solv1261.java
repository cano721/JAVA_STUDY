package argo.study.sol1261;


import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
    int x, y, cnt;

    public Node(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Node o) {
        return cnt - o.cnt;
    }
}

public class Solv1261 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static int solve(int x, int y) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(x, y, 0));
        visited = new boolean[N + 1][M + 1];
        visited[x][y] = true;

        while (!pq.isEmpty()) {
            Node pos = pq.poll();

            int px = pos.x;
            int py = pos.y;

            int cnt = pos.cnt;

            if (px == N  && py == M) {
                return cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nx = px + dx[i];
                int ny = py + dy[i];

                if (nx < 1 || nx > N  || ny < 1 || ny > M ) continue;

                if (visited[nx][ny]) continue;

                visited[nx][ny] = true;

                if (map[nx][ny] == 0) {
                    pq.offer(new Node(nx, ny, cnt));
                } else {
                    pq.offer(new Node(nx, ny, cnt + 1));
                }

            }

        }
        return 0;

    }


    public static void main(String[] args) throws IOException {

        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];


        for (int i = 1; i <= N; i++) {
            String str = br.readLine();

            for (int j = 1; j <= M; j++) {
                map[i][j] = Character.getNumericValue(str.charAt(j - 1));
            }
        }


        bw.write(solve(1, 1) + "\n");

        bw.flush();
        bw.close();
        br.close();

    }
}

