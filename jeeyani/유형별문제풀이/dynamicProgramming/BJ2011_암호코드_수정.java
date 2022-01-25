package 유형별문제풀이.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2011_암호코드_수정 {

	static String input;
	static int arr[];
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
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		input = st.nextToken();
		int len = input.length();
		
		arr = new int[len+1];
		dp = new int[len+1];
		
		for (int i = 0; i < len; i++) {
			arr[i+1] = input.charAt(i) -'0';
		}
		
		dp[0] = 1;
		
		for (int i = 1; i <=len ; i++) {
			
			//1.자연수일 경우
			if(arr[i] > 0) {
				//dp[i]= dp[i-1];
				dp[i] = (dp[i] + dp[i - 1]) % 1000000;
			}
			
			//2. 두자리 수가 10~26일 경우
			int temp = arr[i] + arr[i - 1] * 10;
			if(temp >= 10 && temp <=26) {
				dp[i]= (dp[i]+dp[i-2]) % 1000000;
			}	
			
		}
		//dp[0]은 1로 초기화를 시켜줬음으로 모순된 값이다.
		//따라서 1인 값이 아닌 0이 나오도록 예외처리
		System.out.println(dp[len]);
		

	}

}
