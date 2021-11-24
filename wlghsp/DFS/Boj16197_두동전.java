package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

/*

모르겠네요.. 공부하겠습니다. 
아래 블로그 그대로 옮겼네요. 
https://laugh4mile.tistory.com/66?category=895934
*/

public class Boj16197_두동전 {

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, M, min = Integer.MAX_VALUE;
    static char map[][];

    public static void main(String[] args) throws NumberFormatException, IOException {
        input = new BufferedReader(new StringReader(src));
        tokens = new StringTokenizer(input.readLine());
        N = Integer.parseInt(tokens.nextToken());
        M = Integer.parseInt(tokens.nextToken());
        map = new char[N][M];

        int coin[][] = new int[2][2];
        int cnt = 0;
        for (int r = 0; r < N; r++) {
            String line = input.readLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = line.charAt(c);
                if (map[r][c] == 'o') {
                    coin[cnt][0] = r;
                    coin[cnt++][1] = c;
                }
            }
        }
        // for(char x[] : map) {
        // System.out.println(Arrays.toString(x));
        // }

        // for(int x[] : coin) {
        // System.out.println(Arrays.toString(x));
        // }
        dfs(coin[0][0], coin[0][1], coin[1][0], coin[1][1], 0);
        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    private static void dfs(int r1, int c1, int r2, int c2, int depth) {
        // 둘중 하나만 벗어났을 경우
        if (depth > 10) {
            return;
        }
        // 둘이 동시에 벗어나도 리턴
        if ((!isIn(r1, c1) && !isIn(r2, c2))) {
            return;
        }
        if ((isIn(r1, c1) && !isIn(r2, c2)) || (!isIn(r1, c1) && isIn(r2, c2))) {
            if (min > depth) {
                min = depth;
            }
            return;
        }
        for (int d = 0; d < 4; d++) {
            int nr1 = r1 + dr[d];
            int nc1 = c1 + dc[d];
            int nr2 = r2 + dr[d];
            int nc2 = c2 + dc[d];

            if (isIn(nr1, nc1) && map[nr1][nc1] == '#') { // 벽만나면 이동 못함 ㅠㅠ
                nr1 = r1;
                nc1 = c1;
            }
            if (isIn(nr2, nc2) && map[nr2][nc2] == '#') { // 벽만나면 이동 못함 ㅠㅠ
                nr2 = r2;
                nc2 = c2;
            }
            dfs(nr1, nc1, nr2, nc2, depth + 1);

        }
    }

    static int dr[] = { 0, 0, -1, 1 };
    static int dc[] = { -1, 1, 0, 0 };

    static boolean isIn(int r, int c) {
        return (r >= 0 && c >= 0 && r < N && c < M);
    }

    static String src = "5 3\r\n" + "###\r\n" + ".o.\r\n" + "###\r\n" + ".o.\r\n" + "###";
}