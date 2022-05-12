package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class baekjoon13302_리조트 {
	private static int dp[][], N, M;
	private static boolean map[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N + 1];
		if (M > 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				map[Integer.parseInt(st.nextToken())] = true;
			}
		}
		dp = new int[N + 1][N + 1];
		for (int i = 0; i < N + 1; i++) {
			Arrays.fill(dp[i], -1);
		}

		System.out.println(solve(1, 0));

	}

	static int solve(int day, int coupon) {

		if (day > N)
			return 0;

		if (dp[day][coupon] != -1)
			return dp[day][coupon];

		dp[day][coupon] = Integer.MAX_VALUE;
		if (map[day]) {
			return dp[day][coupon] = Math.min(dp[day][coupon], solve(day + 1, coupon));
		} else {
			if (coupon >= 3) {
				dp[day][coupon] = Math.min(dp[day][coupon], solve(day + 1, coupon - 3));
			}
			dp[day][coupon] = Math.min(dp[day][coupon], solve(day + 1, coupon) + 10000);
			dp[day][coupon] = Math.min(dp[day][coupon], solve(day + 3, coupon + 1) + 25000);
			dp[day][coupon] = Math.min(dp[day][coupon], solve(day + 5, coupon + 2) + 37000);

		}

		return dp[day][coupon];
	}
}