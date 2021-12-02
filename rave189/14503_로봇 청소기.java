package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static boolean[][] map;
	static boolean[][] isCleaned;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static Point cur;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		cur = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()));
		map = new boolean[n][m];
		isCleaned = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				map[i][j] = st.nextToken().charAt(0) == '1' ? true : false;
		}
		int answer = 0;
		while (true) {
			if (!isCleaned[cur.x][cur.y]) {
				isCleaned[cur.x][cur.y] = true;
				answer++;
			}
			if (!searchArround())
				break;
		}
		System.out.println(answer);
	}

	public static boolean searchArround() {
		while (true) {
			for (int i = 0; i < 4; i++) {
				cur.next();
				int nextX = cur.x + dx[cur.direction];
				int nextY = cur.y + dy[cur.direction];
				if (!map[nextX][nextY] && !isCleaned[nextX][nextY]) {
					cur.go(nextX, nextY);
					return true;
				}
			}
			if (!back())
				break;
		}
		return false;
	}

	public static boolean back() {
		int backDirection = (cur.direction + cur.kindOfDirection / 2) % cur.kindOfDirection;
		int nextX = cur.x + dx[backDirection];
		int nextY = cur.y + dy[backDirection];
		if (map[nextX][nextY])
			return false;
		cur.go(nextX, nextY);
		return true;
	}
}

class Point {
	int x, y, direction, kindOfDirection = 4;

	public Point(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public void next() {
		direction = (direction - 1 < 0 ? kindOfDirection : direction) - 1;
	}

	public void go(int nextX, int nextY) {
		x = nextX;
		y = nextY;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + ", direction=" + direction + "]";
	}
}