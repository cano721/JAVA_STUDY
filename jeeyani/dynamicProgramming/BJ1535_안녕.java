package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
 * dp[power] : power(소비한 체력)에 따른 최대기쁨
 * 
 * dp[power] = Math.max(dp[power], dp[power--]+happy[n])
 * 
 * 
*/

public class BJ1535_안녕 {

	static int n;
	static int power[];
	static int happy[];
	static int dp[];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		power = new int[n+1];
		happy = new int[n+1];
		dp = new int[101];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <=n ; i++) {
			power[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <=n ; i++) {
			happy[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i < n+1; i++) {
			for (int j = 99; j >= 0; j--) {
				int temp = power[i]+j;
				
				if(temp < 100) {
					dp[temp] = Math.max(dp[temp], dp[j]+happy[i]);
				}
			}
		}
		System.out.println(dp[99]);
	}

}
