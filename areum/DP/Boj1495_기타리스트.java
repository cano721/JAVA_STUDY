package DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1495_기타리스트 {
    static int N, M, S;
    static int[] volume;
    static boolean[][] dp;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        volume = new int[N + 1];
        dp = new boolean[N + 1][M + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            volume[i] = Integer.parseInt(st.nextToken());
        }


        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], false);
        }

        dp[0][S] = true;
        for (int i = 1; i <= N; i++) {
            for (int v = 0; v <= M; v++) {
                if (dp[i - 1][v]) {
                    if (v + volume[i] <= M) {
                        dp[i][v + volume[i]] = true;
                    }
                    if (0 <= v - volume[i]) {
                        dp[i][v - volume[i]] = true;
                    }
                }

            }
        }

        int ans = -1;
        for (int v = 0; v <= M; v++) {
            if (dp[N][v]) {
                ans = Math.max(ans, v);
            }
        }

        System.out.println(ans);
    }
}