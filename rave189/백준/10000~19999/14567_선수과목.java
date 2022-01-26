package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 대학교에 개설된 모든 전공과목을 들으려고 한다.
 * 모든 과목은 매 학기 개설된다.
 * 몇몇 과목은 선수 과목이 존재하여 선수 과목을 모두 이수하여야 들을 수 있다.
 * 모든 과목에 대해 각 과목을 이수하려면 최소 몇 학기가 필요한지 구하는 문제
 * @author Rave
 *
 */
public class Main {

	static HashSet<Integer>[] map;

	/**
	 * 위상정렬을 사용하여 풀 수 있다.
	 * 위상 정렬의 진입 차수가 0인 점들의 count값이 필요한 최소 학기이다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] degree = new int[n];
		map = new HashSet[n];
		for (int i = 0; i < n; i++)
			map[i] = new HashSet<>();
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken()) - 1;
			int second = Integer.parseInt(st.nextToken()) - 1;
			if (!map[first].contains(second)) {
				map[first].add(second);
				degree[second]++;
			}
		}
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < n; i++)
			if (degree[i] == 0)
				q.add(i);
		int[] result = new int[n];
		int count = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				int cur = q.poll();
				result[cur] = count;
				for (int next : map[cur]) {
					degree[next]--;
					if (degree[next] == 0)
						q.add(next);
				}
			}
			count++;
		}
		for (int v : result)
			answer.append(v).append(' ');
		System.out.print(answer);
	}
}