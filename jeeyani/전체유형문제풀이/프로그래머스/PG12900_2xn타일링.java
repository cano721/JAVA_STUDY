package 전체유형문제풀이.프로그래머스;

import java.util.*;


/*
 * 
 * [DP문제]
 *  
 *  dp[n] = 가로길이가 n인 직사각형을 만들 수 있는 경우의 수
 *  
 *  dp[1] = 1 (1)
 *  dp[2] = 2 ((1,1),(2))
 *  
 *  dp[3] = 3 ((1,2) // (1,1,1) (2,1))
 *  
 *  "n-1일때의 경우의 수 + n-2일때의 경우의 수"
 * 
 * 
 * */


public class PG12900_2xn타일링 {

	public static void main(String[] args) {

		int n = 4;

		int result = solution(n);

		System.out.println(result);

	}

	private static int solution(int n) {
		int[] dp = new int[n + 1];

		int num = 1000000007;

		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i <= n; i++) {
			dp[i] = (dp[i - 1] % num + dp[i - 2] % num) % num;
		}

		return dp[n];
	}

}