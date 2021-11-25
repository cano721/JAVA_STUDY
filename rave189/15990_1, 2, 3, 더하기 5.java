package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int mod = 1000000009;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int[][] dp = new int[100001][4];
		dp[1][1] = 1;
		dp[2][2] = 1;
		dp[3][3] = 1;
		for (int i = 1; i < dp.length; i++) {
			try {
				dp[i + 1][1] = (dp[i + 1][1] + dp[i][2]) % mod;
				dp[i + 1][1] = (dp[i + 1][1] + dp[i][3]) % mod;
				dp[i + 2][2] = (dp[i + 2][2] + dp[i][1]) % mod;
				dp[i + 2][2] = (dp[i + 2][2] + dp[i][3]) % mod;
				dp[i + 3][3] = (dp[i + 3][3] + dp[i][1]) % mod;
				dp[i + 3][3] = (dp[i + 3][3] + dp[i][2]) % mod;
			} catch (ArrayIndexOutOfBoundsException e) {
			}
		}
		int testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0) {
			int n = Integer.parseInt(br.readLine());
			answer.append(((dp[n][1] + dp[n][2]) % mod + dp[n][3]) % mod).append('\n');
		}
		System.out.println(answer);
	}
}