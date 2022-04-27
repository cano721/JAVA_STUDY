package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * ũ�Ⱑ 3�� �볪���� �ִ�.
 * �� �볪���� EEE�� ǥ�õ� �������� �ű���� �Ѵ�.
 * �볪���� ��, ��, ��, ��, ȸ���� �� �� �ִ�.
 * ȸ���� �볪���� �������� 3x3 ũ�Ⱑ ��� ����־�� �� �� �ִ�.
 * EEE�������� �ű�� ���� �ּ� ���� Ƚ���� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	static final int SIZE = 3;
	static int[][] map;
	static boolean[][][] visited;
	static Wood start, end;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[][] rotateDx = { { -1, 0, 1 }, { 1, 0, -1 } };
	static int[][] rotateDy = { { 1, 0, -1 }, { -1, 0, 1 } };

	/**
	 * �϶�µ��� �ϸ� �Ǳ� �ϴµ�
	 * 3�� �ѹ��� �湮üũ�ϰ� �ű�°� �� ���� ������
	 * 
	 * direction�� 0�� ��� �볪���� ���η� �Ǿ��ִ�.
	 * direction�� 1�� ��� �볪���� ���η� �Ǿ��ִ�.
	 * 
	 * ȸ���� ������ ��� ���ʿ��� ������, ������ ��� ������ �Ʒ��� 0, 1, 2�� Point�� ����Ǿ� �ִ�. (�״�� ���������� ��)
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visited = new boolean[n][n][2];
		Point[] startWood = new Point[SIZE];
		Point[] endWood = new Point[SIZE];
		for (int i = 0, sIdx = 0, eIdx = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < n; j++) {
				char ch = input.charAt(j);
				if (ch == 'B')
					startWood[sIdx++] = new Point(i, j);
				else if (ch == 'E')
					endWood[eIdx++] = new Point(i, j);
				else
					map[i][j] = ch - '0';
			}
		}
		int startDirection = startWood[0].x - startWood[1].x == 0 ? 0 : 1;
		int endDirection = endWood[0].x - endWood[1].x == 0 ? 0 : 1;
		start = new Wood(startWood, startDirection);
		end = new Wood(endWood, endDirection);
		System.out.println(bfs());
	}

	public static int bfs() {
		Queue<Wood> q = new LinkedList<>();
		q.add(start);
		visitCheck(start);
		int move = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				Wood curWood = q.poll();
				Point[] p = curWood.p;
				if (isEnd(p))
					return move;
				// ��, ��, ��, ��
				loop: for (int i = 0; i < dx.length; i++) {
					Point[] next = new Point[SIZE];
					for (int j = 0; j < SIZE; j++) {
						int nextX = p[j].x + dx[i];
						int nextY = p[j].y + dy[i];
						try {
							if (map[nextX][nextY] == 1)
								continue loop;
							next[j] = new Point(nextX, nextY);
						} catch (ArrayIndexOutOfBoundsException e) {
							continue loop;
						}
					}
					Wood nextWood = new Wood(next, curWood.direction);
					if (!isVisited(nextWood)) {
						visitCheck(nextWood);
						q.add(nextWood);
					}
				}
				// ������ �ִٸ� ȸ��
				if (isPossibleSpace(p)) {
					Point[] next = new Point[SIZE];
					for (int i = 0; i < rotateDx[curWood.direction].length; i++) {
						int nextX = p[i].x + rotateDx[curWood.direction][i];
						int nextY = p[i].y + rotateDy[curWood.direction][i];
						next[i] = new Point(nextX, nextY);
					}
					Wood nextWood = new Wood(next, curWood.direction ^ 1);
					if (!isVisited(nextWood)) {
						visitCheck(nextWood);
						q.add(nextWood);
					}
				}
			}
			move++;
		}
		return 0;
	}

	/**
	 * ���� ���⿡ �볪���� �湮üũ�Ѵ�.
	 * @param wood
	 */
	public static void visitCheck(Wood wood) {
		Point[] p = wood.p;
		for (int i = 0; i < p.length; i++)
			visited[p[i].x][p[i].y][wood.direction] = true;
	}

	/**
	 * EEE �������� Ȯ���Ѵ�.
	 * @param p �볪���� ��ġ
	 * @return EEE ������ ��� true, �ƴ϶�� false
	 */
	public static boolean isEnd(Point[] p) {
		Point[] endP = end.p;
		for (int i = 0; i < endP.length; i++) {
			if (!p[i].equals(endP[i]))
				return false;
		}
		return true;
	}

	/**
	 * �湮üũ
	 * �ϳ��� false�� �ִٸ� ������ �� �����Ƿ� & �����ڷ� false�� �ִ��� üũ
	 * @param wood �볪��
	 * @return �湮�ߴٸ� true, �ƴ϶�� false
	 */
	public static boolean isVisited(Wood wood) {
		boolean visit = true;
		Point[] p = wood.p;
		for (int i = 0; i < p.length; i++)
			visit &= visited[p[i].x][p[i].y][wood.direction];
		return visit;
	}

	/**
	 * ȸ���ϱ� ���� ������ �ִ��� Ȯ��
	 * @param p �볪�� ��ġ
	 * @return ������ �ִٸ� true, �ƴ϶�� false
	 */
	public static boolean isPossibleSpace(Point[] p) {
		int midX = p[SIZE / 2].x;
		int midY = p[SIZE / 2].y;
		for (int i = midX - 1; i <= midX + 1; i++)
			for (int j = midY - 1; j <= midY + 1; j++)
				try {
					if (map[i][j] == 1)
						return false;
				} catch (ArrayIndexOutOfBoundsException e) {
					return false;
				}
		return true;
	}
}

class Wood {
	Point[] p;
	int direction;

	public Wood(Point[] p, int direction) {
		this.p = p;
		this.direction = direction;
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
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
		return x == other.x && y == other.y;
	}
}