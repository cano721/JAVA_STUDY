package 유형별문제풀이.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *  1은 1   					: 1개
 *  2은 1+1, 2   				: 2개
 *  3은 1+1+1, 1+2, 2+1, 3   : 4개
 * 
 * 	dp[n] = dp[n-1] + dp[n-2] + dp[n-3]
 * 
 */

public class BJ15988_123더하기3 {

	static long dp[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for (int i = 1; i <= t; i++) {
			
			int n = Integer.parseInt(br.readLine());
			
			dp = new long[1000001];
			
			//초기화
			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;
			
			if(n > 3) {
				for (int j = 4; j <=n; j++) {
					dp[j] = (dp[j-1] + dp[j-2] + dp[j-3]) % 1000000009;
				}
				
			}
			
			System.out.println(dp[n]);
			
		}

	}

}
