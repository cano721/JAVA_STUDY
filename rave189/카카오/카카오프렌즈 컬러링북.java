package Programmers;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	int[][] map;
	boolean[][] visited;
	int[] dx = { 1, -1, 0, 0 };
	int[] dy = { 0, 0, 1, -1 };

	public int[] solution(int m, int n, int[][] picture) {
		map = picture;
		visited = new boolean[m][n];
		int numOfArea = 0;
		int max = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (map[i][j] != 0 && !visited[i][j]) {
					max = Math.max(max, bfs(new Point(i, j), map[i][j]));
					numOfArea++;
				}
			}
		}
		return new int[] { numOfArea, max };
	}

	public int bfs(Point start, int type) {
		Queue<Point> q = new LinkedList<>();
		q.add(start);
		visited[start.x][start.y] = true;
		int count = 0;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			count++;
			for (int i = 0; i < dx.length; i++) {
				int nextX = cur.x + dx[i];
				int nextY = cur.y + dy[i];
				try {
					if (visited[nextX][nextY] || map[nextX][nextY] != type)
						continue;
					q.add(new Point(nextX, nextY));
					visited[nextX][nextY] = true;
				} catch (Exception e) {
				}
			}
		}
		return count;
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}