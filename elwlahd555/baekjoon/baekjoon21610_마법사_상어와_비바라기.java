package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class baekjoon21610_마법사_상어와_비바라기 {
	private static class Point {
		int x, y, k;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public Point(int x, int y, int k) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
		}

	}

	private static int N;
	private static int dx[] = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	private static int dy[] = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int answer = 0;
		int map[][] = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		LinkedList<Point> cloud = new LinkedList<>();
		cloud.add(new Point(N, 1));
		cloud.add(new Point(N, 2));
		cloud.add(new Point(N - 1, 1));
		cloud.add(new Point(N - 1, 2));
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int size = cloud.size();
			LinkedList<Point> tempCloud = new LinkedList<>();
			for (int j = 0; j < size; j++) {
				Point p = cloud.poll();
				int x = p.x + dx[d] * s;
				int y = p.y + dy[d] * s;
				while (!isValid(x))
					x = change(x);
				while (!isValid(y))
					y = change(y);
				map[x][y] += 1;
				tempCloud.add(new Point(x, y));
			}
			size = tempCloud.size();
			for (int j = 0; j < size; j++) {
				Point p = tempCloud.poll();
				int cnt = 0;
				for (int k = 1; k < 5; k++) {
					int xx = p.x + dx[2 * k];
					int yy = p.y + dy[2 * k];
					if (xx <= 0 || xx > N || yy <= 0 || yy > N) {
						continue;
					}
					if (map[xx][yy] > 0) {
						cnt++;
					}
				}
				tempCloud.add(new Point(p.x, p.y, cnt));
			}
			boolean visited[][] = new boolean[N + 1][N + 1];
			while (!tempCloud.isEmpty()) {
				Point p = tempCloud.poll();
				map[p.x][p.y] += p.k;
				visited[p.x][p.y] = true;
			}
			for (int j = 1; j <= N; j++) {
				for (int j2 = 1; j2 <= N; j2++) {
					if (map[j][j2] >= 2 && !visited[j][j2]) {
						cloud.add(new Point(j, j2));
						map[j][j2] -= 2;
					}
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				answer += map[i][j];
			}
		}
		System.out.println(answer);
	}

	static boolean isValid(int x) {
		return x > 0 && x <= N;
	}

	static int change(int x) {
		return x < 1 ? x + N : x - N;
	}
}
