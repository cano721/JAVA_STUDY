package icepeppermint.boj2206;

import java.util.Scanner;

public class DFS {
    static Scanner scanner = new Scanner(System.in);
    static int n, m;
    static boolean[][] map = new boolean[105][105];
    static boolean[] visited = new boolean[105];

    static {
        n = scanner.nextInt();
        m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            map[u][v] = map[v][u] = true;
        }
    }

    public static void main(String[] args) {
        System.out.println(dfs(1) - 1);
    }

    static int dfs(int u) {
        int ret = 1;
        visited[u] = true;
        for (int v = 1; v <= n; v++) {
            if (map[u][v] && !visited[v]) {
                ret += dfs(v);
            }
        }
        return ret;
    }
}
