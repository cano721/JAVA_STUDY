import java.util.*;
import java.io.*;


/**
 *   테이블 정의 :  dp[i][j] = n, i 줄의 j열 에 놓았을 때 경우의 수는 n개이다.
 *               dp[i][0] : i줄의 아무것도 안놓을 경우의 수.
 *               dp[i][1] : i줄의 1번 열에 놓을 경우의 수
 *               dp[i][2] : i줄의 2번 열에 놓을 경우의 수
 * 
 *  점화식 : dp[i][0] = dp[i-1][0] + ... + dp[i-1][2] : 이전의 줄에 어떤 경우가 오더라도 아무것도 안놓을 수 있음.
 *         dp[i][1] = dp[i-1][0] + dp[i-1][2]  : 1번 열에 놓으려면 이전에 아무것도 안놓거나 2번 열에 놓아야 가능
 *         dp[i][2] = dp[i-1][0] + dp[i-1][1] : 2번 열에 놓으려면 이전에 아무것도 안놓거나 1번 열에 놓아야 가능.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[n+1][3];
        final int MOD = 9901;
        dp[1][0] = dp[1][1] = dp[1][2]  = 1;
        
        for(int i =2; i <= n ; i++){
            dp[i][0] = ((dp[i-1][0] + dp[i-1][1])%MOD + dp[i-1][2])%MOD;
            dp[i][1] = (dp[i-1][0] + dp[i-1][2])%MOD;
            dp[i][2] = (dp[i-1][0] + dp[i-1][1])%MOD;
        }

        System.out.println( ((dp[n][0] + dp[n][1])%MOD + dp[n][2] ) % MOD );
       
    }

   
    
    
}

