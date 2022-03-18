package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int totalArea, answer = 0;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static ArrayList<Point> virus = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		totalArea = n * m;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					totalArea--;
				else if (map[i][j] == 2) {
					virus.add(new Point(i, j));
					totalArea--;
				}
			}
		}
		bruteforce(map, 3);
		System.out.println(answer);
	}

	public static void bruteforce(int[][] map, int depth) {
		if (depth == 0) {
			int[][] tmpMap = copy(map);
			answer = Math.max(answer, bfs(tmpMap));
			return;
		}
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					bruteforce(map, depth - 1);
					map[i][j] = 0;
				}
			}
		}
	}

	public static int[][] copy(int[][] map) {
		int[][] tmp = new int[map.length][map[0].length];
		for (int i = 0; i < map.length; i++)
			System.arraycopy(map[i], 0, tmp[i], 0, map[0].length);
		return tmp;
	}

	public static int bfs(int[][] map) {
		Queue<Point> q = new LinkedList<>();
		q.addAll(virus);
		int max = totalArea;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int i = 0; i < dx.length; i++) {
				int nextX = cur.x + dx[i];
				int nextY = cur.y + dy[i];
				try {
					if (map[nextX][nextY] == 1 || map[nextX][nextY] == 2)
						continue;
					map[nextX][nextY] = 2;
					max--;
					q.add(new Point(nextX, nextY));
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
		}
		return max - 3;
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}