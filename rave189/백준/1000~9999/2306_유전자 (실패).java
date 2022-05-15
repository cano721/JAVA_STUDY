package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		int length = input.length();
		int[][] dp = new int[length + 1][length + 1];
		Arrays.fill(dp[0], 1);
		for (int i = 1; i <= length; i++) {
			for (int j = i + 1; j <= length; j++) {
				char left = input.charAt(i - 1);
				char right = input.charAt(j - 1);
				dp[i][j] = dp[i][j - 1];
				if ((left == 'a' && right == 't') || (left == 'g' && right == 'c'))
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + dp[i][j - 1]);
			}
		}
		System.out.println(dp[length][length]);
	}
}