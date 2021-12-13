package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static boolean[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		map = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				map[i][j] = st.nextToken().equals("1");
		}
		for (int t = 0; t < n; t++)
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					if (map[i][t] && map[t][j])
						map[i][j] = true;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				answer.append(map[i][j] ? 1 : 0).append(' ');
			answer.append('\n');
		}
		System.out.print(answer);
	}
}