package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 참고)https://st-lab.tistory.com/141
 * 
 * dp[i][k] : i(i번째 가방), k(현재 가방의 무게)
 * 
 * if(i==0 || k ==0) dp[i][k] = 0
 * if(w > k) dp[i-1][k]
 * if(i<0 && w<= k) max(dp[i-1][j], dp[i-1][j-w[i]]+v[i])
 * 
 * 
 * 
 @author Jeeyani
 */

public class BJ12865_평범한배낭 {

	static int n,k,w[],v[];
	static int dp[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		w = new int[n+1];
		v = new int[n+1];
		
		for (int i = 1; i <=n; i++) {
			st = new StringTokenizer(br.readLine());
			
			w[i] = Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[n+1][k+1];
		
		//바텀업
		for (int i = 1; i <=n; i++) {
			for (int j = 1; j <=k; j++) {
			
				//더이상 담을 수 없는 경우
				if(w[i] > j) {
					dp[i][j] = dp[i-1][j];
				}
			
				else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w[i]]+v[i]);
				}
			}
		}
		System.out.println(dp[n][k]);
	}

}
