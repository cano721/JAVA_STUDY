package icepeppermint.boj16173;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
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
        System.out.println(bfs(1, 1) ? "HaruHaru" : "Hing");
    }

    static boolean bfs(int y, int x) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(y, x));
        while (!queue.isEmpty()) {
            Position position = queue.poll();
            if (map[position.y][position.x] == -1) {
                return true;
            }
            for (int i = 0; i < 2; i++) {
                int ny = position.y + map[position.y][position.x] * dy[i];
                int nx = position.x + map[position.y][position.x] * dx[i];
                if ((ny != position.y || nx != position.x) && inRange(ny, nx)) {
                    queue.add(new Position(ny, nx));
                }
            }
        }
        return false;
    }

    static boolean inRange(int y, int x) {
        return y >= 1 && x >= 1 && y <= n && x <= n;
    }

    static class Position {
        int y;
        int x;

        Position(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}