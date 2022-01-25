package 유형별문제풀이.dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/*
 *
 * n=1, 2*1  :1개
 * 
 * n=2, (2*1) ,(1*2)  :2개
 * 
 * n=3, n=2일때의 경우의 수 에서 + (2*1) 추가 :3개 
 * 
 * n=4, n=3일때의 경우의 수 에서 + (2*1) 추가 
 * 		n=2일때의 경우의 수                               :3+2 = 5개
 * 
 * 따라서
 * dp[n] = dp[n-1]+dp[n-2]
 * 
 * 
 */

public class BJ11726_2xn타일링 {

	static int n;
	static long[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		/*tile = new int[2][n]; */
		
		dp = new long[n+2];
		
		int value = 10007;
		
		dp[1]=1;
		dp[2]=2;
		
		for (int i = 3; i <=n; i++) {

			dp[i] = (dp[i-1] +dp[i-2])%value;
		}
		
		bw.write(dp[n] + "\n");
        bw.flush();
        bw.close();
	}

}
