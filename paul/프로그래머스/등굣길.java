import java.util.*;
class Solution {
    public int solution(int m, int n, int[][] puddles) {
        
        int[][] dp = new int[n+1][m+1];
        boolean[][] map = new boolean[n+1][m+1];
        int mod = 1000000007;
        
        for(int i =0; i< puddles.length; i++){
            map[puddles[i][1]][puddles[i][0]] = true;
        }
        
        dp[1][1] = 1;
        dp[0][1] =1;
        dp[1][0] =1;
        for(int i= 2; i<=m; i++){
            if(map[1][i]) dp[1][i] = 0;
            else dp[1][i] = dp[1][i-1];
            
        }
        
        for(int i =2; i<=n; i++){
            if(map[i][1]) dp[i][1] = 0;
            else dp[i][1] = dp[i-1][1];
            
        }
        
        for(int i =2; i<=n; i++){
            for(int j = 2; j<=m; j++){
                if(map[i][j] == true) continue;
                dp[i][j] = dp[i-1][j]%mod + dp[i][j-1]%mod;
            }
        }
        
        
        return dp[n][m]%mod;
    }
}