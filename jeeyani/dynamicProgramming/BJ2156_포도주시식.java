package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 마지막 n잔을 마셨을 경우를 생각하자!!
 * 
 * 1. n-1를 마신 경우  => n-2 잔을 고를 수 없음
 * 2. n-1를 안마신 경우  => n-2 잔을 고를 수 있음
 * 
 * 1. dp[n] = dp[n-3] + arr[n-1] + arr[n];
 * 2. dp[n] = dp[n-2] + arr[n];
 * 
 * 따라서
 * dp[n] = max(dp[n-2] + arr[n],dp[n-3] + arr[n-1] + arr[n]);
 * 
 * 
 * but, 포도주를 2번 연속 안마실 경우
 * dp[n] = max(dp[n-1],dp[n])
 * 
 * 
 * 결국 최종 점화식은
 * dp[n] = max(dp[n-1],dp[n-2] + arr[n],dp[n-3] + arr[n-1] + arr[n]);
 * 
 */

public class BJ2156_포도주시식 {

	static int n;
	static int[] cup;
	static int[] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		cup = new int[10001];
		dp = new int[10001];
		
		for (int i = 1; i <=n; i++) {
			cup[i] = Integer.parseInt(br.readLine());
		}
		
		//초기화
		dp[1] = cup[1];
		dp[2] = cup[1]+cup[2];
		
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i-1];
			dp[i] = Math.max(dp[i], dp[i-2]+cup[i]);
			dp[i] = Math.max(dp[i], dp[i-3]+cup[i-1]+cup[i]);
		}
		
		System.out.println(dp[n]);
	}

}
