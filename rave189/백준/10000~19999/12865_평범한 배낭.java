package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		Item[] items = new Item[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			items[i] = new Item(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		int[][] dp = new int[n + 1][k + 1];
		for (int i = 1; i <= n; i++) {
			int value = items[i - 1].value;
			int weight = items[i - 1].weight;
			for (int j = 0; j < dp[i].length; j++) {
				try {
					dp[i][j] = Math.max(value + dp[i - 1][j - weight], dp[i - 1][j]);
				} catch (ArrayIndexOutOfBoundsException e) {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		System.out.println(dp[n][k]);
	}
}

class Item {
	int weight, value;

	public Item(int weight, int value) {
		this.weight = weight;
		this.value = value;
	}
}