package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n + 2];
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			int pay = Integer.parseInt(st.nextToken());
			dp[i] = Math.max(dp[i], dp[i - 1]);
			try {
				dp[i + day] = Math.max(dp[i + day], dp[i] + pay);
			} catch (ArrayIndexOutOfBoundsException e) {
			}
		}
		System.out.println(Math.max(dp[n], dp[n + 1]));
	}
}