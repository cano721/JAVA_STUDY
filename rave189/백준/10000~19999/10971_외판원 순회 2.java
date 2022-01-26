package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static int start;
	static boolean[] visited;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < n; i++) {
			start = i;
			visited[i] = true;
			dfs(i, 0, 1);
			visited[i] = false;
		}
		System.out.println(answer);
	}

	public static void dfs(int cur, int sum, int depth) {
		if (depth == map.length && map[cur][start] != 0) {
			answer = Math.min(answer, sum + map[cur][start]);
			return;
		}
		for (int i = 0; i < map.length; i++) {
			if (visited[i] || map[cur][i] == 0)
				continue;
			visited[i] = true;
			dfs(i, sum + map[cur][i], depth + 1);
			visited[i] = false;
		}
	}
}