import java.io.*;
import java.util.*;

public class Main {
    static char[][] board = new char[12][6];
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visit;
    static boolean removed;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 12; i++) {
            board[i] = br.readLine().toCharArray();
        }
        
        visit = new boolean[12][6];
        int ans = 0;    
        while (true) {
            removed = false;
            remove();
            if (!removed) break;
            down();
            ans++;
        }

        System.out.println(ans);
    }

    private static void down() {
        for (int j = 0; j < 6; j++) {
            Queue<Character> que = new LinkedList<>();
            for (int i = 11; i >= 0; i--) {
                if (board[i][j] != '.') {
                    que.offer(board[i][j]);
                    board[i][j] = '.';
                }
            }
            for (int i = 11; i >= 0 && !que.isEmpty(); i--) {
                board[i][j] = que.poll();
            }
        }
    }

    private static void remove() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (board[i][j] != '.' &&  getCnt(i, j, board[i][j]) >= 4) {
                    removed = true;
                    remove(i, j, board[i][j]);
                }
            }
        }
    }

    private static int getCnt(int x, int y, int color) {
        int ret = 1;
        visit[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];

            if (check(tx, ty) && board[tx][ty] == color && !visit[tx][ty]) {
                ret += getCnt(tx, ty, color);
            }
        }

        visit[x][y] = false;
        return ret;
    }

    private static void remove(int x, int y, int color) {
        board[x][y] = '.';
        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            if (check(tx, ty) && board[tx][ty] == color) {
                remove(tx, ty, color);
            }
        }
    }

    private static boolean check(int x, int y) {
        return x >= 0 && y >= 0 && x < 12 && y < 6;
    }
}