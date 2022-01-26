package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[] sushi = new int[n];
		int[] eat = new int[d + 1];
		for (int i = 0; i < n; i++)
			sushi[i] = Integer.parseInt(br.readLine());
		int sum = 0;
		for (int i = 0; i < k; i++)
			if (eat[sushi[i]]++ == 0)
				sum++;
		int answer = eat[c] > 0 ? sum : sum + 1;
		for (int i = 0; i < n - 1; i++) {
			if (--eat[sushi[i]] == 0)
				sum--;
			if (eat[sushi[(i + k) % n]]++ == 0)
				sum++;
			answer = Math.max(answer, eat[c] > 0 ? sum : sum + 1);
		}
		System.out.println(answer);
	}
}