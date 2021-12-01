package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 포도주시식과 유사한 문제
 * n, 뒤에서 부터 생각하자
 * 
 * 1. n-1를 건너뛴 경우(두 계단을 이용한 경우)
 * 2. 한 계단씩 이동한 경우
 * 
 * 
 * 1. dp[n] = dp[n-2] + stair[n];
 * 2. dp[n] = dp[n-1]
 *
 * 
 * 
 * 결국 최종 점화식은
 * dp[n] = max(dp[n-1],dp[n-2] + stair[n]);
 * 
 * 
 */

public class BJ2579_계단오르기 {

	static int n, stair[], dp[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		stair = new int[n + 1];
		dp = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			stair[i] = Integer.parseInt(br.readLine());
		}

		//초기화
		dp[1] = stair[1];
		dp[2] = stair[1] + stair[2];

		for (int i = 3; i <= n; i++) {

			dp[i] = Math.max(dp[i-1], dp[i - 2] + stair[i]);
		}

		System.out.println(dp[n]);

	}
}
