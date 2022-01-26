package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static HashSet<Integer>[] map;
	static boolean[] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		map = new HashSet[n];
		visited = new boolean[n];
		for (int i = 0; i < n; i++)
			map[i] = new HashSet<>();
		int m = Integer.parseInt(br.readLine());
		while (m-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken()) - 1;
			int second = Integer.parseInt(st.nextToken()) - 1;
			map[first].add(second);
			map[second].add(first);
		}
		System.out.println(bfs(0));
	}

	public static int bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		visited[start] = true;
		int count = 0;
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int next : map[cur]) {
				if (visited[next])
					continue;
				count++;
				visited[next] = true;
				q.add(next);
			}
		}
		return count;
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}