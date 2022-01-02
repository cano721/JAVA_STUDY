package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 게임 회사에서 새로운 게임을 개발하려고 한다.
 * 전체 게임 시간과 균형을 조정하기 위해 모든 건물을 짓는데 걸리는 최소 시간을 구하려고 한다.
 * 건물은 선행 건물이 있어 선행 건물을 모두 지어야 건설할 수 있다.
 * 건물을 동시에 건설할 수 있을때, 건설에 필요한 최소 시간을 구하는 문제
 */
public class Main {

	static HashSet<Integer>[] map;

	/**
	 * 위상 정렬로 푸는 문제
	 * 하나의 건물에 들어오는 간선이 많기 때문에 들어오는 간선 중 최대 값만을 저장한다.
	 * 최대 값 미만의 건물들은 최대값인 건물을 짓기 전에 다 들어오기 때문이다.
	 * 그리고 자기 건물을 건설하는데 걸리는 시간을 더해주면 최소 시간을 구할 수 있다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		map = new HashSet[n + 1];
		int[] time = new int[n + 1];
		int[] degree = new int[n + 1];
		for (int i = 1; i <= n; i++)
			map[i] = new HashSet<>();
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			while (true) {
				int prev = Integer.parseInt(st.nextToken());
				if (prev == -1)
					break;
				if (!map[prev].contains(i)) {
					map[prev].add(i);
					degree[i]++;
				}
			}
		}
		Queue<Integer> q = new LinkedList<>();
		int[] result = new int[n + 1];
		for (int i = 1; i <= n; i++)
			if (degree[i] == 0)
				q.add(i);
		while (!q.isEmpty()) {
			int cur = q.poll();
			result[cur] += time[cur];
			for (int next : map[cur]) {
				result[next] = Math.max(result[next], result[cur]);
				degree[next]--;
				if (degree[next] == 0)
					q.add(next);
			}
		}
		for (int i = 1; i <= n; i++)
			answer.append(result[i]).append('\n');
		System.out.print(answer);
	}
}