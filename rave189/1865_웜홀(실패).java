package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static int INF = 2000000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int[] distance = new int[n];
			Arrays.fill(distance, INF);
			distance[0] = 0;
			LinkedList<Point> roads = new LinkedList<>();
			LinkedList<Point> wormhole = new LinkedList<>();
			while (m-- > 0) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken()) - 1;
				int end = Integer.parseInt(st.nextToken()) - 1;
				int weight = Integer.parseInt(st.nextToken());
				roads.add(new Point(start, end, weight));
			}
			while (w-- > 0) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken()) - 1;
				int end = Integer.parseInt(st.nextToken()) - 1;
				int weight = Integer.parseInt(st.nextToken());
				wormhole.add(new Point(start, end, weight));
				wormhole.add(new Point(end, start, weight));
			}

			for (int i = 1; i <= n; i++) {
				for (Point cur : roads) {
					if (distance[cur.x] + cur.weight < distance[cur.y])
						distance[cur.y] = distance[cur.x] + cur.weight;
				}
				for (Point cur : wormhole)
					if (distance[cur.x] - cur.weight < distance[cur.y])
						distance[cur.y] = distance[cur.x] - cur.weight;
			}
			boolean isCycle = false;
			for (int v : distance)
				if (v < 0) {
					isCycle = true;
					break;
				}
			answer.append(isCycle ? "YES" : "NO").append('\n');
		}
		System.out.print(answer);
	}
}

class Point {
	int x, y, weight;

	public Point(int x, int y, int weight) {
		this.x = x;
		this.y = y;
		this.weight = weight;
	}
}