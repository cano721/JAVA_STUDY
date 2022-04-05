package day220405;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500_테트로미노 {

    static int N, M, map[][];
    static int answer;
    static int[][] tr = {
            {0, 0, 0}, {1, 2, 3}, //ㅡ |
            {0, 1, 1}, //ㅁ
            {1, 2, 2}, {0, 0, 1}, {0, 1, 2}, {1, 2, 2}, {0, 1, 2}, {0, 0, 1}, {1, 1, 1}, {1, 1, 1}, //ㄴ
            {1, 1, 2}, {1, 1, 2}, {0, 1, 1}, {0, 1, 1}, //지그재그
            {0, 1, 0}, {0, -1, 0}, {1, 1, 2}, {1, 1, 2} //ㅗ
    };
    static int[][] tc = {
            {1, 2, 3}, {0, 0, 0}, //ㅡ |
            {1, 0, 1}, //ㅁ
            {0, 0, 1}, {1, 2, 2}, {1, 1, 1}, {0, 0, -1}, {1, 0, 0}, {-1, -2, -2}, {0, -1, -2}, {0, 1, 2}, //ㄴ
            {0, 1, 1}, {0, -1, -1}, {-1, -1, -2}, {1, 1, 2}, //지그재그
            {1, 1, 2}, {1, 1, 2}, {0, -1, 0}, {0, 1, 0} //ㅗ
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                makeTetromino(i, j);
            }
        }

        System.out.println(answer);
    }

    private static void makeTetromino(int r, int c) {
        //테트로미노 모양 선택
        Loop:
        for (int i = 0, num = tr.length; i < num; i++) {
            //선택된 테트로미노 모양에 포함된 수 계산
            int sum = map[r][c];
            for (int j = 0, length = tr[i].length; j < length; j++) {
                int nr = r + tr[i][j];
                int nc = c + tc[i][j];
                if (nr < 0 || nc < 0 || nr >= N || nc >= M) continue Loop;
                sum += map[nr][nc];
            }
            answer = Math.max(answer, sum);
        }
    }
}
