package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 8x8 ũ���� ü���ǿ��� ���� ���� �Ʒ� ĭ���� ���� ������ �� ĭ���� �̵��� �� �ִ��� ���ϴ� ����
 * �̵��� �� �ִٸ� 1 �ƴϸ� 0�� ����Ѵ�.
 * �̵��� ������ 1ĭ, ������ �밢�� 1ĭ, ���ڸ��̴�.
 * @author Rave
 *
 */
public class Main {

	static final int SIZE = 8;
	static char[][] map = new char[SIZE][SIZE];
	static Queue<Point> person = new LinkedList<>();
	static int[] dx = { 1, -1, 0, 0, 1, 1, -1, -1 };
	static int[] dy = { 0, 0, 1, -1, 1, -1, 1, -1 };

	/**
	 * ���� ť�� �����߾��µ�
	 * ���� ���������� �߰����༭ �� �Ʒ� �پ��ִ� ���� ��� ���ʺ��� ����� (�������� ��)
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < SIZE; i++) {
			String input = br.readLine();
			for (int j = 0; j < SIZE; j++)
				map[i][j] = input.charAt(j);
		}
		Point start = new Point(SIZE - 1, 0);
		map[start.x][start.y] = '@';
		person.add(start);
		System.out.println(escape(start));
	}

	public static int escape(Point start) {
		while (!person.isEmpty()) {
			if (movePerson())
				return 1;
			moveWall();
		}
		return 0;
	}

	public static boolean movePerson() {
		int size = person.size();
		while (size-- > 0) {
			Point cur = person.poll();
			if (cur.x == 0 && cur.y == SIZE - 1)
				return true;
			if (map[cur.x][cur.y] == '#')
				continue;
			for (int i = 0; i < dx.length; i++) {
				int nextX = cur.x + dx[i];
				int nextY = cur.y + dy[i];
				try {
					if (map[nextX][nextY] == '#' || map[nextX][nextY] == '@')
						continue;
					map[nextX][nextY] = '@';
					person.add(new Point(nextX, nextY));
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
			person.add(cur);
		}
		return false;
	}

	public static void moveWall() {
		for (int i = 0; i < SIZE; i++)
			if (map[SIZE - 1][i] == '#')
				map[SIZE - 1][i] = '.';
		for (int i = SIZE - 2; i >= 0; i--) {
			for (int j = 0; j < SIZE; j++) {
				if (map[i][j] == '#') {
					map[i + 1][j] = '#';
					map[i][j] = '.';
				}
			}
		}
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