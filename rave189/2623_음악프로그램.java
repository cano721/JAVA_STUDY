package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * N���� ������ �ְ�, M���� ���� PD�� �ִ�.
 * M���� ���� PD�� ���� ���α׷��� �����ϴ� �������� ������ �˾ƿ´�.
 * �� �������� ���� ���α׷� PD�� ��ü ������ ������ ������ �Ѵ�.
 * ���� ������ ���� ���� ���ٸ� 0�� ����ϰ�, ������ ���� �� �ִٸ� �ƹ��ų� ����Ѵ�.
 * @author Rave
 *
 */
public class Main {

	static HashSet<Integer>[] map;

	/**
	 * ���� ������ �̿��Ͽ� Ǫ�� ����
	 * �������� ������ �־����Ƿ�, ������ prev�� next�� ����� ������ ��������ش�.
	 * �̶�, �̹� �����ϴ� ������ ���� �� �����Ƿ� ������ ���� ��쿡�� ������ ���� ������ �߰����ش�.
	 * ���� ť�� �������� ������ �����Ѵ�.
	 * ���� ����Ŭ�� �ִ� ��� ť�� n���� ä���� ���ϰ� �����Ƿ� 0�� ����Ѵ�.
	 * �ƴ� ��� ���ĵ� ���� ����Ѵ�.
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