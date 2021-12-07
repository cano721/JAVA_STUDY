package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static HashSet<Point>[] map;
	static int INF = 1000000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int vertex = Integer.parseInt(st.nextToken());
		int edge = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());
		map = new HashSet[vertex + 1];
		for (int i = 1; i <= vertex; i++)
			map[i] = new HashSet<>();
		while (edge-- > 0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			map[s].add(new Point(e, weight));
		}
		int[] distance = new int[vertex + 1];
		Arrays.fill(distance, INF);
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(new Point(start, 0));
		distance[start] = 0;
		while (!pq.isEmpty()) {
			Point cur = pq.poll();
			for (Point next : map[cur.end]) {
				if (distance[cur.end] + next.weight < distance[next.end]) {
					distance[next.end] = distance[cur.end] + next.weight;
					pq.add(new Point(next.end, distance[next.end]));
				}
			}
		}
		for (int i = 1; i < distance.length; i++)
			answer.append(distance[i] == INF ? "INF" : distance[i]).append('\n');
		System.out.println(answer);
	}
}

class Point implements Comparable<Point> {
	int end, weight;

	public Point(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Point o) {
		return weight - o.weight;
	}
}