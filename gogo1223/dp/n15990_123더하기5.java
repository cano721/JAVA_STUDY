package dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class n15990_123더하기5 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		
		
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, arr[i]);
		}
		long[][] dp = new long[max+1][4]; 
		dp[1][1] = 1;
		
		dp[2][2] = 1;
		
		dp[3][1] = 1;
		dp[3][2] = 1;
		dp[3][3] = 1;

		for (int i = 4; i <= max; i++) {
			dp[i][1] = (dp[i-1][2]+ dp[i-1][3])%1000000009;
			dp[i][2] = (dp[i-2][1]+ dp[i-2][3])%1000000009;
			dp[i][3] = (dp[i-3][1]+ dp[i-3][2])%1000000009;
		}
		for (int i = 0; i < n; i++) {
			long answer = (dp[arr[i]][1] + dp[arr[i]][2] + dp[arr[i]][3])%1000000009;
			bw.write(answer+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
