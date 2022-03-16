package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class baekjoon14502_연구소 {
	private static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	private static int dx[] = { -1, 1, 0, 0 };
	private static int dy[] = { 0, 0, -1, 1 };
	private static int N, M, result, arr[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		result = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		DFS(0);
		System.out.println(result);
	}

	private static void DFS(int cnt) {
		if (cnt == 3) {
			BFS();
			return;
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0) {
					arr[i][j] = 1;
					DFS(cnt + 1);
					arr[i][j] = 0;
				}
			}
		}
	}

	private static void BFS() {
		LinkedList<Point> que = new LinkedList<Point>();
		int copy_map[][] = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy_map[i][j] = arr[i][j];
				if (copy_map[i][j] == 2) {
					que.add(new Point(i, j));
				}
			}
		}
		while (!que.isEmpty()) {
			Point p = que.poll();
			for (int k = 0; k < 4; k++) {
				int x = p.x + dx[k];
				int y = p.y + dy[k];
				if (x < 0 || x >= N || y < 0 || y >= M) {
					continue;
				}
				if (copy_map[x][y] == 0) {
					copy_map[x][y] = 2;
					que.add(new Point(x, y));
				}
			}
		}
		check(copy_map);
	}

	private static void check(int[][] copy_map) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy_map[i][j] == 0) {
					cnt++;
				}
			}
		}
		result = Math.max(result, cnt);
	}
}
