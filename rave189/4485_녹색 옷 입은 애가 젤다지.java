package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static int[][] decreaseRupee;
	static int INF = 2000000000;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		for (int count = 1;; count++) {
			int n = Integer.parseInt(br.readLine());
			if (n == 0)
				break;
			map = new int[n][n];
			decreaseRupee = new int[n][n];
			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				Arrays.fill(decreaseRupee[i], INF);
				for (int j = 0; j < n; j++)
					map[i][j] = Integer.parseInt(st.nextToken());
			}
			bfs();
			answer.append(String.format("Problem %d: %d\n", count, decreaseRupee[n - 1][n - 1]));
		}
		System.out.println(answer);
	}

	public static void bfs() {
		decreaseRupee[0][0] = map[0][0];
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0));
		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int i = 0; i < dx.length; i++) {
				int nextX = cur.x + dx[i];
				int nextY = cur.y + dy[i];
				try {
					if (decreaseRupee[cur.x][cur.y] + map[nextX][nextY] < decreaseRupee[nextX][nextY]) {
						decreaseRupee[nextX][nextY] = decreaseRupee[cur.x][cur.y] + map[nextX][nextY];
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