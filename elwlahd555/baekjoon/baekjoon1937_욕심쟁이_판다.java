package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class baekjoon1937_욕심쟁이_판다 {
    static int[] rangeX = { -1, 0, 1, 0 };
    static int[] rangeY = { 0, 1, 0, -1 };
    static int N;
    static int[][] map; // 대나무 숲
    static int[][] dp;
 
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
 
        map = new int[N][N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
 
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans = Math.max(ans, DFS(i, j));
            }
        }
 
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
 
    public static int DFS(int x, int y) {
        // dp에 저장된 값이 있을 경우 그 값을 반환.
        if (dp[x][y] != 0) {
            return dp[x][y];
        }
        
        // 판다가 대나무 숲에서 최소한 1년은 살 수 있으므로
        // dp[x][y] = 1로 초기화 할 수 있음.
        dp[x][y] = 1;
 
        int dx, dy;
        // 상하좌우 검사.
        for (int i = 0; i < 4; i++) {
            dx = x + rangeX[i];
            dy = y + rangeY[i];
            
            // 범위에서 벗어났을 경우 continue.
            if (dx < 0 || dy < 0 || dx >= N || dy >= N) {
                continue;
            }
            
            // 현재 대나무 숲보다 더 많은 양의 대나무가 있는 경우.
            if (map[x][y] < map[dx][dy]) {
                // 상하좌우 중에서 가장 오랫동안 생존할 수 있는 기간을 계산한다.
                dp[x][y] = Math.max(dp[x][y], DFS(dx, dy) + 1);
                DFS(dx, dy);
            }
        }
        return dp[x][y];
    }
 
}
