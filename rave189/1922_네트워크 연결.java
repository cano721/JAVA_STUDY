package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * ��� ��ǻ�͸� �����ϴ� ��Ʈ��ũ�� �����Ϸ��� �Ѵ�.
 * ������ ��ǻ�͸� �����ϴ� ����� �ٸ� �� �ִ�.
 * ��� ��ǻ�͸� �����ϴ� ��Ʈ��ũ�� �ּҺ���� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	static HashSet<Point>[] map;
	static boolean[] visited;

	/**
	 * �ּ� ���д� Ʈ�� ����
	 * ���� �˰����� ����Ͽ� ������ Ǭ��.
	 * 
	 * ���� ������ �ϳ� �ΰ� �����Ѵ�.
	 * ����� ��� ���� �� ���� ����� ���� ������ �ϳ� ���Ѵ�.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		map = new HashSet[n];
		visited = new boolean[n];
		for (int i = 0; i < n; i++)
			map[i] = new HashSet<>();
		while (m-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int weight = Integer.parseInt(st.nextToken());
			map[start].add(new Point(end, weight));
			map[end].add(new Point(start, weight));
		}
		PriorityQueue<Point> pq = new PriorityQueue<>();
		int answer = 0;
		pq.add(new Point(0, 0));
		while (!pq.isEmpty()) {
			Point cur = pq.poll();
			if (visited[cur.x])
				continue;
			visited[cur.x] = true;
			answer += cur.weight;
			for (Point next : map[cur.x]) {
				if (visited[next.x])
					continue;
				pq.add(next);
			}
		}
		System.out.println(answer);
	}
}

class Point implements Comparable<Point> {
	int x, weight;

	public Point(int x, int weight) {
		this.x = x;
		this.weight = weight;
	}

	@Override
	public int compareTo(Point o) {
		return weight - o.weight;
	}
}