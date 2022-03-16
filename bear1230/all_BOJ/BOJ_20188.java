import java.io.*;
import java.util.*;
        
public class Main {
	static int n;
	static long answer = 0;
	static int[] dp;
	static ArrayList<Integer>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());

		list = new ArrayList[n + 1];
		dp = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}

		dfs(1);
		System.out.println(answer);
	}

	private static int dfs(int now) {
		dp[now] = 1;
		for (int next : list[now]) {
			if (dp[next] == 0) {
				dp[now] += dfs(next);
			}
		}
		if (now != 1) {
			answer += comb(n) - comb(n - dp[now]);
		}
		return dp[now];
	}

	private static long comb(int n) {
		return (long) n * (long) (n - 1) / 2;
	}

}


