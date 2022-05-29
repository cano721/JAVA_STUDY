package day2205.day29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1937_욕심쟁이판다 {

    static int N;
    static int[][] map;
    static int answer;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] memo;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        memo = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dfs(i, j);
            }
        }
        System.out.println(answer);
    }

    private static int dfs(int r, int c) {
        if (memo[r][c] > 0) return memo[r][c];

        int now = 1;
        int max = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
            if (map[nr][nc] <= map[r][c]) continue;

            max = Math.max(max, dfs(nr, nc));
        }

        now += max;
        memo[r][c] = now;
        answer = Math.max(answer, now);
        return now;
    }
}
