package day2205.day25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1405_미친로봇 {

    static int N, east, west, south, north;
    static int map[][];
    private static double answer;
    private static boolean[][] visited;
    static int dr[] = {0, 0, 1, -1}, dc[] = {1, -1, 0, 0};
    static double percent[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        percent = new double[4];
        percent[0] = Double.parseDouble(st.nextToken()) / 100;
        percent[1] = Double.parseDouble(st.nextToken()) / 100;
        percent[2] = Double.parseDouble(st.nextToken()) / 100;
        percent[3] = Double.parseDouble(st.nextToken()) / 100;

        map = new int[30][30];
        visited = new boolean[30][30];

        dfs(15, 15, 0, 1);
        System.out.println(answer);
    }

    private static void dfs(int r, int c, int cnt, double result) {
        if (cnt == N) {
            answer += result;
            return;
        }

        visited[r][c] = true;

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if (visited[nr][nc]) continue;

            visited[nr][nc] = true;
            dfs(nr, nc, cnt+1, result * percent[i]);
            visited[nr][nc] = false;
        }

    }
}
