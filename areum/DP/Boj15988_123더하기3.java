package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj15988_123더하기3 {
    static int N;
    static long div = 1000000009;
    static long[] dp = new long[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        // dp 초기 값 초기화
        dp[1] = 1; // N=1 → 1 = 1
        dp[2] = 2; // N=2 → 1+1, 2 = 2
        dp[3] = 4; // N=3 → 1+1+1, 1+2, 2+1, 3 = 4

        for(int i=4; i<=10; i++) {
            dp[i] = ( dp[i-3] + dp[i-2] + dp[i-1] ) % div;
        }

        for(int t=0; t<T; t++) {
            int num = Integer.parseInt(br.readLine());
            System.out.println(dp[num]);
        }
    }
}
