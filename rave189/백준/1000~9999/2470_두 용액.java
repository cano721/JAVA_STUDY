package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
		int start = 0, end = n - 1, min = Integer.MAX_VALUE, first = 0, second = 0;
		while (start < end) {
			int sum = arr[start] + arr[end];
			if (Math.abs(sum) < Math.abs(min)) {
				min = Math.abs(sum);
				first = arr[start];
				second = arr[end];
			}
			if (sum > 0)
				end--;
			else
				start++;
		}
		System.out.println(String.format("%d %d", first, second));
	}
}