package 유형별문제풀이.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/* 
 * 
 * dp[n][HP] : n번째 사람에게 인사했을 때, 남은 체력이 HP일때의 최대기쁨
 
 3
 1 21 79
 20 30 25
 
 1번사람
 dp[1][99] = 20 :인사했을 때
 dp[1][100] = 0 :인사안했을 때

 2번 사람
 dp[1][78] = 50
 dp[1][79] = 30
 dp[2][78] = 50
 ...
 
 */




public class BJ1535_안녕_다른점화식 {

	static int n;
	static int power[];
	static int happy[];
	static int dp[][]= new int[21][101];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		power = new int[n+1];
		happy = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <=n ; i++) {
			power[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <=n ; i++) {
			happy[i] = Integer.parseInt(st.nextToken());
		}
		
		//초기값
		dp[1][100-power[1]] = happy[1];
		
		for (int i = 2; i < n+1; i++) {
			
			dp[i][100 - power[i]] = happy[i];
			
			for (int j = 100; j > 0; j--) {
				if (j + power[i] <= 100 && dp[i - 1][j + power[i]] != 0) {
	                // max( dp[i - 1][(남은 체력) + (i번째 인사함으로써 소모되는 체력)], dp[i - 1][남은 체력] ) 
	                dp[i][j] = Math.max(dp[i - 1][j + power[i]] + happy[i], dp[i - 1][j]);
	            } else {
	                //i번째 사람에게 인사를 안할 수도 있는 경우
	                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
	            }
			}
		}
		
		int maxHappy=0;
		for (int i = 100; i > 0; i--) {
			maxHappy = Math.max(dp[n][i], maxHappy);
	    }
		
	    System.out.println(maxHappy);
	}

}
