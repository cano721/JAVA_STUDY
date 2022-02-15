import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] dp = new int[n+1][n+1];
        
        for (int i = 1 ; i <= n;i++){
            Arrays.fill(dp[i],100000000);
            dp[i][i] = 0;
        }


        for (int[] fare : fares){
            dp[fare[0]][fare[1]] = fare[2];
            dp[fare[1]][fare[0]] = fare[2];
        }
        for (int i = 1 ; i <= n; i++){
            for (int j = 1 ; j <= n;j++){
                for (int k = 1 ; k <= n;k++){
                    dp[j][k] = Integer.min(dp[j][k],dp[j][i] + dp[i][k]);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 1 ; i <= n;i++){
            ans = Integer.min(ans,dp[s][i] + dp[i][a] + dp[i][b]);
        }
        return ans;
    }
}
