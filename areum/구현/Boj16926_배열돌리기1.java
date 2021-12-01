package 구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj16926_배열돌리기1 {
    static int N, M, R;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1}; // 위쪽, 오른쪽, 아래쪽, 위쪽 → 우상하좌
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 배열의 크기
        M = Integer.parseInt(st.nextToken()); // 배열의 크기
        R = Integer.parseInt(st.nextToken()); // 회전의 수

        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 돌려야하는 네모의 수
        int roll = Math.min(N, M) / 2;
        for(int r=0; r<R; r++) {
            for(int i=0; i<roll; i++) {
                int x = i;
                int y = i;
                int starPoint = map[i][i];
                int dir = 0;

                // 네방향
                while(dir<4) {

                    int nx = x + dx[dir];
                    int ny = y + dy[dir];

                    // 범위 내에 있으면
                    if (nx>=i && ny>=i && nx<N-i && ny<M-i) {
                        // 값 변경
                        map[x][y] = map[nx][ny];

                        // 좌표 변경
                        x = nx;
                        y = ny;
                    } else {
                        dir++;
                    }
                }
                map[i+1][i] = starPoint;
            }
        }

        // 출력
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
