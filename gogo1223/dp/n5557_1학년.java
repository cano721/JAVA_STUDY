package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n5557_1학년 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 

		int n = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];

		st = new StringTokenizer(br.readLine()); 
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int result = arr[n-1];
		
		long[] dp = new long[21];
		dp[arr[0]] = 1;
		
		for (int i = 1; i < arr.length - 1; i++) {
			long[] res = new long[21];
			for (int j = 0; j < 21; j++) {
				if(dp[j] == 0) continue;
				else {
					if(j+arr[i] >= 0 && j+arr[i] <= 20) {
						res[j+arr[i]] += dp[j];
					}
					if(j-arr[i] >= 0 && j-arr[i] <= 20) {
						res[j-arr[i]] += dp[j];
					}
				}
				
			}
			dp = res;
		}
		System.out.println(dp[result]);
	}
}
