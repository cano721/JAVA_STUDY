package studyGroup.may.may13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
dp

dp[i][j] 는 i와 j를 검사할 때 횡단보도 최대 갯수

3가지 경우의 수
i, j를 연결하고 i+1, j+1로 넘어가기
i를 버리고 i+1, j 로 넘어가기
j를 버리고 i, j+1로 넘어가기

https://ongveloper.tistory.com/318
 */

public class 소가길을건너는이유8구분14462 {

    static int n;
    static int[] left;
    static int[] right;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        dp = new int[n+1][n+1];
        left = new int[n+1];
        right = new int[n+1];




        for(int i = 1; i < n+1; i++)
        {
            int l = Integer.parseInt(br.readLine());
            left[i] = l;
        }

        for(int j  = 1; j < n+1; j++)
        {
            int r = Integer.parseInt(br.readLine());
            right[j] = (r);
        }

        for(int i = 1; i < n+1; i++)
        {
            for(int j = 1; j<n+1; j++)
            {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                if(Math.abs(left[i] - right[j]) <= 4)
                {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }
            }
        }

        System.out.println(dp[n][n]);


    }
}
