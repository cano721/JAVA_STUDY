package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 크기가 3인 통나무가 있다.
 * 이 통나무를 EEE로 표시된 지점까지 옮기려고 한다.
 * 통나무는 상, 하, 좌, 우, 회전을 할 수 있다.
 * 회전은 통나무를 기준으로 3x3 크기가 모두 비어있어야 할 수 있다.
 * EEE지점까지 옮기기 위한 최소 동작 횟수를 구하는 문제
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
	 * 하라는데로 하면 되긴 하는데
	 * 3개 한번에 방문체크하고 옮기는게 좀 많이 귀찮네
	 * 
	 * direction이 0인 경우 통나무가 가로로 되어있다.
	 * direction이 1인 경우 통나무가 세로로 되어있다.
	 * 
	 * 회전은 가로인 경우 왼쪽에서 오른쪽, 세로인 경우 위에서 아래로 0, 1, 2번 Point에 저장되어 있다. (그대로 목적지인지 비교)
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
				// 상, 하, 좌, 우
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
				// 공간이 있다면 회전
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
	 * 현재 방향에 통나무를 방문체크한다.
	 * @param wood
	 */
	public static void visitCheck(Wood wood) {
		Point[] p = wood.p;
		for (int i = 0; i < p.length; i++)
			visited[p[i].x][p[i].y][wood.direction] = true;
	}

	/**
	 * EEE 지점인지 확인한다.
	 * @param p 통나무의 위치
	 * @return EEE 지점인 경우 true, 아니라면 false
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
	 * 방문체크
	 * 하나라도 false가 있다면 움직일 수 있으므로 & 연산자로 false가 있는지 체크
	 * @param wood 통나무
	 * @return 방문했다면 true, 아니라면 false
	 */
	public static boolean isVisited(Wood wood) {
		boolean visit = true;
		Point[] p = wood.p;
		for (int i = 0; i < p.length; i++)
			visit &= visited[p[i].x][p[i].y][wood.direction];
		return visit;
	}

	/**
	 * 회전하기 위한 공간이 있는지 확인
	 * @param p 통나무 위치
	 * @return 공간이 있다면 true, 아니라면 false
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