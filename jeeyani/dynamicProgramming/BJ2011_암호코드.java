package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2011_암호코드 {

	static String input;
	static int dp[];
	
	/*
	 * dp[n] = k
	 * n번자리까지 가능한 경우의 수의 갯수 k개
	 * 
	 * 			2  5  1  1  4
	 * 1의 자리      1  1  2  2  4
	 * 10의 자리    0  1  0  2  2
	 *  DP      1  2  2  4  6
	 *  
	 *  1. 0이 아닌 자연수의 경우 : dp[n-1]
	 *  2. 두자리 수가 10~26이하 일 경우  : dp[n-2]
	 *  
	 *  
	 */
	
	/*런타임에러*/
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		input = br.readLine();
		int len = input.length();
        
		dp = new int[len+1];
		dp[0] = dp[1] = 1;
		
		
		for (int i = 1; i <=len ; i++) {
			
			//현재위치
			int now = i-1;
			
			//1.자연수일 경우
			if(input.charAt(now) > '0') {
				dp[i]= (dp[i]+dp[i-1]) % 1000000;
			}
			
			//2. 두자리 수가 10~26일 경우
			int temp = (input.charAt(now - 1) - '0') * 10 + (input.charAt(now) - '0');
			if(temp >= 10 && temp <=26) {
				dp[i]= (dp[i]+dp[i-2]) % 1000000;
			}
			
		}
		//dp[0]은 1로 초기화를 시켜줬음으로 모순된 값이다.
		//따라서 1인 값이 아닌 0이 나오도록 예외처리
		System.out.println(input.equals("0") ? 0 : dp[len]);
		

	}

}
