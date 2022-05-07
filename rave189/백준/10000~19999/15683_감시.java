package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static final int WALL = 6;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int[][][] directions = { {}, { { 0 }, { 1 }, { 2 }, { 3 } }, { { 0, 2 }, { 1, 3 } },
			{ { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 } }, { { 0, 1, 2 }, { 1, 2, 3 }, { 2, 3, 0 }, { 3, 0, 1 } },
			{ { 0, 1, 2, 3 } } };
	static int n, m, answer = Integer.MAX_VALUE;
	static ArrayList<Point> cctvs = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (0 < map[i][j] && map[i][j] < 6)
					cctvs.add(new Point(i, j));
			}
		}
		backTracking(map, 0);
		System.out.println(answer);
	}

	public static void backTracking(int[][] map, int cur) {
		if (cur == cctvs.size()) {
			answer = Math.min(answer, calc(map));
			return;
		}
		Point cctv = cctvs.get(cur);
		int cctvType = map[cctv.x][cctv.y];
		for (int[] direction : directions[cctvType]) {
			int[][] copyMap = copy(map);
			setWatchArea(copyMap, cctv, direction, cctvType);
			backTracking(copyMap, cur + 1);
		}
	}

	public static int calc(int[][] map) {
		int count = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (map[i][j] == 0)
					count++;
		return count;
	}

	public static int[][] copy(int[][] map) {
		int[][] copy = new int[n][m];
		for (int i = 0; i < n; i++)
			System.arraycopy(map[i], 0, copy[i], 0, m);
		return copy;
	}

	public static void setWatchArea(int[][] map, Point p, int[] direction, int value) {
		for (int d : direction) {
			for (int step = 1;; step++) {
				try {
					int nextX = p.x + dx[d] * step;
					int nextY = p.y + dy[d] * step;
					if (map[nextX][nextY] == WALL)
						break;
					else if (map[nextX][nextY] > 0)
						continue;
					map[nextX][nextY] = value;
				} catch (ArrayIndexOutOfBoundsException e) {
					break;
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
}