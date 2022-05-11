package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int length = n / 8 + 1;
		int[][] dp = new int[n + 6][length];
		for (int i = 0; i < dp.length; i++)
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		dp[0][0] = 0;
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j < length; j++) {
				if (dp[i][j] == Integer.MAX_VALUE)
					continue;
				dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 10000);
				if (j + 1 < length)
					dp[i + 3][j + 1] = Math.min(dp[i + 3][j + 1], dp[i][j] + 25000);
				if (j + 2 < length)
					dp[i + 5][j + 2] = Math.min(dp[i + 5][j + 2], dp[i][j] + 37000);
				if (j >= 3)
					dp[i + 1][j - 3] = Math.min(dp[i + 1][j - 3], dp[i][j]);
			}
		}
		int answer = Integer.MAX_VALUE;
		for (int i = n; i < dp.length; i++)
			for (int j = 0; j < length; j++)
				answer = Math.min(answer, dp[i][j]);
		System.out.println(answer);
	}
}