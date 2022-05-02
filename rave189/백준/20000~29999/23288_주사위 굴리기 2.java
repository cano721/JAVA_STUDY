package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * �ֻ����� (0, 0)�� �ִ�.
 * �ֻ����� �������� ������ ����.
 * 	 2
 * 4 1 3
 *   5
 *   6
 * �ֻ����� ���� ������ �������� �� ��, ��� ������ ���� ���ϴ� ����
 * �ֻ����� ������ ���� �������� ��������.
 * 1. �ֻ����� �̵� �������� �� ĭ ��������. ����, �̵� ���⿡ ĭ�� ���ٸ�, �̵� ������ �ݴ�� �� ���� �� ĭ ��������.
 * 2. �ֻ����� ������ ĭ (x, y)�� ���� ������ ȹ���Ѵ�.
 * 3. �ֻ����� �Ʒ��鿡 �ִ� ���� A�� �ֻ����� �ִ� ĭ (x, y)�� �ִ� ���� B�� ���� �̵� ������ �����Ѵ�.
 * 3-1. A > B�� ��� �̵� ������ 90�� �ð� �������� ȸ����Ų��.
 * 3-2. A < B�� ��� �̵� ������ 90�� �ݽð� �������� ȸ����Ų��.
 * 3-3. A = B�� ��� �̵� ���⿡ ��ȭ�� ����.
 * 
 * �� ĭ�� �������� �� ��� ������ �̵��� ĭ (x, y)�� �� B�� (x, y)���� �������� �������� �������� �̵��� �� �ִ� ĭ�� ���� C�� ���̴�.
 * @author Rave
 *
 */
public class Main {

	static int[][] map, scoreTable;
	static boolean[][] visited;
	static int n, m;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	/**
	 * �������� 2 �κ��� ��, 5�κ��� ���̴�. (�̰Ŷ����� 1�ð� ���� ������ ���� ���� ���)
	 * �������� �����϶�µ��� �����ϸ� ��.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		scoreTable = new int[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		makeScoreTable();
		// ��������(0123)
		int score = 0, direction = 0;
		// �� �Ʒ� ���� ������ �� ��
		int[] dice = { 1, 6, 4, 3, 2, 5 };
		Point dicePoint = new Point(0, 0);
		while (k-- > 0) {
			int nextX = dicePoint.x + dx[direction];
			int nextY = dicePoint.y + dy[direction];
			if (!isValid(nextX, nextY)) {
				direction = getOppositeDirection(direction);
				nextX = dicePoint.x + dx[direction];
				nextY = dicePoint.y + dy[direction];
			}
			dice = diceMove(dice, direction);
			dicePoint.move(nextX, nextY);
			score += scoreTable[nextX][nextY];
			if (dice[1] > map[nextX][nextY])
				direction = rotateClockWise(direction);
			else if (dice[1] < map[nextX][nextY])
				direction = rotateCounterClockWise(direction);
		}
		System.out.println(score);
	}

	public static void makeScoreTable() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (visited[i][j])
					continue;
				ArrayList<Point> points = bfs(new Point(i, j));
				for (Point cur : points)
					scoreTable[cur.x][cur.y] = map[cur.x][cur.y] * points.size();
			}
		}
	}

	public static boolean isValid(int x, int y) {
		return (0 <= x && x < n) && (0 <= y && y < m);
	}

	public static ArrayList<Point> bfs(Point start) {
		ArrayList<Point> points = new ArrayList<>();
		visited[start.x][start.y] = true;
		points.add(start);
		int type = map[start.x][start.y];
		for (int idx = 0; idx < points.size(); idx++) {
			Point cur = points.get(idx);
			for (int i = 0; i < dx.length; i++) {
				int nextX = cur.x + dx[i];
				int nextY = cur.y + dy[i];
				try {
					if (visited[nextX][nextY] || map[nextX][nextY] != type)
						continue;
					visited[nextX][nextY] = true;
					points.add(new Point(nextX, nextY));
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
		}
		return points;
	}

	public static int getOppositeDirection(int d) {
		if (d == 0)
			return 1;
		else if (d == 1)
			return 0;
		else if (d == 2)
			return 3;
		else if (d == 3)
			return 2;
		return -1;
	}

	public static int[] diceMove(int[] dice, int direction) {
		if (direction == 0)
			return new int[] { dice[2], dice[3], dice[1], dice[0], dice[4], dice[5] };
		else if (direction == 1)
			return new int[] { dice[3], dice[2], dice[0], dice[1], dice[4], dice[5] };
		else if (direction == 2)
			return new int[] { dice[4], dice[5], dice[2], dice[3], dice[1], dice[0] };
		else if (direction == 3)
			return new int[] { dice[5], dice[4], dice[2], dice[3], dice[0], dice[1] };
		return null;
	}

	public static int rotateClockWise(int d) {
		if (d == 0)
			return 2;
		else if (d == 1)
			return 3;
		else if (d == 2)
			return 1;
		else if (d == 3)
			return 0;
		return -1;
	}

	public static int rotateCounterClockWise(int d) {
		if (d == 0)
			return 3;
		else if (d == 1)
			return 2;
		else if (d == 2)
			return 0;
		else if (d == 3)
			return 1;
		return -1;
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void move(int nextX, int nextY) {
		x = nextX;
		y = nextY;
	}
}