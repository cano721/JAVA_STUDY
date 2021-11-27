package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n1535_안녕 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		
		int n = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][2];
		
		st = new StringTokenizer(br.readLine()); 
		for (int i = 0; i < n; i++) {
			arr[i][0] = Integer.parseInt(st.nextToken()); //체력
		}
		st = new StringTokenizer(br.readLine()); 
		for (int i = 0; i < n; i++) {
			arr[i][1] = Integer.parseInt(st.nextToken()); //기쁨
		}
		
		int[][] dp = new int[n][101];
		
		for (int i = 0; i < n; i++) {
			for(int j = 1; j <= 100; j++) {
				if(i == 0) dp[i][j] = 0;
				else dp[i][j] = Math.max(dp[i][j], dp[i-1][j]);
				if(j >= arr[i][0]) {
					if(i == 0) dp[i][j-arr[i][0]] = arr[i][1];
					else dp[i][j-arr[i][0]] = Math.max(dp[i][j-arr[i][0]], dp[i-1][j] + arr[i][1]);
				}			
			}			
		}
		
		int answer = 0;
		for (int i = 1; i <= 100; i++) {
			answer = Math.max(answer, dp[n-1][i]);
		}
		System.out.println(answer);
	}

}
