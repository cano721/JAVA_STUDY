package icepeppermint.boj16173;

import java.util.Scanner;

public class DFS {
    static Scanner scanner = new Scanner(System.in);
    static int n;
    static int[][] map = new int[5][5];
    static int[] dy = new int[] { 1, 0 };
    static int[] dx = new int[] { 0, 1 };

    static {
        n = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                map[i][j] = scanner.nextInt();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(dfs(1, 1) ? "HaruHaru" : "Hing");
    }

    static boolean dfs(int y, int x) {
        if (map[y][x] == -1) {
            return true;
        }
        for (int i = 0; i < 2; i++) {
            if (map[y][x] != 0) {
                int ny = y + map[y][x] * dy[i];
                int nx = x + map[y][x] * dx[i];
                if (inRange(ny, nx)) {
                    if (dfs(ny, nx)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static boolean inRange(int y, int x) {
        return y >= 1 && x >= 1 && y <= n && x <= n;
    }
}