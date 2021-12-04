package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2579_계단오르기 {
    static int N;
    static int[] dp, cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1]; // 각 단계별 최댓값
        cost = new int[N+1];

        for(int i=1; i<=N; i++) {
            cost[i] = Integer.parseInt(br.readLine());
        }

        // 첫번쨰 계단
        dp[1] = cost[1];

        if(N>=2) {
            dp[2] = cost[1] + cost[2];
        }

        for (int i=3; i<=N; i++) {
            dp[i] = Math.max(dp[i-3] + cost[i-1] + cost[i], dp[i-2] + cost[i]);
        }

        System.out.println(dp[N]);
    }
}
