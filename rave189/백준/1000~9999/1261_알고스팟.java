package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static boolean[][] map;
	static int[][] bomb;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		map = new boolean[n][m];
		bomb = new int[n][m];
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			Arrays.fill(bomb[i], Integer.MAX_VALUE);
			for (int j = 0; j < m; j++)
				map[i][j] = line.charAt(j) == '1' ? true : false;
		}
		bfs();
		System.out.println(bomb[n - 1][m - 1]);
	}

	public static void bfs() {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0));
		map[0][0] = true;
		bomb[0][0] = 0;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int i = 0; i < dx.length; i++) {
				int nextX = cur.x + dx[i];
				int nextY = cur.y + dy[i];
				try {
					int bombCnt = bomb[cur.x][cur.y] + (map[nextX][nextY] ? 1 : 0);
					if (bombCnt < bomb[nextX][nextY]) {
						bomb[nextX][nextY] = bombCnt;
						q.add(new Point(nextX, nextY));
					}
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