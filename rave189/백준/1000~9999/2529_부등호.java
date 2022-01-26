package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static char[] op;
	static boolean[] visited = new boolean[10];
	static String min = Long.MAX_VALUE + "";
	static String max = Long.MIN_VALUE + "";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		op = new char[n];
		for (int i = 0; i < n; i++)
			op[i] = st.nextToken().charAt(0);
		for (int i = 0; i < visited.length; i++) {
			visited[i] = true;
			bruteforce(i + "", i, 0);
			visited[i] = false;
		}
		System.out.println(max + "\n" + min);
	}

	public static void bruteforce(String result, int prev, int idx) {
		if (idx >= op.length) {
			long value = Long.parseLong(result);
			if (value < Long.parseLong(min))
				min = result;
			if (value > Long.parseLong(max))
				max = result;
			return;
		}
		for (int i = 0; i < visited.length; i++) {
			if (visited[i])
				continue;
			if ((op[idx] == '>' && prev > i) || (op[idx] == '<' && prev < i)) {
				visited[i] = true;
				bruteforce(result + i, i, idx + 1);
				visited[i] = false;
			}
		}
	}
}