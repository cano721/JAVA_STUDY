package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class baekjoon19238_스타트_택시 {
	private static int dx[] = { -1, 1, 0, 0 };
	private static int dy[] = { 0, 0, -1, 1 };

	private static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int map[][] = new int[N][N];
		int answer = -1;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		LinkedList<Point> texi = new LinkedList<Point>();
		LinkedList<Point> people = new LinkedList<Point>();
		int X = Integer.parseInt(st.nextToken()) - 1;
		int Y = Integer.parseInt(st.nextToken()) - 1;
		texi.add(new Point(X, Y));
		HashMap<String, String> customer = new HashMap<String, String>();
		Point goal = new Point(0, 0);
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int gx = Integer.parseInt(st.nextToken()) - 1;
			int gy = Integer.parseInt(st.nextToken()) - 1;
			customer.put(x + "," + y, gx + "," + gy);
			map[x][y] = 2;
		}
		boolean visited[][] = new boolean[N][N];
		visited[X][Y] = true;
		int cnt = 0;
		while (customer.size() != 0 && K >= 0) {
			int size = texi.size();
			int px = N;
			int py = N;
			for (int i = 0; i < size; i++) {
				Point p = texi.poll();
				if (map[p.x][p.y] == 2) {
					if (px > p.x) {
						px = p.x;
						py = p.y;
					} else if (px == p.x && py > p.y) {
						py = p.y;
					}
				}
				if (px < N || py < N) {
					continue;
				}
				for (int k = 0; k < 4; k++) {
					int x = p.x + dx[k];
					int y = p.y + dy[k];
					if (x < 0 || x >= N || y < 0 || y >= N) {
						continue;
					}
					if (map[x][y] != 1 && !visited[x][y]) {
						texi.add(new Point(x, y));
						visited[x][y] = true;
					}
				}
			}
			if (cnt > K) {
				break;
			}
			if (px < N || py < N) {
				people = new LinkedList<Point>();
				map[px][py] = 0;
				String[] temp = customer.get(px + "," + py).split(",");
				goal.x = Integer.parseInt(temp[0]);
				goal.y = Integer.parseInt(temp[1]);
				customer.remove(px + "," + py);
				if (K - cnt <= 0) {
					break;
				} else {
					K -= cnt;
				}
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						visited[i][j] = false;
					}
				}
				visited[px][py] = true;
				people.add(new Point(px, py));
				int fuel = 0;
				outer: while (!people.isEmpty()) {
					int psize = people.size();
					for (int i = 0; i < psize; i++) {
						Point p = people.poll();
						if (p.x == goal.x && p.y == goal.y) {
							if (K - fuel >= 0) {
								K += fuel;
								texi.clear();
								texi.add(new Point(p.x, p.y));
								cnt = -1;
								for (int ii = 0; ii < N; ii++) {
									for (int j = 0; j < N; j++) {
										visited[ii][j] = false;
									}
								}
								break outer;
							}
						}
						for (int k = 0; k < 4; k++) {
							int x = p.x + dx[k];
							int y = p.y + dy[k];
							if (x < 0 || x >= N || y < 0 || y >= N) {
								continue;
							}
							if (map[x][y] != 1 && !visited[x][y]) {
								people.add(new Point(x, y));
								visited[x][y] = true;
							}
						}
					}
					fuel++;
					if (K - fuel < 0) {
						K -= fuel;
						texi.clear();
						break;
					}
				}
			}
			cnt++;
		}
		if (customer.size() == 0) {
			answer = K;
		}
		System.out.println(answer);
	}
}
