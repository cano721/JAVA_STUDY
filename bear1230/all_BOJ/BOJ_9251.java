/*
DP
9251 - LCS
*/

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();
        
        int dp[][] = new int[1001][1001];
        
        int n = str1.length();
        int m = str2.length();
        
        for(int i = 1; i <= n; i++){
            char c1 = str1.charAt(i - 1);
            for(int j = 1; j <= m; j++){
                char c2 = str2.charAt(j - 1);
                if(c1 == c2){
                    dp[i][j] = dp[i-1][j-1] +1;
                }else{
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        
        }
       System.out.println(dp[n][m]);   
    }
}