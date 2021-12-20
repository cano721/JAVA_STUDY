package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * �� �ڸ��� ����� ���� N���� ���� �������� �Ѵ�.
 * ������ ���� x, y��ǥ�� �־�����, ���ڸ��� ����� �ּҺ���� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	static int[] unionFind;

	/**
	 * ������ ��ǥ�� �־����Ƿ� ���� �ٸ� ������� �Ÿ��� ��� ���Ͽ� �켱���� ť�� �ִ´�.
	 * �� ������ �Ÿ��� ���� ª��, ����Ŭ�� ���� �ʴ� ������ �̾��ش�.
	 * 
	 * ������ ���� �ణ �� ���ƺ��̴�(?) ����
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		unionFind = new int[n];
		for (int i = 1; i < n; i++)
			unionFind[i] = i;
		ArrayList<Point> stars = new ArrayList<>();
		PriorityQueue<Line> pq = new PriorityQueue<>();
		while (n-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			for (int i = 0; i < stars.size(); i++) {
				Point star = stars.get(i);
				double distance = getDistance(star.x - x, star.y - y);
				pq.add(new Line(i, stars.size(), distance));
				pq.add(new Line(stars.size(), i, distance));
			}
			stars.add(new Point(x, y));
		}
		int lineCnt = unionFind.length;
		double answer = 0;
		while (lineCnt > 1) {
			Line cur = pq.poll();
			if (union(cur.x, cur.y)) {
				answer += cur.cost;
				lineCnt--;
			}
		}
		System.out.println(answer);
	}

	public static double getDistance(double x, double y) {
		return Math.sqrt(x * x + y * y);
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

	public static int find(int n) {
		if (n == unionFind[n])
			return n;
		return unionFind[n] = find(unionFind[n]);
	}
}

class Point {
	double x, y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
}

class Line implements Comparable<Line> {
	int x, y;
	double cost;

	public Line(int x, int y, double cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
	}

	@Override
	public int compareTo(Line o) {
		return (int) (cost - o.cost);
	}
}