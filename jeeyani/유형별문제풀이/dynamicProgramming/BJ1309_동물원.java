package 유형별문제풀이.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 *<<1>>
 *
 *
 * 1. 사자가 왼, 오 둘다 없는 경우
 * dp[n][0] = dp[n-1][0] + dp[n-1][1] + dp[n-1][2];
 * 
 * 
 * 2. 사자가 왼쪽만 들어가 있는 경우
 * dp[n][1] = dp[n-1][0] + dp[n-1][2];
 * 
 * 
 * 3. 사자가 오른쪽만 들어가 있는 경우
 * dp[n][2] = dp[n-1][0] + dp[n-1][1];
 * 
 * 
 * 
 * <<2>>
 * 
 * n=1인 경우 1
 * n=2인 경우, 3
 * n=3인 경우, 17
 * n=4인 경우, 41
 * 
 * dp[n] = dp[n-1]*2 + dp[n-2]
 * 
 */

public class BJ1309_동물원 {

	static int n, dp[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		
		dp = new int[n+1][3];
		
		//초기화
		//사자를 한 마리도 배치하지 않는 경우도 하나의 경우의 수로 친다
		dp[1][0] = 1;
		dp[1][1] = 1;
		dp[1][2] = 1;
		
		for (int i = 2; i <=n; i++) {
			
			dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % 9901;
			dp[i][1] = dp[i-1][0] + dp[i-1][2] % 9901;
			dp[i][2] = dp[i-1][0] + dp[i-1][1] % 9901;
		
		}
		
		System.out.println((dp[n][0] + dp[n][1] + dp[n][2]) % 9901);
	}

}
