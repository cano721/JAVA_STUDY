import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[] arr;
	static int[][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n + 1];
		dp = new int[n + 1][n +1];
		
		if(m != 0) {
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < m; i++) {
				int index = Integer.parseInt(st.nextToken());
				arr[index] = -1;
			}
		}
		
		for(int i = 0; i < n + 1; i++) {
			for(int j = 0; j < n +1; j++) {
				dp[i][j] = -1;
			}
		}
		
		int answer = solve(1, 0);
		System.out.println(answer);
	}
	
	static int solve(int d, int c) {
		if(d > n) return 0;
		
		if(dp[d][c] != -1) return dp[d][c];
		
		dp[d][c] = 999999999;

		if(arr[d] == -1) {
			dp[d][c] = Math.min(dp[d][c], solve(d + 1, c));
		}
		
		dp[d][c] = Math.min(dp[d][c], solve(d + 5, c + 2) + 37000);
		dp[d][c] = Math.min(dp[d][c], solve(d + 3, c + 1) + 25000);
		dp[d][c] = Math.min(dp[d][c], solve(d + 1, c) + 10000);
		
		if(c >= 3) {
			dp[d][c] = Math.min(dp[d][c], solve(d + 1, c - 3));
		}
		return dp[d][c];
	}
}