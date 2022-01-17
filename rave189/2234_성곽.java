package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * � ������ ���� ��ΰ� �ִ�.
 * ������ �ѷ����� ���� ���̴�.
 * �� ���� ���� ���� ������ �ϳ��� ������ �־�����.
 * ���ʿ� ���� ������ 1, ������ 2, ������ 4, ������ 8�� ���� ���� ������ �־�����.
 * �̶�, �� ���� �ִ� ���� ����, ���� ���� ���� ����, �ϳ��� ���� �����Ͽ� ���� �� �ִ� ���� ���� ���� ũ�⸦ ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	static final int[] DX = { 0, -1, 0, 1 };
	static final int[] DY = { -1, 0, 1, 0 };
	static int[][] map;
	static boolean[][] visited;

	/**
	 * bfs�� bruteforce�� ����Ͽ� ������ ǰ
	 * ��� �ð��� ���� �ɸ�
	 * 
	 * ���� ��ȣ ���̱� ó�� �� ���� indexȭ�ϰ�, index�� �ٸ� �κ��� ������ ���� ���� ���� ���ϴ� ����� �ξ� ����
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		int max = 0;
		int count = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (!visited[i][j]) {
					count++;
					max = Math.max(max, getAreaSize(new Point(i, j)));
				}
		System.out.println(count);
		System.out.println(max);
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				for (int t = 0; t < DX.length; t++) {
					if ((map[i][j] & (1 << t)) == (1 << t)) {
						visited = new boolean[n][m];
						int wall = (int) Math.pow(2, t);
						map[i][j] -= wall;
						max = Math.max(max, getAreaSize(new Point(i, j)));
						map[i][j] += wall;
					}
				}
		System.out.println(max);
	}

	public static int getAreaSize(Point start) {
		Queue<Point> q = new LinkedList<>();
		int count = 0;
		q.add(start);
		visited[start.x][start.y] = true;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			count++;
			for (int i = 0; i < DX.length; i++) {
				if ((map[cur.x][cur.y] & (1 << i)) == (1 << i))
					continue;
				int nextX = cur.x + DX[i];
				int nextY = cur.y + DY[i];
				try {
					if (visited[nextX][nextY])
						continue;
					q.add(new Point(nextX, nextY));
					visited[nextX][nextY] = true;
				} catch (ArrayIndexOutOfBoundsException e) {
					return -1;
				}
			}
		}
		return count;
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}