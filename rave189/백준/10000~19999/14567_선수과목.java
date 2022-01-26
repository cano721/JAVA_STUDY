package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * ���б��� ������ ��� ���������� �������� �Ѵ�.
 * ��� ������ �� �б� �����ȴ�.
 * ��� ������ ���� ������ �����Ͽ� ���� ������ ��� �̼��Ͽ��� ���� �� �ִ�.
 * ��� ���� ���� �� ������ �̼��Ϸ��� �ּ� �� �бⰡ �ʿ����� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	static HashSet<Integer>[] map;

	/**
	 * ���������� ����Ͽ� Ǯ �� �ִ�.
	 * ���� ������ ���� ������ 0�� ������ count���� �ʿ��� �ּ� �б��̴�.
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