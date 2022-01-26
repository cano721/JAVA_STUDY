package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] arr;
	static int s;
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		for (int i = 0; i < n; i++)
			bruteforce(arr[i], i + 1);
		System.out.println(answer);
	}

	public static void bruteforce(int sum, int next) {
		if (sum == s)
			answer++;
		for (int i = next; i < arr.length; i++)
			bruteforce(sum + arr[i], i + 1);
	}
}