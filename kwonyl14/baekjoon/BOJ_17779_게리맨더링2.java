package day220410;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17779_게리맨더링2 {

    static int N, total, answer = Integer.MAX_VALUE;
    static int[][] map;
    static int[] population = new int[5];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                total += map[i][j];
            }
        }

        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                for (int d1 = 1; d1 < N; d1++) {
                    for (int d2 = 1; d2 < N; d2++) {
                        if (x+d1+d2 > N) continue;
                        if (y-d1 < 1) continue;
                        if (y+d2 > N) continue;
                        solve(x, y, d1, d2);
                    }
                }
            }
        }

        System.out.println(answer);
    }
    static boolean[][] visited;
    private static void solve(int x, int y, int d1, int d2) {
        visited = new boolean[N+1][N+1];
        //경계선 표시
        for (int i = 0; i <= d1; i++) {
            visited[x+i][y-i] = true;
            visited[x+d2+i][y+d2-i] = true;
        }

        for (int i = 0; i <= d2; i++) {
            visited[x+i][y+i] = true;
            visited[x+d1+i][y-d1+i] = true;
        }

        population[0] = 0;
        population[1] = 0;
        population[2] = 0;
        population[3] = 0;
        population[4] = 0;

        //1번 선거구
        for (int r = 1; r < x+d1; r++) {
            for (int c = 1; c <= y; c++) {
                if (visited[r][c]) break;
                population[0] += map[r][c];
            }
        }
        population[4] += population[0];
        //2번 선거구
        for (int r = 1; r <= x+d2; r++) {
            for (int c = N; c > y; c--) {
                if (visited[r][c]) break;
                population[1] += map[r][c];
            }
        }
        population[4] += population[1];
        //3번 선거구
        for (int r = x+d1; r <= N; r++) {
            for (int c = 1; c < y-d1+d2; c++) {
                if (visited[r][c]) break;
                population[2] += map[r][c];
            }
        }
        population[4] += population[2];
        //4번 선거구
        for (int r = N; r > x+d2; r--) {
            for (int c = N; c >= y-d1+d2; c--) {
                if (visited[r][c]) break;
                population[3] += map[r][c];
            }
        }
        population[4] += population[3];

        population[4] = total - population[4];

        Arrays.sort(population);
        answer = Math.min(answer, population[4] - population[0]);
    }
}
