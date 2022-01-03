package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * N���� ������ �̷���� �������� �ִ�.
 * �� �������� 1���������� N�� �������� ���̵� ������� ���� �Ǿ��ִ�.
 * ��, 1�� ������ ���� ���� �����̰�, N�� ������ ���� ����� �����̴�.
 * �� ������ �߿��� ���� Ǯ�� ���� ������ �־� ������ ���� �������� ������ Ǯ���� �Ѵ�.
 * 1. N���� ������ ��� Ǯ��� �Ѵ�.
 * 2. ���� Ǫ�� ���� ���� ������ �ִ� ������ ���� Ǫ�� ���� ���� ������ �ݵ�� ���� Ǯ��� �Ѵ�.
 * 3. �����ϸ� ���� �������� Ǯ��� �Ѵ�.
 * ������ Ǫ�� ������ ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	static HashSet<Integer>[] map;

	/**
	 * ���� ������ ���Ͽ� ������ �����Ѵ�.
	 * �̶�, ť�� �ƴ� �켱���� ť�� ����Ͽ� ���� ������ ������ �������� �Ѵ�.
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