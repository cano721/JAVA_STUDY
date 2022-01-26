package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * N���� ���� M���� ���ΰ� �ִ� ������ �ִ�.
 * �� ������ ���θ� �ϳ� ���� �� ���� ������ �и��Ϸ��� �Ѵ�.
 * ���� ������ ������ ���� ���� ������ �� �� ���̿� �ּ� ����� ���ΰ� �����ϵ��� �ϰ�
 * �������� ��� ���ַ��� �Ѵ�.
 * �̶�, ���� �������� ���� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	static int[] unionFind;

	/**
	 * ũ�罺Į(1800ms����), ����(4000ms)�� ����Ͽ� �ذ��� �� �ִ�.
	 * ������ ��� �߸� ������ �� �ѵ� �� �𸣰���.
	 * 
	 * ũ�罺Į �˰���
	 * ��� ������ ������ ���� ������ �����Ѵ�.
	 * ���� �ϳ��� ������ ����Ŭ�� �������� �ʴ� ������ �߰��Ѵ�.
	 * ����Ŭ�� ���Ͽ� ���ε带 ����� ���� �θ� ���� ������ �߰����� �ʴ´�.
	 * ���ο� ������ �̾�ٿ��� �ϴ� ���̹Ƿ� �θ� ���� ������ ����Ŭ�� �����ϰ� �ȴ�.
	 * 
	 * ���� �˰���
	 * �ϳ��� ������ �������� ��� Ž���� ������� ������ ���� ��
	 * �湮���� �ʾ����鼭 ���� ������ ���� ������ ���Ѵ�.
	 * Ž���� �ϸ鼭 ������ ���� ū ������ ���� �����Ѵ�.
	 * ���� ������ ���� ū ���θ� ������ ������ �и��Ѵ�.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		unionFind = new int[n];
		for (int i = 1; i < n; i++)
			unionFind[i] = i;
		PriorityQueue<Road> pq = new PriorityQueue<>();
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int cost = Integer.parseInt(st.nextToken());
			pq.add(new Road(x, y, cost));
			pq.add(new Road(y, x, cost));
		}
		int answer = 0;
		while (n > 2) {
			Road cur = pq.poll();
			if (union(cur.x, cur.y)) {
				answer += cur.cost;
				n--;
			}
		}
		System.out.println(answer);
	}

	public static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x != y) {
			unionFind[y] = x;
			return true;
		}
		return false;
	}

	public static int find(int x) {
		if (x == unionFind[x])
			return x;
		return unionFind[x] = find(unionFind[x]);
	}
}

class Road implements Comparable<Road> {
	int x, y, cost;

	public Road(int x, int y, int cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
	}

	@Override
	public int compareTo(Road o) {
		return cost - o.cost;
	}
}