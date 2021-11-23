package DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj16197_두동전 {
    static class Coin{
        int x1;
        int y1;
        int x2;
        int y2;
        public Coin(int x1, int y1, int x2, int y2){
            this.x1=x1;
            this.y1=y1;
            this.x2=x2;
            this.y2=y2;
        }
    }

    static int N, M, x1, x2, y1, y2, coinCnt;
    static char[][] map;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static int ans = 11;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for(int i=0; i < N; i++){
            String s = br.readLine();
            for(int j=0; j < M; j++){
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'o'){
                    if(coinCnt == 0){
                        x1 = i;
                        y1 = j;
                    } else {
                        x2 = i;
                        y2 = j;
                    }
                    // 코인 개수 ++
                    coinCnt++;
                }
            }
        }

        coinGame(new Coin(x1, y1, x2, y2), 0);
        if(ans == 11){
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

    // 완전 탐색
    static void coinGame(Coin coins, int cnt) {

        // 이동 횟수가 최소값보다 커졌거나 10회 이상이면 return
        if(cnt > ans || cnt > 10) return;

        for(int i=0; i < 4; i++) {
            int out = 0;
            int nx1 = coins.x1 + dx[i];
            int ny1 = coins.y1 + dy[i];
            int nx2 = coins.x2 + dx[i];
            int ny2 = coins.y2 + dy[i];

            // 범위 내에 있는지 확인
            int outCnt = isIn(nx1, ny1, nx2, ny2);

            // 2개가 나간 경우 제외
            if (outCnt == 2) continue;

            // 1개만 나간 경우 움직인 횟수를 비교하고 return
            if (outCnt == 1) {
                ans = Math.min(ans, cnt + 1);
                return;
            }

            // 벽으로 이동한 경우는 다시 원래 값으로 저장
            if (map[nx1][ny1] == '#') {
                nx1 = coins.x1;
                ny1 = coins.y1;
            }

            if (map[nx2][ny2] == '#') {
                nx2 = coins.x2;
                ny2 = coins.y2;
            }

            coinGame(new Coin(nx1, ny1, nx2, ny2), cnt + 1);
        }
    }

    static int isIn(int x1, int y1, int x2, int y2) {
        int cnt = 0;
        if(x1 < 0 || x1 >= N || y1 < 0 || y1 >= M){
            cnt++;
        }
        if(x2 < 0 || x2 >= N || y2 < 0 || y2 >= M){
            cnt++;
        }
        return cnt;
    }
}
