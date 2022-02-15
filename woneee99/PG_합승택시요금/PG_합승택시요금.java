import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class PG_합승택시요금 {
	ArrayList<Node>[] arr;
	int[] dist;

	public int solution(int n, int s, int a, int b, int[][] fares) {
		arr = new ArrayList[n + 1];
		dist = new int[n + 1];
		for (int i = 0; i <= n; i++)
			arr[i] = new ArrayList<>();

		for (int i = 0; i < fares.length; i++) {
			int start = fares[i][0];
			int end = fares[i][1];
			int cost = fares[i][2];

			arr[start].add(new Node(end, cost));
			arr[end].add(new Node(start, cost));
		}
		int[] startA = new int[n + 1];
		int[] startB = new int[n + 1];
		int[] start = new int[n + 1];

		Arrays.fill(startA, Integer.MAX_VALUE);
		Arrays.fill(startB, Integer.MAX_VALUE);
		Arrays.fill(start, Integer.MAX_VALUE);

		startA = cal(a, startA);
		startB = cal(b, startB);
		start = cal(s, start);

		int answer = Integer.MAX_VALUE;
		for (int i = 1; i <= n; i++) {
			answer = Math.min(answer, startA[i] + startB[i] + start[i]);
		}
		return answer;
	}

	int[] cal(int start, int[] cost) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(start, 0));
		cost[start] = 0;

		while (!q.isEmpty()) {
			Node node = q.poll();

			if (node.cost > cost[node.idx])
				continue;

			for (int i = 0; i < arr[node.idx].size(); i++) {
				Node next = arr[node.idx].get(i);
				if (cost[node.idx] + next.cost < cost[next.idx]) {
					cost[next.idx] = cost[node.idx] + next.cost;
					q.offer(new Node(next.idx, cost[next.idx]));
				}
			}
		}
		return cost;
	}

	class Node implements Comparable<Node> {
		int idx, cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}

	}
}