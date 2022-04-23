import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int dr[] = {-1, 1, 0, 0};
    private static int dc[] = {0, 0, -1, 1};

    static class Ice {
        int r, c, life;

        public Ice(int r, int c, int life) {
            this.r = r;
            this.c = c;
            this.life = life;
        }
    }

    static int N, M, map[][];
    static boolean visited[][], isDead[][];
    static Queue<Ice> deadIceList = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        isDead = new boolean[N][M];
        boolean flag = true;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0) flag = false;
            }
        }

        if (flag) {
            System.out.println(0);
            return;
        }

        int ans = 0;

        while (true) {
            visited = new boolean[N][M];

            nowLoop:
            for (int i = 1; i < N-1; i++) {
                for (int j = 1; j < M-1; j++) {
                    if (map[i][j] > 0) {
                        bfs(i, j);
                        break nowLoop;
                    }
                }
            }
//            print();
            ans++;
            flag = true;
            for (int i = 1; i < N-1; i++) {
                for (int j = 1; j < M-1; j++) {
                    if (!visited[i][j] && map[i][j] > 0) {
                        System.out.println(ans-1);
                        return;
                    }
                    if (map[i][j] > 0) flag = false;
                }
            }
            if (flag) {
                System.out.println(0);
                return;
            }
        }
    }

    private static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void bfs(int r, int c) {

        Queue<Ice> queue = new LinkedList<>();
        queue.offer(new Ice(r, c, map[r][c]));
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            Ice nowIce = queue.poll();
            // 주변에 물로 인해 얼마나 감소하는지 계산
            int cnt = 0;
            for (int d = 0; d < 4; d++) {
                int nr = nowIce.r + dr[d];
                int nc = nowIce.c + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if (map[nr][nc] == 0) {
//                    map[nowIce.r][nowIce.c]--;
                    cnt ++;
                }
            }
            // 다녹아 없어질 얼음은 죽은 얼음 리스트에 넣음
            if (cnt >= map[nowIce.r][nowIce.c]) {
                // 감소할 빙하 리스트에 넣는다.
                deadIceList.offer(new Ice(nowIce.r, nowIce.c, cnt));
                isDead[nowIce.r][nowIce.c] = true;
            } else {
                map[nowIce.r][nowIce.c] -= cnt;
            }

            for (int d = 0; d < 4; d++) {
                int nr = nowIce.r + dr[d];
                int nc = nowIce.c + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue;
                if (visited[nr][nc]) continue;
                if (map[nr][nc] > 0 && !isDead[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.offer(new Ice(nr, nc, map[nr][nc]));
                }
            }
        }

        while (!deadIceList.isEmpty()) {
            Ice ice = deadIceList.poll();
            map[ice.r][ice.c] = 0;
        }
//        print();
    }
}
