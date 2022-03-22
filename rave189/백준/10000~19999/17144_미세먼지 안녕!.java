package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static Point[] airCleaner = new Point[2];
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int r, c;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		int airCleanerIdx = 0;
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					airCleaner[airCleanerIdx++] = new Point(i, j);
				}
			}
		}
		while (t-- > 0) {
			spreadDust();
			clean();
		}
		int answer = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] > 0)
					answer += map[i][j];
			}
		}
		System.out.println(answer);
	}

	public static void spreadDust() {
		Queue<Point> spread = new LinkedList<>();
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] <= 0)
					continue;
				int spreadAmount = 0;
				for (int t = 0; t < dx.length; t++) {
					int nextX = i + dx[t];
					int nextY = j + dy[t];
					try {
						if (map[nextX][nextY] == -1)
							continue;
						int amount = map[i][j] / 5;
						if (amount > 0) {
							spread.add(new Point(nextX, nextY, amount));
							spreadAmount += amount;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
					}
				}
				map[i][j] -= spreadAmount;
			}
		}
		while (!spread.isEmpty()) {
			Point dust = spread.poll();
			map[dust.x][dust.y] += dust.value;
		}
	}

	public static void clean() {
		Point topCleaner = airCleaner[0];
		for (int i = topCleaner.x; i > 0; i--)
			map[i][0] = map[i - 1][0];
		for (int i = 1; i < c; i++)
			map[0][i - 1] = map[0][i];
		for (int i = 1; i <= topCleaner.x; i++)
			map[i - 1][c - 1] = map[i][c - 1];
		for (int i = c - 1; i > 0; i--)
			map[topCleaner.x][i] = map[topCleaner.x][i - 1];
		map[topCleaner.x][topCleaner.y] = -1;
		map[topCleaner.x][topCleaner.y + 1] = 0;

		Point bottomCleaner = airCleaner[1];
		for (int i = bottomCleaner.x + 1; i < r; i++)
			map[i - 1][0] = map[i][0];
		for (int i = 1; i < c; i++)
			map[r - 1][i - 1] = map[r - 1][i];
		for (int i = r - 1; i > bottomCleaner.x; i--)
			map[i][c - 1] = map[i - 1][c - 1];
		for (int i = c - 1; i > 0; i--)
			map[bottomCleaner.x][i] = map[bottomCleaner.x][i - 1];
		map[bottomCleaner.x][bottomCleaner.y] = -1;
		map[bottomCleaner.x][bottomCleaner.y + 1] = 0;
	}
}

class Point {
	int x, y, value;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}
}