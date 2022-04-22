package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static Queue<Ice> ices = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0)
					ices.add(new Ice(i, j, map[i][j]));
			}
		}
		int answer = 0;
		do {
			answer++;
			melt();
		} while (!ices.isEmpty() && !isDivide());
		System.out.println(ices.isEmpty() ? 0 : answer);
	}

	public static void melt() {
		ArrayList<Ice> melts = new ArrayList<>();
		while (!ices.isEmpty()) {
			Ice cur = ices.poll();
			int closeWaterCnt = 0;
			for (int i = 0; i < dx.length; i++) {
				int nextX = cur.x + dx[i];
				int nextY = cur.y + dy[i];
				try {
					if (map[nextX][nextY] == 0)
						closeWaterCnt++;
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
			melts.add(new Ice(cur.x, cur.y, closeWaterCnt));
		}
		for (Ice ice : melts) {
			map[ice.x][ice.y] = Math.max(map[ice.x][ice.y] - ice.v, 0);
			if (map[ice.x][ice.y] > 0)
				ices.add(new Ice(ice.x, ice.y, map[ice.x][ice.y]));
		}
	}

	public static boolean isDivide() {
		visited = new boolean[map.length][map[0].length];
		boolean isDivide = false;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] > 0 && !visited[i][j]) {
					if (isDivide)
						return true;
					else {
						isDivide = true;
						bfs(i, j);
					}
				}
			}
		}
		return false;
	}

	public static void bfs(int x, int y) {
		Queue<Ice> q = new LinkedList<>();
		q.add(new Ice(x, y, 0));
		visited[x][y] = true;
		while (!q.isEmpty()) {
			Ice cur = q.poll();
			for (int i = 0; i < dx.length; i++) {
				int nextX = cur.x + dx[i];
				int nextY = cur.y + dy[i];
				try {
					if (visited[nextX][nextY] || map[nextX][nextY] == 0)
						continue;
					visited[nextX][nextY] = true;
					q.add(new Ice(nextX, nextY, 0));
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
		}
	}
}

class Ice {
	int x, y, v;

	public Ice(int x, int y, int v) {
		this.x = x;
		this.y = y;
		this.v = v;
	}
}