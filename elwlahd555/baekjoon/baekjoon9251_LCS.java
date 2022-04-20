package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon9251_LCS {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String first = br.readLine();
		String second = br.readLine();
		int[][] memo = new int[first.length() + 1][second.length() + 1];
		for (int i = 1; i <= first.length(); i++) {
			for (int j = 1; j <= second.length(); j++) {
				if (first.charAt(i - 1) == second.charAt(j - 1)) {
					memo[i][j] = memo[i - 1][j - 1] + 1;
				} else {
					memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
				}
			}
		}
		System.out.println(memo[first.length()][second.length()]);
	}
}
