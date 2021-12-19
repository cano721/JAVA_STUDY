package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * ������ ��� �濡�� ���ε��� �ִ�.
 * ������ ��������� ������ ���� ��� �Ϻθ� �ҵ��Ϸ��� �Ѵ�.
 * �� � �� ���� �շ��� ��, ���� ���������� �ȵȴ�.
 * �� ������ ��� �� �� �ֿ� ����, ���� ���� �游���� ���� �շ��� �� �־�� �Ѵ�.
 * �� ������ ��Ű�� ������ �� �ִ� �ݾ��� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	static List<Road>[] map;
	static int answer;
	static PriorityQueue<Road> pq = new PriorityQueue<>();
	static boolean[] visited;

	/**
	 * ��� ���ε��� ����� ���Ѵ�.
	 * ���� �ִ� ������� ��� ���� �պ��� �� �ִ� ���ε��� ��� ���� ���Ѵ�.
	 * ��� ���ε��� ��뿡�� �ִ� ����� ���� ������ ���Ѵ�.
	 * 
	 * ���� �˰������� Ǫ�� 1356ms���� ���´�.
	 * ������ ���� �е��� ���� ���Ͽ� ���ε带 ����Ͽ� �� ������ ������ ���� �� �ִ�.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			if (n == 0 && m == 0)
				break;
			map = new List[n];
			answer = 0;
			visited = new boolean[n];
			pq.clear();
			for (int i = 0; i < n; i++)
				map[i] = new ArrayList<>();
			while (m-- > 0) {
				st = new StringTokenizer(br.readLine());
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				map[first].add(new Road(second, cost));
				map[second].add(new Road(first, cost));
				answer += cost;
			}
			pq.add(new Road(0, 0));
			while (!pq.isEmpty()) {
				Road cur = pq.poll();
				if (visited[cur.point])
					continue;
				visited[cur.point] = true;
				answer -= cur.cost;
				for (Road next : map[cur.point]) {
					if (visited[next.point])
						continue;
					pq.add(next);
				}
			}
			sb.append(answer).append('\n');
		}
		System.out.println(sb);
	}
}

class Road implements Comparable<Road> {
	int point, cost;

	public Road(int point, int cost) {
		this.point = point;
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Road [point=" + point + ", cost=" + cost + "]";
	}

	@Override
	public int compareTo(Road o) {
		return cost - o.cost;
	}
}