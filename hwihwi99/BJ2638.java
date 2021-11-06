import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj2638 {
    static class Data{
        int x;
        int y;

        public Data(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, M;
    static int map[][];
    static boolean visited[][];
    static Queue <Data> q;
    // 상 하 좌 우
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};
    static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            // 먼저 공기랑 닿아있는 치즈 구하고
            bfs();
            if(isMelted())
                res++;
            else break;
        }
        System.out.println(res);
    }

    static boolean isMelted() {
        boolean isAvailable = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 외부와 2번 이상 닿은 치즈가 있을 경우
                if(map[i][j] >= 3) {
                    // 치즈 녹여주고 공기로 만든다
                    map[i][j] = 0;
                    // 치즈 녹일 수 있으니까
                    isAvailable = true;
                }
                // 외부(공기)와 한번만 닿았었으면 그냥 원래 치즈로 돌려놓기
                if(map[i][j] == 2) {
                    map[i][j] = 1;
                }
            }
        }

        return isAvailable;
    }

    static void bfs() {
        q = new LinkedList<>();
        // 매번 새로 만들어주기
        visited = new boolean [N][M];
        visited[0][0] = true;
        q.offer(new Data(0, 0));

        Data cur = null;
        while(!q.isEmpty()) {
            cur = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dr[dir];
                int ny = cur.y + dc[dir];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M)
                    continue;
                if(visited[nx][ny]) continue;
                // 공기로만 도니까 치즈로는 가지 않는다
                if(map[nx][ny] >= 1) {
                    map[nx][ny]++;
                    continue;
                }

                visited[nx][ny] = true;
                q.offer(new Data(nx, ny));
            }
        }
    }
}
