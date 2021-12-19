package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 도시의 모든 길에는 가로등이 있다.
 * 하지만 유지비용이 굉장히 많이 들어 일부를 소등하려고 한다.
 * 단 어떤 두 집을 왕래할 때, 불이 꺼져있으면 안된다.
 * 즉 도시의 모든 두 집 쌍에 대해, 불이 켜진 길만으로 서로 왕래할 수 있어야 한다.
 * 위 조건을 지키며 절약할 수 있는 금액을 구하는 문제
 * @author Rave
 *
 */
public class Main {

	static List<Road>[] map;
	static int answer;
	static PriorityQueue<Road> pq = new PriorityQueue<>();
	static boolean[] visited;

	/**
	 * 모든 가로등의 비용을 더한다.
	 * 이후 최단 비용으로 모든 집을 왕복할 수 있는 가로등의 비용 합을 구한다.
	 * 모든 가로등의 비용에서 최단 비용을 빼는 것으로 구한다.
	 * 
	 * 프림 알고리즘으로 푸니 1356ms정도 나온다.
	 * 정답을 맞춘 분들을 보니 유니온 파인드를 사용하여 더 빠르게 정답을 구할 수 있다.
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