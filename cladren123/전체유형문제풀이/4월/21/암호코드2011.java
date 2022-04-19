package studyGroup.april.april21;

/*
DP문제
https://iamheesoo.github.io/blog/algo-boj2011
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 암호코드2011 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int num = 1000000;
        int n = s.length(); // 숫자의 길이

        // 시작이 0이면 잘못된 암호
        if(s.charAt(0) == '0')
        {
            System.out.println("0");
            return;
        }

        long[] dp = new long[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++)
        {
            char ch = s.charAt(i-1);
            char prev = s.charAt(i-2);

            if(ch=='0')
            {
                if(prev == '1' || prev == '2')
                {
                    dp[i] = dp[i-2]%num;
                }
                else break;
            }
            else
            {
                if(prev == '0')
                {
                    dp[i] = dp[i-1]%num;
                }
                else
                {
                    int temp = (prev - '0') * 10 + (ch - '0');
                    if(1 <= temp && temp <= 26)
                    {
                        dp[i] = (dp[i-2] + dp[i-1])%num;
                    }
                    else
                    {
                        dp[i] = dp[i-1]%num;
                    }
                }
            }
        }

        System.out.println(dp[n]%num);
    }
}
