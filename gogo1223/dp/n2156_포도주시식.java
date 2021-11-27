package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n2156_포도주시식 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 

		int n = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		int[] dp = new int[n+1];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine()); 
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[1] = arr[0];
		
		if(n >= 2) dp[2] = arr[0] + arr[1];
		
		for (int i = 3; i < dp.length; i++) {
			dp[i] = Math.max(Math.max(dp[i-2]+arr[i-1], dp[i-3]+arr[i-2]+arr[i-1]), dp[i-1]);
		}
		System.out.println(dp[n]);

	}

}
