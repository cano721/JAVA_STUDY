package day2205.day09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class BOJ_16507_어두운건무서워 {

    static int R, C, Q, map[][], temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (j > 0) map[i][j] += map[i][j-1];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken())-1;
            int c1 = Integer.parseInt(st.nextToken())-1;
            int r2 = Integer.parseInt(st.nextToken())-1;
            int c2 = Integer.parseInt(st.nextToken())-1;

            int answer = 0;
            for (int j = r1; j <= r2 ; j++) {
                answer += map[j][c2];
            }
            if (c1 > 0) {
                for (int j = r1; j <= r2 ; j++) {
                    answer -= map[j][c1-1];
                }
            }

            answer = answer / ((r2 - r1 + 1)*(c2 - c1 + 1));

            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}
