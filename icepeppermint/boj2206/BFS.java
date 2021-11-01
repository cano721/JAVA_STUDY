package icepeppermint.boj2206;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
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
        System.out.println(bfs(1));
    }

    static int bfs(int u) {
        int ret = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(u);
        visited[u] = true;
        while (!queue.isEmpty()) {
            u = queue.poll();
            for (int i = 1; i <= n; i++) {
                if (map[u][i] && !visited[i]) {
                    ret += 1;
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
        return ret;
    }
}
