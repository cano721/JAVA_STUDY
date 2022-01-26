package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * NxMũ���� �����̿� ġ� �ִ�.
 * �� ġ��� 4�� �� 2�� �̻��� �ܺ� ����� �����ϸ� 1�ð� �Ŀ� �������.
 * ġ� ��� ���������� �ɸ��� �ð��� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {
	static int[][] map;
	static boolean[][] visited;
	static Queue<Point> q = new LinkedList<>();
	static List<Point> removeList = new LinkedList<>();
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	/**
	 * ����� bfs����
	 * 
	 * �迭 ��ü Ž���ϸ� �ð� �����ɸ��Ű��Ƽ� dfs�� �߾��µ�
	 * �ᱹ bfs�� �ٸ��� ���� �ڵ尡 ������, dfs ������ �̻������� bfs�� �ٲ㼭 ����
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					q.add(new Point(i, j));
			}
		}
		System.out.println(bfs());
	}

	public static int bfs() {
		int hour = 0;
		while (!q.isEmpty()) {
			hour++;
			isOutLine();
			int size = q.size();
			while (size-- > 0) {
				Point cur = q.poll();
				int outLineCnt = 0;
				for (int i = 0; i < dx.length; i++) {
					int nextX = cur.x + dx[i];
					int nextY = cur.y + dy[i];
					if (map[nextX][nextY] == -1)
						outLineCnt++;
				}
				if (outLineCnt < 2)
					q.add(cur);
				else
					removeList.add(cur);
			}
			remove();
		}
		return hour;
	}

	public static void isOutLine() {
		visited = new boolean[map.length][map[0].length];
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0));
		visited[0][0] = true;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			map[cur.x][cur.y] = -1;
			for (int i = 0; i < dx.length; i++) {
				int nextX = cur.x + dx[i];
				int nextY = cur.y + dy[i];
				try {
					if (visited[nextX][nextY] || map[nextX][nextY] == 1)
						continue;
					visited[nextX][nextY] = true;
					q.add(new Point(nextX, nextY));
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
		}
	}

	public static void remove() {
		for (Point cur : removeList)
			map[cur.x][cur.y] = 0;
		removeList.clear();
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}