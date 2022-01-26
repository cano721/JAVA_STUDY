package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] stairs = new int[n];
		for (int i = 0; i < n; i++)
			stairs[i] = Integer.parseInt(br.readLine());
		int[][] dp = new int[n + 1][2];
		dp[1][0] = stairs[0];
		for (int i = 2; i <= n; i++) {
			dp[i][0] = Math.max(dp[i - 2][0], dp[i - 2][1]) + stairs[i - 1];
			dp[i][1] = dp[i - 1][0] + stairs[i - 1];
		}
		System.out.println(Math.max(dp[n][0], dp[n][1]));
	}
}