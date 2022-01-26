package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	static char[][] map;
	static boolean[][] visited;
	static boolean[][] weakVisited;
	static int[] result = new int[26];
	static int weakColor = 0;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		visited = new boolean[n][n];
		weakVisited = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < n; j++)
				map[i][j] = line.charAt(j);
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					BFS(new Point(i, j), map[i][j]);
					result[map[i][j] - 'A']++;
				}
				if (map[i][j] == 'R' || map[i][j] == 'G')
					if (!weakVisited[i][j]) {
						weakBFS(new Point(i, j));
						weakColor++;
					}
			}
		}
		int normalPeople = result['R' - 'A'] + result['G' - 'A'] + result['B' - 'A'];
		int weakPeople = result['B' - 'A'] + weakColor;
		System.out.println(normalPeople + " " + weakPeople);
	}

	public static void BFS(Point start, char type) {
		Queue<Point> q = new LinkedList<>();
		q.add(start);
		visited[start.x][start.y] = true;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int i = 0; i < dx.length; i++) {
				int nextX = cur.x + dx[i];
				int nextY = cur.y + dy[i];
				try {
					if (visited[nextX][nextY] || map[nextX][nextY] != type)
						continue;
					visited[nextX][nextY] = true;
					q.add(new Point(nextX, nextY));
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
		}
	}

	public static void weakBFS(Point start) {
		Queue<Point> q = new LinkedList<>();
		q.add(start);
		weakVisited[start.x][start.y] = true;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int i = 0; i < dx.length; i++) {
				int nextX = cur.x + dx[i];
				int nextY = cur.y + dy[i];
				try {
					if (weakVisited[nextX][nextY] || map[nextX][nextY] == 'B')
						continue;
					weakVisited[nextX][nextY] = true;
					q.add(new Point(nextX, nextY));
				} catch (ArrayIndexOutOfBoundsException e) {
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