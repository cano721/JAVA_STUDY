package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[n + 1];
		int[] mod = new int[m];
		long answer = 0;
		for (int i = 1; i <= n; i++) {
			arr[i] = (arr[i - 1] + Integer.parseInt(st.nextToken())) % m;
			if (arr[i] == 0)
				answer++;
			answer += mod[arr[i]];
			mod[arr[i]]++;
		}
		System.out.println(answer);
	}
}