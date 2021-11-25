package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int mod = 1000000009;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		int[] dp = new int[1000001];
		dp[0] = 1;
		for (int i = 0; i < dp.length; i++) {
			try {
				dp[i + 1] = (dp[i + 1] + dp[i]) % mod;
				dp[i + 2] = (dp[i + 2] + dp[i]) % mod;
				dp[i + 3] = (dp[i + 3] + dp[i]) % mod;
			} catch (ArrayIndexOutOfBoundsException e) {
			}
		}
		while (testCase-- > 0) {
			int n = Integer.parseInt(br.readLine());
			answer.append(dp[n] % mod).append('\n');
		}
		System.out.println(answer);
	}
}