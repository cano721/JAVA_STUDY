package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n12865_평범한배낭 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[][] arr = new int[n][2];
        
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
        	arr[i][0] = Integer.parseInt(st.nextToken());
        	arr[i][1] = Integer.parseInt(st.nextToken());
		}
        int[][] dp = new int[n+1][k+1];
        for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				if(j < arr[i-1][0]) {
					dp[i][j] = dp[i-1][j];
				}else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-arr[i-1][0]]+arr[i-1][1]);
				}
			}
		}
        
        System.out.println(dp[n][k]);
	}

}
