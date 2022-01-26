package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[][] map, dp;
	static int INF = 100000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		dp = new int[n][(1 << n) - 1];
		for (int i = 0; i < n; i++)
			Arrays.fill(dp[i], INF);
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		System.out.println(tsp(0, 1));
	}

	public static int tsp(int cur, int visited) {
		if (visited == (1 << map.length) - 1)
			return map[cur][0] == 0 ? INF : map[cur][0];
		else if (dp[cur][visited] != INF)
			return dp[cur][visited];

		for (int i = 0; i < map.length; i++) {
			if (map[cur][i] == 0 || (visited & (1 << i)) != 0)
				continue;
			dp[cur][visited] = Math.min(dp[cur][visited], tsp(i, visited | (1 << i)) + map[cur][i]);
		}
		return dp[cur][visited];
	}
}