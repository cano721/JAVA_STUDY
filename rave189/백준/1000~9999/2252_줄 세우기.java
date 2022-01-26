package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * N명의 학생들을 키 순서대로 줄세우려고 한다.
 * 하지만 키를 잴 수가 없어 두 학생의 키를 비교하는 방법을 사용한다.
 * 또한 모든 학생을 비교하는 것이 아닌 일부 학생의 키만을 비교한다.
 * 일부 학생의 키를 비교한 결과가 주어질 때 모든 학생들을 줄 세우는 문제
 * 답이 여러 개일 경우 아무거나 출력한다.
 * @author Rave
 *
 */
public class Main {

	static HashSet<Integer>[] map;

	/**
	 * 위상 정렬을 이용하는 문제
	 * 다음과 같은 방법으로 정렬을 할 수 있다.
	 * 1. 진입 차수가 0(들어오는 간선이 하나도 없는 노드)인 노드들을 큐에 넣고, 각각의 노드의 차수를 저장해둔다..
	 * 2. 큐에서 하나를 빼 해당 노드와 간선을 모두 제거한다.
	 * 2-1. 간선을 제거하면서 연결된 노드의 차수를 1씩 줄인가.
	 * 2-2. 줄인 노드의 차수가 0이 되면 큐에 저장한다.
	 * 3. 큐의 원소가 하나도 없을 때까지 2번을 반복한다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new HashSet[n + 1];
		for (int i = 1; i <= n; i++)
			map[i] = new HashSet<>();
		int[] edges = new int[n + 1];
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			map[first].add(second);
			edges[second]++;
		}
		Queue<Integer> q = new LinkedList<>();
		// 초기 진입 차수가 0인 노드를 큐에 저장한다.
		for (int i = 1; i <= n; i++)
			if (edges[i] == 0)
				q.add(i);
		// 큐가 빌 때까지 실행
		while (!q.isEmpty()) {
			// 하나 정렬 완료
			int cur = q.poll();
			answer.append(cur).append(' ');
			// 연결된 노드들 탐색
			for (int next : map[cur]) {
				// cur의 간선을 없애므로 차수를 하나씩 줄임
				edges[next]--;
				// 줄인 값이 0이면 큐에 추가
				if (edges[next] == 0)
					q.add(next);
			}
		}
		System.out.print(answer);
	}
}