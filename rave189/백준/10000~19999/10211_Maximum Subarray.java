package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0) {
			int n = Integer.parseInt(br.readLine());
			int[] arr = new int[n + 1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++)
				arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
			int max = Integer.MIN_VALUE;
			for (int i = 1; i <= n; i++)
				for (int j = 0; j < i; j++)
					max = Math.max(max, arr[i] - arr[j]);
			answer.append(max).append('\n');
		}
		System.out.print(answer);
	}
}