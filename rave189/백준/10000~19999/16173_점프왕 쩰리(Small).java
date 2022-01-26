package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * NxNũ���� ������ �ִ�.
 * �� ������ �� ĭ���� ���ڰ� �����ְ�, ������ �� ���ڸ�ŭ ������ �Ǵ� �Ʒ������� ������ �� �ִ�.
 * ������ �׻� (1, 1)���� ����ϰ� (N, N)���� ������ �Ѵ�.
 * ������ (N, N)�� �� �� �ִٸ� HaruHaru��, �ƴ϶�� Hing�� ����ϴ¹���
 * 
 * ĭ�� ���� ���ڸ�ŭ �̵��� �� �ִٰ� ���մµ�
 * �����ؾߵȴٰ� �����ؾ���.
 * 
 * @author Rave
 *
 */
public class Main {

	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 1, 0 };
	static int[] dy = { 0, 1 };

	/**
	 * �ܼ� bfs����
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		System.out.println(bfs(new Point(0, 0)));
	}

	public static String bfs(Point start) {
		Queue<Point> q = new LinkedList<>();
		q.add(start);
		visited[start.x][start.y] = true;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			if (map[cur.x][cur.y] == -1)
				return "HaruHaru";
			for (int i = 0; i < dx.length; i++) {
				int nextX = cur.x + dx[i] * map[cur.x][cur.y];
				int nextY = cur.y + dy[i] * map[cur.x][cur.y];
				try {
					if (visited[nextX][nextY])
						continue;
					visited[nextX][nextY] = true;
					q.add(new Point(nextX, nextY));
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
		}
		return "Hing";
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
}