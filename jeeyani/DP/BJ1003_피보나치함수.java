package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1003_피보나치함수 {

	/*
	 * dp[n][0]
	 * dp[n][1]
	 *  n 일때 0 혹은 1 호출 횟수를 계속 담아 준다
	 */
	static Integer[][] dp; //null값 비교를 위해 Integer
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		

		int t = Integer.parseInt(br.readLine());

		//N은 40보다 작거나 같은 자연수 또는 0
		dp = new Integer[41][2];
		
		dp[0][0] = 1; //n=0일때 0호출 횟수
		dp[0][1] = 0; //n=0일때 1호출 횟수
		dp[1][0] = 0; //n=1일때 0호출 횟수
		dp[1][1] = 1; //n=1일때 1호출 횟수
		
		for (int i = 1; i <= t; i++) {
			int n = Integer.parseInt(br.readLine());
			
			fibo(n);
			System.out.println(dp[n][0]+" "+dp[n][1]);
			
		}
		
	

	}

	private static Integer[] fibo(int n) {
		//n에 대한 0,1의 호출 횟수가 없을 때
		if(dp[n][0] == null || dp[n][1] == null) {
			dp[n][0] = fibo(n-1)[0] + fibo(n-2)[0]; 
			dp[n][1] = fibo(n-1)[1] + fibo(n-2)[1]; 
		}
		return dp[n];
	}

}
