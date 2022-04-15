package studyGroup.april.april16;

import java.util.*;
import java.lang.*;
import java.io.*;


public class 퇴사14501 {

    static int n; // 날짜 수
    static int[] days;
    static int[] moneys;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        days = new int[n+2];
        moneys = new int[n+2];

        for(int i = 1; i < n+1; i++)
        {
            st = new StringTokenizer(br.readLine());
            days[i] = Integer.parseInt(st.nextToken());
            moneys[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n+2];

        for(int i = n; i > 0; i--)
        {
            int next = i + days[i];
            if(next > n+1)
            {
                dp[i] = dp[i+1];
            }
            else
            {
                dp[i] = Math.max(dp[i + 1], dp[next] + moneys[i]);
            }
        }

        System.out.println(dp[1]);






    }


}
