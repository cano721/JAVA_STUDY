package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * N���� �л����� Ű ������� �ټ������ �Ѵ�.
 * ������ Ű�� �� ���� ���� �� �л��� Ű�� ���ϴ� ����� ����Ѵ�.
 * ���� ��� �л��� ���ϴ� ���� �ƴ� �Ϻ� �л��� Ű���� ���Ѵ�.
 * �Ϻ� �л��� Ű�� ���� ����� �־��� �� ��� �л����� �� ����� ����
 * ���� ���� ���� ��� �ƹ��ų� ����Ѵ�.
 * @author Rave
 *
 */
public class Main {

	static HashSet<Integer>[] map;

	/**
	 * ���� ������ �̿��ϴ� ����
	 * ������ ���� ������� ������ �� �� �ִ�.
	 * 1. ���� ������ 0(������ ������ �ϳ��� ���� ���)�� ������ ť�� �ְ�, ������ ����� ������ �����صд�..
	 * 2. ť���� �ϳ��� �� �ش� ���� ������ ��� �����Ѵ�.
	 * 2-1. ������ �����ϸ鼭 ����� ����� ������ 1�� ���ΰ�.
	 * 2-2. ���� ����� ������ 0�� �Ǹ� ť�� �����Ѵ�.
	 * 3. ť�� ���Ұ� �ϳ��� ���� ������ 2���� �ݺ��Ѵ�.
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
		// �ʱ� ���� ������ 0�� ��带 ť�� �����Ѵ�.
		for (int i = 1; i <= n; i++)
			if (edges[i] == 0)
				q.add(i);
		// ť�� �� ������ ����
		while (!q.isEmpty()) {
			// �ϳ� ���� �Ϸ�
			int cur = q.poll();
			answer.append(cur).append(' ');
			// ����� ���� Ž��
			for (int next : map[cur]) {
				// cur�� ������ ���ֹǷ� ������ �ϳ��� ����
				edges[next]--;
				// ���� ���� 0�̸� ť�� �߰�
				if (edges[next] == 0)
					q.add(next);
			}
		}
		System.out.print(answer);
	}
}