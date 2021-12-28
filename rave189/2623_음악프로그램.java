package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * N명의 가수가 있고, M명의 보조 PD가 있다.
 * M명의 보조 PD는 음악 프로그램에 출현하는 가수들의 순서를 알아온다.
 * 이 순서들을 보고 프로그램 PD는 전체 가수의 순서를 만들어야 한다.
 * 만약 순서를 정할 수가 없다면 0을 출력하고, 순서를 만들 수 있다면 아무거나 출력한다.
 * @author Rave
 *
 */
public class Main {

	static HashSet<Integer>[] map;

	/**
	 * 위상 정렬을 이용하여 푸는 문제
	 * 가수들의 순서가 주어지므로, 각각을 prev와 next로 만들어 간선을 연결시켜준다.
	 * 이때, 이미 존재하는 간선이 들어올 수 있으므로 간선이 없는 경우에만 간선과 진입 차수를 추가해준다.
	 * 이후 큐가 빌때까지 정렬을 수행한다.
	 * 만약 사이클이 있는 경우 큐가 n번을 채우지 못하고 끝나므로 0을 출력한다.
	 * 아닌 경우 정렬된 값을 출력한다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] edges = new int[n + 1];
		map = new HashSet[n + 1];
		for (int i = 1; i <= n; i++)
			map[i] = new HashSet<>();
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int prev = Integer.parseInt(st.nextToken());
			while (--t > 0) {
				int next = Integer.parseInt(st.nextToken());
				if (!map[prev].contains(next)) {
					map[prev].add(next);
					edges[next]++;
				}
				prev = next;
			}
		}
		int[] result = new int[n];
		int idx = 0;
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= n; i++)
			if (edges[i] == 0)
				q.add(i);
		while (!q.isEmpty()) {
			int cur = q.poll();
			result[idx++] = cur;
			for (int next : map[cur]) {
				edges[next]--;
				if (edges[next] == 0)
					q.add(next);
			}
		}
		if (idx == n) {
			for (int v : result)
				answer.append(v).append('\n');
			System.out.print(answer);
		} else
			System.out.println(0);
	}
}