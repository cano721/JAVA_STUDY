package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] op = new int[4];
	static int[] arr;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < op.length; i++)
			op[i] = Integer.parseInt(st.nextToken());
		bruteforce(arr[0], 1, n - 1);
		System.out.println(max);
		System.out.println(min);
	}

	public static void bruteforce(int sum, int next, int depth) {
		if (depth == 0) {
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			return;
		}
		for (int i = 0; i < op.length; i++) {
			if (op[i] == 0)
				continue;
			op[i]--;
			int result = i == 0 ? sum + arr[next]
					: i == 1 ? sum - arr[next] : i == 2 ? sum * arr[next] : sum / arr[next];
			bruteforce(result, next + 1, depth - 1);
			op[i]++;
		}
	}
}