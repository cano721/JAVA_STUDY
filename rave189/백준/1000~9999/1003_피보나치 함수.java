package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int[][] dp = new int[2][41];
		dp[0][0] = 1;
		dp[1][1] = 1;
		for(int i=2; i<41; i++) {
			dp[0][i] = dp[0][i-1] + dp[0][i-2];
			dp[1][i] = dp[1][i-1] + dp[1][i-2];
		}
		int testCase = Integer.parseInt(br.readLine());
		while(testCase-- > 0) {
			int n = Integer.parseInt(br.readLine());
			answer.append(dp[0][n]).append(' ').append(dp[1][n]).append('\n');
		}
		System.out.println(answer);
	}
}