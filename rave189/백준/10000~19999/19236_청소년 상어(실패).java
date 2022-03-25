package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static final int SIZE = 4;
	static int answer = 0;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] map = new int[SIZE][SIZE];
		int[][] directionMap = new int[SIZE][SIZE];
		Point[] fishList = new Point[SIZE * SIZE + 1];
		for (int i = 0; i < SIZE; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < SIZE; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				directionMap[i][j] = Integer.parseInt(st.nextToken()) - 1;
				fishList[map[i][j]] = new Point(i, j);
			}
		}
		Point start = new Point(0, 0);
		backTracking(start, map, directionMap, fishList, 0);
		System.out.println(answer);
	}

	public static void backTracking(Point cur, int[][] map, int[][] directionMap, Point[] fishList, int result) {
		result += map[cur.x][cur.y];
		answer = Math.max(answer, result);
		fishList[map[cur.x][cur.y]] = null;
		Point[] fishs = copy(fishList);
		map[cur.x][cur.y] = -1;
		int[][] copyMap = copy(map);
		int[][] copyDirectionMap = copy(directionMap);
		for (int i = 1; i < fishs.length; i++) {
			Point fish = fishs[i];
			if (fish == null)
				continue;
			int direction = copyDirectionMap[fish.x][fish.y];
			int nextX = fish.x + dx[direction];
			int nextY = fish.y + dy[direction];
			try {
				if (copyMap[nextX][nextY] == -1)
					throw new Exception();
				if (copyMap[nextX][nextY] == 0) {
					copyMap[nextX][nextY] = copyMap[fish.x][fish.y];
					copyMap[fish.x][fish.y] = 0;
					copyDirectionMap[nextX][nextY] = copyDirectionMap[fish.x][fish.y];
				} else {
					swap(copyMap, fish.x, fish.y, nextX, nextY);
					swap(copyDirectionMap, fish.x, fish.y, nextX, nextY);
					fishs[copyMap[nextX][nextY]].move(nextX, nextY);
				}
				fishs[copyMap[fish.x][fish.y]].move(fish.x, fish.y);
			} catch (Exception e) {
				copyDirectionMap[fish.x][fish.y] = (direction + 1) % dx.length;
				i--;
			}
		}
		copyMap[cur.x][cur.y] = 0;
		for (int step = 1;; step++) {
			int direction = copyDirectionMap[cur.x][cur.y];
			int nextX = cur.x + dx[direction] * step;
			int nextY = cur.y + dy[direction] * step;
			try {
				if (copyMap[nextX][nextY] == 0)
					continue;
				backTracking(new Point(nextX, nextY), copyMap, copyDirectionMap, fishs, result);
			} catch (Exception e) {
				break;
			}
		}
	}

	public static int[][] copy(int[][] map) {
		int[][] copy = new int[map.length][map[0].length];
		for (int i = 0; i < map.length; i++)
			System.arraycopy(map[i], 0, copy[i], 0, map[0].length);
		return copy;
	}

	public static Point[] copy(Point[] fishList) {
		Point[] copy = new Point[fishList.length];
		for (int i = 0; i < fishList.length; i++) {
			if (fishList[i] == null)
				continue;
			copy[i] = new Point(fishList[i].x, fishList[i].y);
		}
		return copy;
	}

	public static void swap(int[][] map, int x1, int y1, int x2, int y2) {
		int tmp = map[x1][y1];
		map[x1][y1] = map[x2][y2];
		map[x2][y2] = tmp;
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

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
}