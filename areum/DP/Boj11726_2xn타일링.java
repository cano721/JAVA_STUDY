package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj11726_2xn타일링 {
    static final int DIV = 10007;
    static int N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];

        dp[0] = 0;
        dp[1] = 1; // 1일때는 한가지만 가능
        dp[2] = 2; // 1일때는 두가지만 가능

        for (int i=3; i<=N; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % DIV;
        }

        System.out.println(dp[N]);
    }
}
