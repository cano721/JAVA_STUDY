import java.util.*;
import java.io.*;

/*
비트마스킹 - 외판원 순회
*/

public class Main {
	static final int INF = 100000000;
	static int n; 
	static int[][] map, dp; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		dp = new int[n][(1 << n) - 1];
		for (int i = 0; i < n; i++)
			Arrays.fill(dp[i], INF);

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int cost = Integer.parseInt(st.nextToken());
				map[i][j] = cost;
			}
		}
		System.out.println(dfs(0, 1));

	}

	public static int dfs(int node, int visit) {

		if (visit == (1 << n) - 1) {	
			if (map[node][0] == 0)
				return INF;
			return map[node][0];
		}
		if (dp[node][visit] != INF) {	
			return dp[node][visit];
		}
		for (int i = 0; i < n; i++) {
			if ((visit & (1 << i)) == 0 && map[node][i] != 0) {
				dp[node][visit] = Math.min(dp[node][visit], dfs(i, visit | (1 << i)) + map[node][i]);
			}
		}
		return dp[node][visit];
	}
}