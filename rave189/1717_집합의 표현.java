package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] unionFind;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		unionFind = new int[n + 1];
		for (int i = 1; i <= n; i++)
			unionFind[i] = i;
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int first = find(Integer.parseInt(st.nextToken()));
			int second = find(Integer.parseInt(st.nextToken()));
			if (command == 0)
				unionFind[second] = unionFind[first];
			else
				answer.append(unionFind[first] == unionFind[second] ? "YES" : "NO").append('\n');
		}
		System.out.println(answer);
	}

	public static int find(int n) {
		if (n == unionFind[n])
			return n;
		return unionFind[n] = find(unionFind[n]);
	}
}