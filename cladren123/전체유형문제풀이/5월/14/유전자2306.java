package studyGroup.may.may14;

import java.util.*;
import java.io.*;

/*

1. 가장 긴 KOI 유전자 길이 출력
2. 없을 때는 0을 출력

KOI
1. at  gc
2. aXt gXc
3. XY

dp[l][r]
l번부터 r번까지 KOI 유전자의 최대 길이

3번 규칙 -> dp[l][k] + dp[k+1][r]
2번 규칙 -> if(str[l] == a,g str[r] == t,c)면 dp[l+1][r-1] + 2

*/

public class 유전자2306 {

    static String message;
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        message = br.readLine();

        dp = new int[505][505];
        for(int i = 0; i < 500; i++)
        {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dynamic(0, message.length() - 1));

    }

    public static int dynamic(int start, int end)
    {
        if(dp[start][end] != -1)
        {
            return dp[start][end];
        }

        if(start >= end)
        {
            return 0;
        }

        int cost = 0;

        for(int k = start + 1; k < end; k++)
        {
            cost = Math.max(cost, dynamic(start, k) + dynamic(k, end));
        }

        if((message.charAt(start) == 'a' && message.charAt(end) == 't') || (message.charAt(start) == 'g' && message.charAt(end) == 'c'))
        {
            cost = Math.max(cost, dynamic(start + 1, end - 1) + 2);
        }

        return dp[start][end] = cost;
    }




}
