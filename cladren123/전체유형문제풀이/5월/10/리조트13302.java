package studyGroup.may.may10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://loosie.tistory.com/258

public class 리조트13302 {

    static int n; // 여름방학 일수
    static int m; // 리조트에 갈 수 없는 일수
    static boolean[] holiday; // 리조트에 갈 수 없는 날짜
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        holiday = new boolean[n + 1];

        if(m > 0)
        {
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < m; i++)
            {
                holiday[Integer.parseInt(st.nextToken())] = true;
            }
        }


        dp = new int[n+1][n+1];
        for(int i = 0; i < n+1; i++)
        {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(solve(1, 0));

    }

    // top down 방식
    public static int solve(int day, int coupon)
    {
        // 주어진 날짜보다 크면 바로 return
        if(day > n) return 0;

        // 값이 이미 들어와 있으면 값을 리턴
        if(dp[day][coupon] != -1) return dp[day][coupon];

        // Math.min을 하기 위한 최고값을 배치
        dp[day][coupon] = Integer.MAX_VALUE;

        // 휴일이라면 다음 차례로 넘어간다.
        if(holiday[day])
        {
            return dp[day][coupon] = Math.min(dp[day][coupon], solve(day + 1, coupon));
        }
        // 휴일이 아니라면
        else
        {
            // 쿠폰이 3개인 경우 공짜로 이용
            if(coupon >= 3)
            {
                dp[day][coupon] = Math.min(dp[day][coupon], solve(day+1, coupon-3));
            }

            // 각 이용권에 따른 값을 추가
            dp[day][coupon] = Math.min(dp[day][coupon], solve(day+1, coupon) + 10000);
            dp[day][coupon] = Math.min(dp[day][coupon], solve(day+3, coupon+1) + 25000);
            dp[day][coupon] = Math.min(dp[day][coupon], solve(day+5, coupon+2) + 37000);
        }

        return dp[day][coupon];

    }

}
