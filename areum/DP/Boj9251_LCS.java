package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    LCS 개념 다시 보기
 */
public class Boj9251_LCS {
    public static void main(String[] args) throws IOException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();

        int str1Len = str1.length();
        int str2Len = str2.length();

        int[][] dp = new int[str1Len+1][str2Len+1];
        for(int i=1; i<=str2Len; i++) {
            for(int j=1; j<=str1Len; j++) {
                if(str2.charAt(i-1) != str1.charAt(j-1)) {
                    dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j-1]+1;
                }
            }
        }

        System.out.println(dp[str2Len][str1Len]);

    }
}
