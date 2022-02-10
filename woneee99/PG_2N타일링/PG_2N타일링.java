public class PG_2N타일링 {
	int[] dp;

	public int solution(int n) {
		dp = new int[n + 1];
		dp[0] = 1;
		dp[1] = 1;

		return cal(n);
	}

	public int cal(int x) {
		if (dp[x] > 0) {
			return dp[x];
		} else {
			for (int i = 2; i <= x; i++) {
				dp[i] = (dp[i - 1] + dp[i - 2]) % 1_000_000_007;
			}
		}

		return dp[x];
	}
}