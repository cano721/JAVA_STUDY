package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[] drinks;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		drinks = new int[n + 1];
		for (int i = 1; i <= n; i++)
			drinks[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(drinks);
		int[] noTasteFoods = new int[n + 1];
		for (int i = 1; i <= n; i++)
			noTasteFoods[i] = getNoTasteFood(i, d);
		int[][] dp = new int[k + 1][n + 1];
		for (int i = 1; i <= k; i++)
			for (int j = 1; j <= n; j++) {
				int cnt = noTasteFoods[j];
				dp[i][j] = Math.max(dp[i][j - 1], cnt + dp[i - 1][j - cnt]);
			}
		System.out.println(dp[k][n]);
	}

	public static int getNoTasteFood(int idx, int time) {
		int left = 1, right = idx, min = drinks[idx] - time + 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (drinks[mid] < min)
				left = mid + 1;
			else
				right = mid - 1;
		}
		return idx - right;
	}
}