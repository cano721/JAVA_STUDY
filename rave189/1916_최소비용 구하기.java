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
		int vertex = Integer.parseInt(br.readLine());
		int edge = Integer.parseInt(br.readLine());
		map = new HashSet[vertex + 1];
		for (int i = 1; i <= vertex; i++)
			map[i] = new HashSet<>();
		while (edge-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			map[s].add(new Point(e, weight));
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
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
		System.out.println(distance[end]);
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