package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] map, dp;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		int answer = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				answer = Math.max(answer, dfs(i, j));
		System.out.println(answer);
	}

	public static int dfs(int x, int y) {
		if (dp[x][y] > 0)
			return dp[x][y];
		int max = 0;
		for (int i = 0; i < dx.length; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			try {
				if (map[x][y] <= map[nextX][nextY])
					continue;
				max = Math.max(max, dfs(nextX, nextY));
			} catch (ArrayIndexOutOfBoundsException e) {
			}
		}
		return dp[x][y] = max + 1;
	}
}