package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] arr = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++)
			arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
		int max = Integer.MIN_VALUE;
		for (int i = 1; i <= n; i++)
			try {
				max = Math.max(max, arr[i] - arr[i - k]);
			} catch (ArrayIndexOutOfBoundsException e) {
			}
		System.out.println(max);
	}
}