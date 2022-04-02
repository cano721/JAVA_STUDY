import java.util.*;
import java.io.*;

public class Main {
	static int n, m;
	static int[] cost, memory;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		memory = new int[n + 1];
		cost = new int[n + 1];
		int sum = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			memory[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			sum += cost[i];
		}

		int ans = Integer.MAX_VALUE;
		long[][] dp = new long[n + 1][sum + 1];
		
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= sum; j++) {
				if (j - cost[i] >= 0) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost[i]] + memory[i]);
				} else {
					dp[i][j] = dp[i - 1][j];
				}
				if (dp[i][j] >= m) {
					ans = Math.min(ans, j);
				}
			}
		}
		System.out.println(ans);
	}

}