import java.io.*;
import java.util.*;

public class Main {
	static int N, M, min, result;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int[][] map;
	static List<Point> virus;
	static Queue<Point> que;
	static boolean[] check;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		virus = new ArrayList<>();
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				if (map[i][j] != 1) {
					result++;
				}

				if (map[i][j] == 2) {
					virus.add(new Point(j, i, 0));
				}
			}
		}
		check = new boolean[virus.size()];
		min = Integer.MAX_VALUE;
		que = new LinkedList<>();
		comb(0, 0);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	private static void comb(int cnt, int begin) {
		if (cnt == M) {
			bfs();
			return;
		}

		for (int i = begin; i < virus.size(); i++) {
			check[i] = true;
			comb(cnt + 1, i + 1);
			check[i] = false;
		}
	}

	private static void bfs() {
		Point p = null;
		int ny, nx;

		for (boolean[] v : visited) {
			Arrays.fill(v, false);
		}

		for (int i = 0; i < check.length; i++) {
			if (check[i]) {
				p = virus.get(i);
				que.offer(p);
				visited[p.y][p.x] = true;
			}
		}

		int cnt = virus.size();
		int d = 0;

		while (!que.isEmpty()) {
			p = que.poll();

			if (p.d >= min) {
				que.clear();
				return;
			}

			if (result == cnt) {
				d = p.d;
				while (!que.isEmpty()) {
					d = que.poll().d;
				}
				break;
			}

			for (int k = 0; k < 4; k++) {
				ny = p.y + dy[k];
				nx = p.x + dx[k];

				if (ny < 0 || nx < 0 || ny >= N || nx >= N)
					continue;

				if (visited[ny][nx])
					continue;

				if (map[ny][nx] == 1)
					continue;

				que.offer(new Point(nx, ny, p.d + 1));
				visited[ny][nx] = true;

				if (map[ny][nx] != 2) {
					cnt++;
				}
			}
		}

		if (cnt == result && d < min) {
			min = d;
		}
	}

	static class Point {
		int x;
		int y;
		int d;

		public Point(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
}
