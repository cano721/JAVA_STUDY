package 전체유형문제풀이.프로그래머스;

import java.util.*;

/*
[DP]

dp[i][j] = dp[i-1][j] + dp[i][j-1];

:(i,j)로 오는 최단경로의 수

- 웅덩이의 위치인 경우 값을 계산하지 않는다!


*/

public class PG42898_등굣길 {

	
	public static void main(String[] args) {

		int[][] puddles = {{2,2}};

		int result = solution(4,3,puddles);

		System.out.println(result);

	}
	
	static final int mod = 1000000007;
	
	
	public static int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        int[][] dp = new int[n+1][m+1];
        //초기화
        for(int[] p : puddles){
            dp[p[1]][p[0]] = -1;
        }
        dp[1][1] = 1;
        
        for(int i=1;i<n+1;i++){
            for(int j=1; j<m+1; j++){
                //웅덩이면 계산을 무시
                if(dp[i][j] == -1) continue;
                
                if(dp[i-1][j] > 0) dp[i][j] += dp[i-1][j] % mod;
                if(dp[i][j-1] > 0) dp[i][j] += dp[i][j-1] % mod;

            }
        }
        
        return dp[n][m] % mod;
    }
	
	/*
	 * 
	 * 효율성 테스트 3/10
	 * */
	/*public static int solution(int m, int n, int[][] puddles) {
		int[][] dp = new int[n][m];
	    //초기화
	    for(int[] p : puddles){
	        dp[p[1]-1][p[0]-1] = -1;
	    }
	    dp[0][0] = 1;
	    
	    for(int i=0;i<n;i++){
	        for(int j=0; j<m; j++){
	            //웅덩이면 계산을 무시
	            if(dp[i][j] == -1) continue;
	            
	            if(i != 0){
	                if(dp[i-1][j] > 0) dp[i][j] += dp[i-1][j] % mod;
	            }
	            if(j !=0){
	                if(dp[i][j-1] > 0) dp[i][j] += dp[i][j-1] % mod;
	            }
	        }
	    }
	    
	    return dp[n-1][m-1];
	}*/
	
	

}