package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static Map<Integer, Integer> hash = new HashMap<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[n + 1];
		long answer = 0;
		for (int i = 1; i <= n; i++) {
			arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
			if (arr[i] == k)
				answer++;
			answer += hash.getOrDefault(arr[i] - k, 0);
			hash.put(arr[i], hash.getOrDefault(arr[i], 0) + 1);
		}
		System.out.println(answer);
	}
}