package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static final int mod = 1000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int[] dp = new int[input.length() + 1];
		int prev = 0;
		dp[0] = 1;
		for (int i = 1; i < dp.length; i++) {
			int cur = input.charAt(i - 1) - '0';
			int sumOfPrev = prev * 10 + cur;
			prev = cur;
			if (cur > 0)
				dp[i] = dp[i - 1];
			if (i >= 2 && isValid(sumOfPrev))
				dp[i] += dp[i - 2];
			dp[i] %= mod;
		}
		System.out.println(dp[input.length()]);
	}

	public static boolean isValid(int n) {
		return 10 <= n && n <= 26;
	}
}