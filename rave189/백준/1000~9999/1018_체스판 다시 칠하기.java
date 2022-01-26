package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] dx = { 0, 1 };
	static int[] dy = { 1, 0 };
	static char[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < m; j++)
				map[i][j] = line.charAt(j);
		}
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i <= n - 8; i++)
			for (int j = 0; j <= m - 8; j++) {
				Point start = new Point(i, j);
				answer = Math.min(answer, bfs(start, 'B'));
				answer = Math.min(answer, bfs(start, 'W'));
			}
		System.out.println(answer);
	}

	public static int bfs(Point start, char next) {
		boolean[][] visited = new boolean[map.length][map[0].length];
		Queue<Point> q = new LinkedList<>();
		q.add(start);
		visited[start.x][start.y] = true;
		int count = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				Point cur = q.poll();
				if (map[cur.x][cur.y] != next)
					count++;
				for (int i = 0; i < dx.length; i++) {
					int nextX = cur.x + dx[i];
					int nextY = cur.y + dy[i];
					if (isValid(start, nextX, nextY) && !visited[nextX][nextY]) {
						visited[nextX][nextY] = true;
						q.add(new Point(nextX, nextY));
					}
				}
			}
			next = next == 'B' ? 'W' : 'B';
		}
		return count;
	}

	public static boolean isValid(Point p, int x, int y) {
		return (p.x <= x && x < p.x + 8) && (p.y <= y && y < p.y + 8);
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}