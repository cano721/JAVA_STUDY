package day2205.day30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1915_가장큰정사각형 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = 0;
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (chars[j] == '0') map[i][j] = 0;
                else map[i][j] = 1;
                answer = Math.max(answer, map[i][j]);
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                //왼쪽 위쪽 왼쪽위대각선이 모두 1이상이면 현재값 + 1 하기?
                if (map[i][j] > 0 && map[i-1][j] > 0 && map[i][j-1] > 0 && map[i-1][j-1] > 0) map[i][j] = Math.min(map[i][j-1], Math.min(map[i-1][j-1], map[i-1][j])) + 1;
                answer = Math.max(answer, map[i][j]);
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }

        System.out.println(answer * answer);

    }
}
