package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon14462_소가_길을_건너간_이유8 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int map[][] = new int[2][N + 1];
		for (int i = 0; i < 2; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(br.readLine());
			}
		}
		int dp[][] = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				if (Math.abs(map[0][i] - map[1][j]) <= 4) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
			}
		}
		System.out.println(dp[N][N]);
	}
}
