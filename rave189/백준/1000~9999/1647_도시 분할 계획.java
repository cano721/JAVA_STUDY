package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * N개의 집과 M개의 도로가 있는 마을이 있다.
 * 이 마을의 도로를 하나 없애 두 개의 마을로 분리하려고 한다.
 * 또한 도로의 유지비가 많이 나와 임의의 두 집 사이에 최소 비용의 도로가 존재하도록 하고
 * 나머지는 모두 없애려고 한다.
 * 이때, 들어가는 유지비의 합을 구하는 문제
 * @author Rave
 *
 */
public class Main {

	static int[] unionFind;

	/**
	 * 크루스칼(1800ms정도), 프림(4000ms)을 사용하여 해결할 수 있다.
	 * 프림은 어딘가 잘못 구현한 듯 한데 잘 모르겠음.
	 * 
	 * 크루스칼 알고리즘
	 * 모든 간선을 유지비가 적은 순서로 정렬한다.
	 * 이후 하나씩 꺼내며 사이클이 생성되지 않는 간선만 추가한다.
	 * 사이클은 유니온 파인드를 사용해 같은 부모를 가진 간선은 추가하지 않는다.
	 * 새로운 간선을 이어붙여야 하는 것이므로 부모가 같은 간선은 사이클을 생성하게 된다.
	 * 
	 * 프림 알고리즘
	 * 하나의 정점을 기준으로 잡고 탐색한 정점들과 인접한 간선 중
	 * 방문하지 않았으면서 가장 유지비가 적은 간선을 택한다.
	 * 탐색을 하면서 유지비가 가장 큰 도로의 값을 저장한다.
	 * 이후 유지비가 가장 큰 도로를 제거해 마을을 분리한다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		unionFind = new int[n];
		for (int i = 1; i < n; i++)
			unionFind[i] = i;
		PriorityQueue<Road> pq = new PriorityQueue<>();
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			pq.add(new Road(x, y, cost));
			pq.add(new Road(y, x, cost));
		}
		int answer = 0;
		while (n > 2) {
			Road cur = pq.poll();
			if (union(cur.x, cur.y)) {
				answer += cur.cost;
				n--;
			}
		}
		System.out.println(answer);
	}

	public static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x != y) {
			unionFind[y] = x;
			return true;
		}
		return false;
	}

	public static int find(int x) {
		if (x == unionFind[x])
			return x;
		return unionFind[x] = find(unionFind[x]);
	}
}

class Road implements Comparable<Road> {
	int x, y, cost;

	public Road(int x, int y, int cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
	}

	@Override
	public int compareTo(Road o) {
		return cost - o.cost;
	}
}