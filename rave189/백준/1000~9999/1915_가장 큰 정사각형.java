package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		boolean[][] map = new boolean[n + 1][m + 1];
		int[][] dp = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			String input = br.readLine();
			for (int j = 1; j <= m; j++)
				map[i][j] = input.charAt(j - 1) == '1' ? true : false;
		}
		int max = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (!map[i][j])
					continue;
				dp[i][j] = 1;
				if (dp[i - 1][j] == dp[i][j - 1] && dp[i][j - 1] == dp[i - 1][j - 1])
					dp[i][j] = dp[i - 1][j - 1] + 1;
				else
					dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1;
				max = max(max, dp[i][j]);
			}
		}
		System.out.println(max * max);
	}

	public static int max(int a, int b) {
		return a > b ? a : b;
	}

	public static int min(int a, int b, int c) {
		return min(min(a, b), min(b, c));
	}

	public static int min(int a, int b) {
		return a < b ? a : b;
	}
}