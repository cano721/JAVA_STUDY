package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16173_점프왕쩰리 {
    static class Pair {
        int x;
        int y;
        Pair (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N;
    static int dx[] = {1, 0}; // → ↓
    static int dy[] = {0, 1};
    static int[][] map;
    static boolean visited[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        visited = new boolean[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

    static String bfs() {
        Queue<Pair> q = new LinkedList<>();
        visited[0][0] = true;
        q.offer(new Pair(0, 0));

        while(!q.isEmpty()) {
            Pair cur = q.poll();
            int cnt = map[cur.x][cur.y];

            if(cnt == -1) {
                return "HaruHaru";
            }

            for(int i=0; i<2; i++) {
                int nx = cur.x + dx[i] * cnt;
                int ny = cur.y + dy[i] * cnt;

                if(nx>=0 && nx<N && ny>=0 && ny<N && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.offer(new Pair(nx, ny));
                }
            }
        }

        return "Hing";
    }
}
