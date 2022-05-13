import java.io.*;
import java.util.*;

public class Main {

	static class Point {
		int number;
		int x, y, z;

		Point(int number, int x, int y, int z) {
			this.number = number;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	static class Edge implements Comparable<Edge> {
		int s, e;
		int cost;

		Edge(int s, int e, int cost) {
			this.s = s;
			this.e = e;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return cost - o.cost;
		}
	}

	static int[] parent;
	static ArrayList<Edge> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int result = 0;
		list = new ArrayList<>();

		parent = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parent[i] = i;
		}

		Point[] points = new Point[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			points[i] = new Point(i, x, y, z);
		}


		Arrays.sort(points, (p1, p2) -> p1.x - p2.x);
		for (int i = 0; i < N - 1; i++) {
			int cost = Math.abs(points[i].x - points[i + 1].x);
			list.add(new Edge(points[i].number, points[i + 1].number, cost));
		}

		Arrays.sort(points, (p1, p2) -> p1.y - p2.y);
		for (int i = 0; i < N - 1; i++) {
			int cost = Math.abs(points[i].y - points[i + 1].y);

			list.add(new Edge(points[i].number, points[i + 1].number, cost));
		}

		Arrays.sort(points, (p1, p2) -> p1.z - p2.z);
		for (int i = 0; i < N - 1; i++) {
			int cost = Math.abs(points[i].z - points[i + 1].z);

			list.add(new Edge(points[i].number, points[i + 1].number, cost));
		}

		Collections.sort(list);

		for (int i = 0; i < list.size(); i++) {
			Edge edge = list.get(i);
			if (find(edge.s) != find(edge.e)) {
				result += edge.cost;
				union(edge.s, edge.e);
			}
		}

        System.out.println(result);
	}

	public static int find(int x) {
		if (x == parent[x])
			return x;

		return parent[x] = find(parent[x]);
	}

	public static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y) {
			if (x < y) {
				parent[y] = x;
			} else {
				parent[x] = y;
			}
		}
	}
}