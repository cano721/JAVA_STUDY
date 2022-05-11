package day2205.day11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_13302_리조트 {

    static int dp[][], N, M;
    static boolean isClose[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        isClose = new boolean[N + 1];
        if (M > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int no = Integer.parseInt(st.nextToken());
                isClose[no] = true;
            }
        }
        dp = new int[N + 1][N + 1]; //dp[i][j] = i날에 j개의 쿠폰으로 가져갈 수 있는 최소 비용
        for (int i = 0; i < N+1; i++) {
            Arrays.fill(dp[i], -1);
        }

        int answer = solve(1, 0);
        for (int i = 0; i < N + 1; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        System.out.println(answer);
    }

    private static int solve(int day, int coupon) {
        if (day > N) return 0;
        if (dp[day][coupon] != -1) return dp[day][coupon];
        dp[day][coupon] = Integer.MAX_VALUE;

        if (isClose[day]) return dp[day][coupon] = Math.min(solve(day + 1, coupon), dp[day][coupon]);
        else {
            if (coupon >= 3) dp[day][coupon] = Math.min(dp[day][coupon], solve(day + 1, coupon - 3));
            dp[day][coupon] = Math.min(dp[day][coupon], solve(day + 1, coupon) + 10000);
            dp[day][coupon] = Math.min(dp[day][coupon], solve(day + 3, coupon+1) + 25000);
            dp[day][coupon] = Math.min(dp[day][coupon], solve(day + 5, coupon+2) + 37000);
        }

        return dp[day][coupon];
    }
}
