package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class baekjoon20188_등산_마니아 {
	private static int N, result;
	private static int dp[];
	private static ArrayList<ArrayList<Integer>> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		list = new ArrayList<ArrayList<Integer>>();
		dp = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			list.add(new ArrayList<Integer>());
		}
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			list.get(b).add(a);
		}
		DP(1);
		System.out.println(result);
	}

	private static int DP(int now) {
		dp[now] = 1;
		for (int next : list.get(now)) {
			if (dp[next] == 0) {
				dp[now] += DP(next);
			}
		}
		if (now != 1) {
			result += comb(N) - comb(N - dp[now]);
		}
		return dp[now];
	}

	private static long comb(int n) {
		return (long) n * (long) (n - 1) / 2;
	}
}
