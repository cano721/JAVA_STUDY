package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * �κ��� �ְ�, �κ����� ����� ���� �� �ִ�.
 * ����� �� ������ ������ ����.
 * 1. �κ��� ���� �ٶ󺸴� �������� 1, 2 �Ǵ� 3 ��ŭ ������ �� �ִ�.
 * 2. �κ��� ������ �����̳� ���������� �ٲ۴�.
 * �κ��� ���� ���¿� ���� ���°� �־��� ��, ���� ���¿� �������� ���� �ּ� ��� Ƚ���� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	static int[][] map;
	static boolean[][] visited;
	static Point start, end;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	/**
	 * bfs ����
	 * ó������ �������� �־������ ���� ��ġ���� ��� turn ���, ������ �� �ִ� ��� ��츦 ť�� �־��־���. (�ð� �ʰ�, �޸� �ʰ�, Ʋ�Ƚ��ϴ�)
	 * �׷��� ������ �ٲ� ��ɰ� ������� ���� �κ��� ��ġ���� 4������ ��� �˻��Ѵ�.
	 * 4���� �� �� �� �ִ� ������ �ִٸ� ���� �ٶ󺸴� ��ġ���� �����̰� ���� ��ġ�� ���� ��ȯ ��� + �����̴� ����� �����־� ť�� �־���. (�ξ� ����)
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < 2; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			if (i == 0)
				start = new Point(x, y, d, 0);
			else
				end = new Point(x, y, d, 0);
		}
		bfs();
	}

	public static void bfs() {
		PriorityQueue<Point> q = new PriorityQueue<>((v1, v2) -> v1.count - v2.count);
		q.add(start);
		visited[start.x][start.y] = true;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			if (cur.equals(end)) {
				System.out.println(cur.count + getTurnCnt(cur.direction, end.direction));
				return;
			}
			for (int i = 0; i < dx.length; i++) {
				for (int step = 1; step <= 3; step++) {
					int nextX = cur.x + dx[i] * step;
					int nextY = cur.y + dy[i] * step;
					try {
						if (map[nextX][nextY] == 1)
							break;
						else if (visited[nextX][nextY])
							continue;
						visited[nextX][nextY] = true;
						q.add(new Point(nextX, nextY, i, cur.count + getTurnCnt(cur.direction, i) + 1));
					} catch (ArrayIndexOutOfBoundsException e) {
						break;
					}
				}
			}
		}
	}

	public static int getTurnCnt(int d, int compare) {
		if (d == compare)
			return 0;
		if (d == 0)
			return compare == 1 ? 2 : 1;
		else if (d == 1)
			return compare == 0 ? 2 : 1;
		else if (d == 2)
			return compare == 3 ? 2 : 1;
		else
			return compare == 2 ? 2 : 1;
	}
}

class Point {
	int x, y, direction, count;

	public Point(int x, int y, int direction, int count) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.count = count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}