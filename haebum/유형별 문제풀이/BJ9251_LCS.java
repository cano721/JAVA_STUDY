/*
    LCS 알고리즘
    
    
*/

import java.util.*;
import java.io.*;

public class BJ9251_LCS {
    public static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str1 = '0' + br.readLine();
        String str2 = '0' + br.readLine();

        int n = str1.length();
        int m = str2.length();

        dp = new int[n][m];

        for(int i = 1; i < n; i++){
            for(int j = 1; j < m; j++){
                if(str1.charAt(i) == str2.charAt(j)){
                    dp[i][j] = dp[i-1][j-1] +1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }

        System.out.println(dp[n-1][m-1]);
    }
}
