package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * N���� �۾��� �ִ�.
 * �۾��� ���̿��� ���� ���谡 �־, �̸� ��� ���� �۾����� �����ؾ� �Ѵ�.
 * ����, K�� �۾��� ���� ���� ���谡 �ִ� �۾��� 1�̻� K-1�����̴�.
 * ��� �۾��� �Ϸ��ϱ� ���� �ּ� �ð��� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	static HashSet<Integer>[] map;

	/**
	 * ���� ������ �̿��� ����.
	 * DP�� ���� ������.
	 * �۾��� �ɸ��� �ð��� time�� ������ edges�� �����ϸ� ��� �۾����� ���� ���踦 �����Ѵ�.
	 * ���� ���۽ð� + �ҿ�ð��� ���� ª�� ������� �켱����ť���� ���� �ּ� �ð��� ���Ѵ�.
	 * �۾��� �Ϸ�Ǵ� �ð��� ���� ���� �۾��� �տ� ���Ƿ� �׻� answer = �۾� �Ϸ� �ð��̴�.
	 * �۾��� �Ϸ�Ǿ����� �� �۾��� ������ ������ 0�� �۾��� �켱���� ť�� �ִ´�.
	 * ��� �۾��� �Ϸ����� ���� ������ �۾��� �۾� �Ϸ�ð��� ����Ѵ�.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] edges = new int[n + 1];
		int[] time = new int[n + 1];
		map = new HashSet[n + 1];
		for (int i = 1; i <= n; i++)
			map[i] = new HashSet<>();
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			int count = Integer.parseInt(st.nextToken());
			while (count-- > 0) {
				int prev = Integer.parseInt(st.nextToken());
				if (!map[prev].contains(i)) {
					map[prev].add(i);
					edges[i]++;
				}
			}
		}
		PriorityQueue<Task> pq = new PriorityQueue<>();
		int answer = 0;
		for (int i = 1; i <= n; i++)
			if (edges[i] == 0)
				pq.add(new Task(i, time[i], 0));
		while (!pq.isEmpty()) {
			Task cur = pq.poll();
			answer = cur.start + cur.time;
			for (int next : map[cur.num]) {
				edges[next]--;
				if (edges[next] == 0)
					pq.add(new Task(next, time[next], answer));
			}
		}
		System.out.println(answer);
	}
}

class Task implements Comparable<Task> {
	int num, time, start;

	public Task(int num, int time, int start) {
		this.num = num;
		this.time = time;
		this.start = start;
	}

	@Override
	public int compareTo(Task o) {
		return time + start - (o.time + o.start);
	}
}