import java.util.*;

class Solution {
	public int solution(int N, int[][] road, int K) {
		int answer = 0;
		int[][] map = new int[N][N];
		int[] dist = new int[N];

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return dist[o1] - dist[o2];
			}
		});

		for (int i = 0; i < road.length; i++) {
			int a = road[i][0] - 1;
			int b = road[i][1] - 1;
			int w = road[i][2];

			if (map[a][b] == 0 || map[a][b] > w) {
				map[a][b] = w;
				map[b][a] = w;
			}
		}

		dist[0] = 0;
		pq.add(0);
		while (!pq.isEmpty()) {
			int cur = pq.poll();

			for (int i = 1; i < N; i++) {
				if (map[cur][i] == 0)
					continue;

				if (dist[i] == 0 || dist[i] > dist[cur] + map[cur][i]) {
					dist[i] = dist[cur] + map[cur][i];
					pq.add(i);
				}
			}
		}

		for (int d : dist)
			if (d <= K)
				answer++;

		return answer;
	}

}
