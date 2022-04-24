package day220424;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17136_색종이붙이기 {

    static int map[][], colorPaperCnt[], answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[10][10];
        colorPaperCnt = new int[]{0, 5, 5, 5, 5, 5};

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);

        if (answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    private static void dfs(int r, int c, int cnt) {
        if (r == 9 && c == 10) {
            answer = Math.min(answer, cnt);
            return;
        }

        if (answer <= cnt) {
            return;
        }

        if (c == 10) {
            dfs(r+1, 0, cnt);
            return;
        }


        if (map[r][c] == 1) {
            for (int i = 5; i >= 1; i--) {
                if (colorPaperCnt[i] > 0 && isAttach(r, c, i)) {
                    colorPaperCnt[i] --;
                    attach(i, r, c, true);
                    dfs(r, c + 1, cnt + 1);
                    attach(i, r, c, false);
                    colorPaperCnt[i] ++;
                }
            }
        } else {
            dfs(r, c+1, cnt);
        }
    }

    private static boolean isAttach(int r, int c, int i) {
        for (int j = r; j < r + i; j++) {
            for (int k = c; k < c + i; k++) {
                if (j < 0 || k < 0 || j >= 10 || k >= 10) return false;
                if (map[j][k] == 0) return false;
            }
        }
        return true;
    }

    private static void attach(int h, int r, int c, boolean isAttach) {
        if (isAttach) {
            for (int i = r; i < r + h; i++) {
                for (int j = c; j < c + h; j++) {
                    map[i][j] = 0;
                }
            }
        } else {
            for (int i = r; i < r + h; i++) {
                for (int j = c; j < c + h; j++) {
                    map[i][j] = 1;
                }
            }
        }
    }
}
