package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class baekjoon2573_빙산 {
	private static int map[][];
	private static int N, M;
	private static int dx[] = { -1, 1, 0, 0 };
	private static int dy[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int answer = 0;
		int cnt = 0;
		while (isCheck()) {
			deleteIce();
			cnt++;
		}
		outer: for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0) {
					answer = cnt;
					break outer;
				}
			}
		}
		System.out.println(answer);
	}

	private static void deleteIce() {
		int copyMap[][] = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int cnt = 0;
				if (map[i][j] != 0) {
					for (int k = 0; k < 4; k++) {
						int x = i + dx[k];
						int y = j + dy[k];
						if (x < 0 || x >= N || y < 0 || y >= M) {
							continue;
						}
						if (map[x][y] == 0) {
							cnt++;
						}
					}
				}
				if (map[i][j] - cnt < 0) {
					copyMap[i][j] = 0;
				} else {
					copyMap[i][j] = map[i][j] - cnt;
				}
			}
		}
		map = copyMap;
	}

	private static boolean isCheck() {
		int cnt = 1;
		boolean visited[][] = new boolean[N][M];
		LinkedList<String> que = new LinkedList<>();
		outer: for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0) {
					que.add(i + "," + j);
					visited[i][j] = true;
					break outer;
				}
			}
		}
		if (que.size() == 0) {
			return false;
		}
		while (!que.isEmpty()) {
			StringTokenizer st = new StringTokenizer(que.poll(), ",");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			for (int k = 0; k < 4; k++) {
				int x = a + dx[k];
				int y = b + dy[k];
				if (x < 0 || x >= N || y < 0 || y >= M) {
					continue;
				}
				if (map[x][y] > 0 && !visited[x][y]) {
					que.add(x + "," + y);
					visited[x][y] = true;
				}
			}
			if (que.isEmpty()) {
				outer2: for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						if (map[i][j] > 0 && !visited[i][j]) {
							que.add(i + "," + j);
							visited[i][j] = true;
							break outer2;
						}
					}
				}
				if (cnt == 1 && que.isEmpty()) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}
}
