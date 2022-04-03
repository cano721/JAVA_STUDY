import java.util.*;
class Solution
{
    public int solution(int [][]board)
    {
        int answer = 0;
        int m = board.length;
        int n = board[0].length;
        int[][] dp = new int[m+1][n+1];
        for(int i =0; i< m ; i++){
            for(int j=0; j<n; j++){
                dp[i+1][j+1] = board[i][j];
            }
        }
        
        for(int i =1; i<= m ; i++){
            for(int j=1; j<=n; j++){
                if(dp[i][j] == 0) continue;
                dp[i][j] = Math.min( Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1] ) +1;
                answer = Math.max(answer, dp[i][j]);
            }
        }
        
        return answer*answer;
    }
}