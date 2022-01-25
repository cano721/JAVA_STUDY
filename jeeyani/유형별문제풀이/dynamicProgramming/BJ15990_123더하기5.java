package 유형별문제풀이.dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/*
 * dp[n][마지막더한수] = n을 구하고자 할때, 마지막 더한 수를  더해 나갈때 n이 나올 수 있는 경우의 수
 * 
 * 
 * dp[1][1] = 1  : 1
 * 
 * dp[2][1] = 0  
 * dp[2][2] = 1  : 2
 *
 * dp[3][1] = 1  : 2+1
 * dp[3][2] = 2  : 1+2
 * dp[3][3] = 1  : 3
 * 
 * 
 * 따라서
 *  dp[i][1] = dp[i-1][1];
 *  dp[i][2] = dp[i-2][1] + dp[i-2][2];
 *  dp[i][3] = dp[i-3][1] + dp[i-3][2] + dp[i-3][3]; (n >= 4)
 *  
 *  
 * 
 */

public class BJ15990_123더하기5 {

	static long dp[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		
		dp = new long[100001][4];
		
		//초기화
		dp[1][1] = 1; // 1
		dp[2][2] = 1; // 2
		dp[3][1] = 1; // 2+1
		dp[3][2] = 1; // 1+2
		dp[3][3] = 1; // 3

		for(int j = 4; j <= 100000; j++) {
			dp[j][1] = (dp[j-1][2] + dp[j-1][3]) % 1000000009;
			dp[j][2] = (dp[j-2][1] + dp[j-2][3]) % 1000000009;
			dp[j][3] = (dp[j-3][1] + dp[j-3][2]) % 1000000009;
		}
		
		for (int i = 1; i <= t; i++) {
			
			int n = Integer.parseInt(br.readLine());	
			System.out.println((dp[n][1]+dp[n][2]+dp[n][3])%1000000009);
			
		}
	}

}
