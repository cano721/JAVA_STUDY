package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * ���� ȸ�翡�� ���ο� ������ �����Ϸ��� �Ѵ�.
 * ��ü ���� �ð��� ������ �����ϱ� ���� ��� �ǹ��� ���µ� �ɸ��� �ּ� �ð��� ���Ϸ��� �Ѵ�.
 * �ǹ��� ���� �ǹ��� �־� ���� �ǹ��� ��� ����� �Ǽ��� �� �ִ�.
 * �ǹ��� ���ÿ� �Ǽ��� �� ������, �Ǽ��� �ʿ��� �ּ� �ð��� ���ϴ� ����
 */
public class Main {

	static HashSet<Integer>[] map;

	/**
	 * ���� ���ķ� Ǫ�� ����
	 * �ϳ��� �ǹ��� ������ ������ ���� ������ ������ ���� �� �ִ� ������ �����Ѵ�.
	 * �ִ� �� �̸��� �ǹ����� �ִ밪�� �ǹ��� ���� ���� �� ������ �����̴�.
	 * �׸��� �ڱ� �ǹ��� �Ǽ��ϴµ� �ɸ��� �ð��� �����ָ� �ּ� �ð��� ���� �� �ִ�.
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