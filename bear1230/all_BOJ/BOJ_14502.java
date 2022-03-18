import java.util.*;
import java.io.*;

class Point {
	int x, y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

}

public class Main {
	static int n, m, max = Integer.MIN_VALUE;
	static int[][] map;
	static int[] row = { 0, 0, 1, -1 };
	static int[] col = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(3);
		System.out.println(max);
	}

	private static void dfs(int left) {
		if (left <= 0) {
			bfs(map);
			return;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					dfs(left - 1);
					map[i][j] = 0;
				}
			}
		}
	}

	private static void bfs(int[][] map) {
		Queue<Point> que = new LinkedList<>();
		int[][] newMap = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				newMap[i][j] = map[i][j];
				if (map[i][j] == 2) {
					que.offer(new Point(i, j));
				}
			}
		}

		while (!que.isEmpty()) {
			Point p = que.poll();
			int x = p.x;
			int y = p.y;
			for (int i = 0; i < 4; i++) {
				int nx = x + row[i];
				int ny = y + col[i];
				if (nx < 0 || nx >= n || ny < 0 || ny >= m || newMap[nx][ny] != 0)
					continue;
				newMap[nx][ny] = 2;
				que.offer(new Point(nx, ny));
			}
		}

		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (newMap[i][j] == 0) {
					cnt++;
				}
			}
		}
		if (cnt > max) {
			max = cnt;
		}
		return;
	}

}