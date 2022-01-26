package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 다시 풀어볼 것
 * @author Rave
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] sum = new int[n + 1];
		int[] arr = new int[n + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum[i] = sum[i - 1] + arr[i];
		}
		int trainNum = Integer.parseInt(br.readLine());
		int[][] dp = new int[n + 1][4];
		for (int i = 1 * trainNum; i <= n; i++)
			for (int j = 1; j < dp[i].length; j++)
				dp[i][j] = Math.max(dp[i - 1][j], dp[i - trainNum][j - 1] + sum[i] - sum[i - trainNum]);
		System.out.println(dp[n][3]);
	}
}