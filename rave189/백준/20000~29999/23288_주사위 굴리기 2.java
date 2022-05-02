package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 주사위가 (0, 0)에 있다.
 * 주사위의 전개도는 다음과 같다.
 * 	 2
 * 4 1 3
 *   5
 *   6
 * 주사위를 굴려 점수를 얻으려고 할 때, 얻는 점수의 합을 구하는 문제
 * 주사위는 다음과 같은 로직으로 굴러간다.
 * 1. 주사위가 이동 방향으로 한 칸 굴러간다. 만약, 이동 방향에 칸이 없다면, 이동 방향을 반대로 한 다음 한 칸 굴러간다.
 * 2. 주사위가 도착한 칸 (x, y)에 대한 점수를 획득한다.
 * 3. 주사위의 아랫면에 있는 정수 A와 주사위가 있는 칸 (x, y)에 있는 정수 B를 비교해 이동 방향을 결정한다.
 * 3-1. A > B인 경우 이동 방향을 90도 시계 방향으로 회전시킨다.
 * 3-2. A < B인 경우 이동 방향을 90도 반시계 방향으로 회전시킨다.
 * 3-3. A = B인 경우 이동 방향에 변화는 없다.
 * 
 * 각 칸에 도달했을 때 얻는 점수는 이동한 칸 (x, y)의 값 B와 (x, y)에서 동서남북 방향으로 연속으로 이동할 수 있는 칸의 개수 C의 곱이다.
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
	 * 전개도의 2 부분이 앞, 5부분이 뒤이다. (이거때문에 1시간 동안 이유를 몰라서 직접 접어봄)
	 * 나머지는 구현하라는데로 구현하면 끝.
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
		// 동서남북(0123)
		int score = 0, direction = 0;
		// 위 아래 왼쪽 오른쪽 앞 뒤
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