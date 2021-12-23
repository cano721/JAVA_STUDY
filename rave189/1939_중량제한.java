package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static Map<Integer, Integer>[] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int right = 0;
		map = new Map[n];
		for (int i = 0; i < n; i++)
			map[i] = new HashMap<>();
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			right = Math.max(right, weight);
			if (map[x].getOrDefault(y, 0) < weight)
				map[x].put(y, weight);
			if (map[y].getOrDefault(x, 0) < weight)
				map[y].put(x, weight);
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken()) - 1;
		int end = Integer.parseInt(st.nextToken()) - 1;
		int left = 0;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (bfs(start, end, mid))
				left = mid + 1;
			else
				right = mid - 1;
		}
		System.out.println(left - 1);
	}

	public static boolean bfs(int start, int end, int weight) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[map.length];
		q.add(start);
		visited[start] = true;
		while (!q.isEmpty()) {
			int cur = q.poll();
			if (cur == end)
				return true;
			for (int next : map[cur].keySet()) {
				if (visited[next] || map[cur].get(next) < weight)
					continue;
				q.add(next);
				visited[next] = true;
			}
		}
		return false;
	}
}