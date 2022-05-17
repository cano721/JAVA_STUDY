package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int LOCOMO_SIZE = 3;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] sumOfPassenger = new int[n + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++)
			sumOfPassenger[i] = sumOfPassenger[i - 1] + Integer.parseInt(st.nextToken());
		int max = Integer.parseInt(br.readLine());
		int[][] dp = new int[n + 1][LOCOMO_SIZE + 1];
		for (int i = max; i <= n; i++)
			for (int j = 1; j <= LOCOMO_SIZE; j++)
				dp[i][j] = Math.max(dp[i - 1][j], dp[i - max][j - 1] + sumOfPassenger[i] - sumOfPassenger[i - max]);
		System.out.println(dp[n][LOCOMO_SIZE]);
	}
}