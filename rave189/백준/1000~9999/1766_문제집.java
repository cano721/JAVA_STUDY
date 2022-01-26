package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * N개의 문제로 이루어진 문제집이 있다.
 * 이 문제집은 1번문제부터 N번 문제까지 난이도 순서대로 출제 되어있다.
 * 즉, 1번 문제가 가장 쉬운 문제이고, N번 문제가 가장 어려운 문제이다.
 * 이 문제들 중에는 먼저 풀면 좋은 문제가 있어 다음과 같은 조건으로 문제를 풀려고 한다.
 * 1. N개의 문제는 모두 풀어야 한다.
 * 2. 먼저 푸는 것이 좋은 문제가 있는 문제는 먼저 푸는 것이 좋은 문제를 반드시 먼저 풀어야 한다.
 * 3. 가능하면 쉬운 문제부터 풀어야 한다.
 * 문제를 푸는 순서를 구하는 문제
 * @author Rave
 *
 */
public class Main {

	static HashSet<Integer>[] map;

	/**
	 * 위상 정렬을 통하여 정렬을 수행한다.
	 * 이때, 큐가 아닌 우선순위 큐를 사용하여 쉬운 문제가 앞으로 나오도록 한다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] degree = new int[n + 1];
		map = new HashSet[n + 1];
		for (int i = 1; i <= n; i++)
			map[i] = new HashSet<>();
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			if (!map[first].contains(second)) {
				map[first].add(second);
				degree[second]++;
			}
		}
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 1; i <= n; i++)
			if (degree[i] == 0)
				pq.add(i);
		while (!pq.isEmpty()) {
			int cur = pq.poll();
			answer.append(cur).append(' ');
			for (int next : map[cur]) {
				degree[next]--;
				if (degree[next] == 0)
					pq.add(next);
			}
		}
		System.out.print(answer);
	}
}