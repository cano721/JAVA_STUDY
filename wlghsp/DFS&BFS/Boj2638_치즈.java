package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2638_치즈 {
    // 너무 어렵습니다. 따라치기라도 하겠습니다.
    // https://aomee0880.tistory.com/84?category=772285
    // bfs, dfs는 따로 공부해야겠습니다.
    static int n, m, map[][], visit[][];
    static int dx[] = { 1, 0, -1, 0 };
    static int dy[] = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visit = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int time = 0;
        boolean check = true;

        while (check) {
            init_visit(); // visit 초기화
            bfs();

            // 치즈가 있는지 확인
            check = false;
            loop: for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] != 0) {
                        check = true;
                        break loop;
                    }
                }
            }
            time++;
        }
        System.out.println(time);

    }

    static void bfs() {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0));

        while (!q.isEmpty()) {
            Pair p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m)
                    continue;

                if (map[nx][ny] == 0 && visit[nx][ny] == 0) {
                    visit[nx][ny] = 1;
                    q.add(new Pair(nx, ny));
                }
                if (map[nx][ny] == 1) {
                    visit[nx][ny]++;
                    if (visit[nx][ny] >= 2)
                        map[nx][ny] = 0;
                }

            }

        }
    }

    static void init_visit() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visit[i][j] = 0;
            }
        }
    }

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
