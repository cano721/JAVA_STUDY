package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		int length = Integer.MAX_VALUE, start = 0, end = 0;
		long sum = 0;
		while (start <= end && end < n) {
			if (sum + arr[end] < s) {
				sum += arr[end++];
			} else {
				length = Math.min(length, end + 1 - start);
				sum -= arr[start++];
			}
		}
		System.out.println(length == Integer.MAX_VALUE ? 0 : length);
	}
}