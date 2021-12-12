package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * N���� ���ð� �ִ�.
 * �� ���ÿ��� M���� ���ο� W���� ��Ȧ�� �ִ�.
 * �� ���ÿ��� �ٸ� ���÷� ���θ� �̿��ϸ� �Ÿ���ŭ �ð��� �����Ѵ�.
 * ������ ��Ȧ�� �̿��ϸ� �ð��� �پ���.
 * �� ��, ��� �� �������� ����Ͽ� �ٽ� ���� �������� ���ƿ��� ��,
 * �ð��� �ǵ��ư� ��찡 �ִ��� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	/**
	 * ���� ���� �˰����� �̿��ؼ� Ǫ�� ����
	 * distance�� ���� ���������� �ϳ��� �߿����� �ʴ�.
	 * ���� ��� ������ n����ŭ Ž���Ͽ� ���� ����Ŭ�� �����ϴ����� Ȯ���ϸ� �ȴ�.
	 * ������ ���� ������ ����Ŭ�� ������ �ʴ� ��� ������������ ���ŵ��� �ʰ�, ���������� ���ŵ��� �����Ƿ� ���� ������ ���ŵ��� �ʴ´�.
	 * ���� ����Ŭ�� ��� ���� ������ �۾��� ���̰�, ���� ������ �۾��� ���̱⿡ ������ �ǹǷ� �Ǻ��� �����ϴ�.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int[] distance = new int[n];
			LinkedList<Point> roads = new LinkedList<>();
			while (m-- > 0) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken()) - 1;
				int end = Integer.parseInt(st.nextToken()) - 1;
				int weight = Integer.parseInt(st.nextToken());
				roads.add(new Point(start, end, weight));
				roads.add(new Point(end, start, weight));
			}
			while (w-- > 0) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken()) - 1;
				int end = Integer.parseInt(st.nextToken()) - 1;
				int weight = Integer.parseInt(st.nextToken());
				roads.add(new Point(start, end, -weight));
			}
			boolean isCycle = false;
			loop: for (int i = 1; i <= n; i++) {
				for (Point cur : roads) {
					if (distance[cur.x] + cur.weight < distance[cur.y]) {
						distance[cur.y] = distance[cur.x] + cur.weight;
						if (i == n) {
							isCycle = true;
							break loop;
						}
					}
				}
			}
			answer.append(isCycle ? "YES" : "NO").append('\n');
		}
		System.out.print(answer);
	}
}

class Point {
	int x, y, weight;

	public Point(int x, int y, int weight) {
		this.x = x;
		this.y = y;
		this.weight = weight;
	}
}