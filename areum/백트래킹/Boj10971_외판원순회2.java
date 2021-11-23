package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10971_외판원순회2 {
    private static int N, ans = Integer.MAX_VALUE;
    private static int[][] map;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            visited[i] = true;
            dfs(i, i, 0, 0);
            visited[i] = false;
        }

        System.out.println(ans);
    }

    private static void dfs(int st, int pr, int depth, int sum) {
        if (depth == N-1) {
            if(map[pr][st] == 0) return;
            ans = Math.min(ans, sum + map[pr][st]);
            return;
        }

        for (int next = 0; next < N; next++) {
            if(!visited[next] && map[pr][next] != 0) {
                visited[next] = true;
                dfs(st, next, depth+1, sum + map[pr][next]);
                visited[next] = false;
            }
        }
    }
}
