package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static boolean[][] map;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new boolean[n][m];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < m; j++)
				map[i][j] = line.charAt(j) == '1' ? true : false;
		}
		System.out.println(bfs(new Point(0, 0)));
	}

	public static int bfs(Point start) {
		Queue<Point> q = new LinkedList<>();
		q.add(start);
		visited[start.x][start.y] = true;
		int count = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			count++;
			while (size-- > 0) {
				Point cur = q.poll();
				if (cur.x == map.length - 1 && cur.y == map[0].length - 1)
					return count;
				for (int i = 0; i < dx.length; i++) {
					int nextX = cur.x + dx[i];
					int nextY = cur.y + dy[i];
					try {
						if (visited[nextX][nextY] || !map[nextX][nextY])
							continue;
						visited[nextX][nextY] = true;
						q.add(new Point(nextX, nextY));
					} catch (ArrayIndexOutOfBoundsException e) {
					}
				}
			}
		}
		return -1;
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}