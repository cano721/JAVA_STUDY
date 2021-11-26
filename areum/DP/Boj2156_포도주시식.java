package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2156_포도주시식 {
    static int N;
    static int[] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        dp = new int[N+1];

        for(int i = 1; i<=N; i++) {
            arr[i] =Integer.parseInt(br.readLine());
        }

        // dp[1] 초기화
        dp[1] = arr[1];

        // N이 1인 경우 예외 처리
        if(N>=2)
            dp[2] = arr[1] + arr[2];

        for(int i=3;i<=N;i++) {
            // 경우의 수 중 최댓값
            dp[i] = Math.max(Math.max(arr[i-1] + dp[i-3]+ arr[i], dp[i-2] + arr[i]), dp[i-1]) ;
        }

        System.out.println(dp[N]);
    }
}
