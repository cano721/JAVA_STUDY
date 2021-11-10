package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 가족과 친척간의 관계가 주어지고, 두 사람의 번호가 주어질 때, 두 사람의 촌수를 구하는 문제
 * @author Rave
 *
 */
public class Main {

	static HashSet<Integer>[] map;
	static boolean[] visited;

	/**
	 * 관계를 저장하고 bfs로 가장 빠른 촌수를 구한다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		map = new HashSet[n];
		visited = new boolean[n];
		for (int i = 0; i < n; i++)
			map[i] = new HashSet<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken()) - 1;
		int end = Integer.parseInt(st.nextToken()) - 1;
		int m = Integer.parseInt(br.readLine());
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken()) - 1;
			int second = Integer.parseInt(st.nextToken()) - 1;
			map[first].add(second);
			map[second].add(first);
		}
		System.out.println(bfs(start, end));
	}

	public static int bfs(int start, int end) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		visited[start] = true;
		int count = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				int cur = q.poll();
				if (cur == end)
					return count;
				for (int next : map[cur]) {
					if (visited[next])
						continue;
					visited[next] = true;
					q.add(next);
				}
			}
			count++;
		}
		return -1;
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}