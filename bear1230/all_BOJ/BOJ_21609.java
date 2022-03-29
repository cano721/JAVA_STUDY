import java.io.*;
import java.util.*;

public class Main {
	public static int n, m;
	public static int[][] map;
	public static boolean visit[][];

	public static int[] dr = { -1, 1, 0, 0 };
	public static int[] dc = { 0, 0, -1, 1 };

	public static class Node {
		int r, c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int score = 0;
		while (true) {

			visit = new boolean[n][n];
			List<Node> group = new ArrayList<>();
			int groupCnt = 0;
			for (int r = 0; r < n; r++) {
				for (int c = 0; c < n; c++) {
					if (!visit[r][c] && map[r][c] > 0) {
						list.clear();
						listCnt = 0;
						dfs(r, c, map[r][c]);
						if (list.size() >= 2 && list.size() > group.size()) {
							group.clear();
							groupCnt = listCnt;
							group.addAll(list);
						} else if (list.size() == group.size()) {
							if (listCnt >= groupCnt) {
								group.clear();
								groupCnt = listCnt;
								group.addAll(list);
							}
						}

						visitInit();
					}
				}
			}

			if (group.isEmpty())
				break;

			for (Node node : group) {
				map[node.r][node.c] = -2;
			}
			score += group.size() * group.size();

			gravity();
			rotate();
			gravity();
		}

		System.out.println(score);
	}

	public static List<Node> list = new ArrayList<>();
	public static int listCnt;

	public static void dfs(int r, int c, int m) {
		visit[r][c] = true;
		list.add(new Node(r, c));
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visit[nr][nc]) {
				if (map[nr][nc] == 0) {
					listCnt++;
					dfs(nr, nc, m);
				} else if (map[nr][nc] == m) {
					dfs(nr, nc, m);
				}
			}
		}
	}

	public static void visitInit() {
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (map[r][c] == 0) {
					visit[r][c] = false;
				}
			}
		}
	}

	public static void gravity() {
		for (int c = 0; c < n; c++) {
			for (int r = n - 2; r >= 0; r--) {
				if (map[r][c] >= 0 && map[r + 1][c] == -2) {
					for (int rr = r + 2; rr <= n; rr++) {
						if (rr == n || map[rr][c] != -2) {
							map[rr - 1][c] = map[r][c];
							map[r][c] = -2;
							break;
						}
					}
				}
			}
		}
	}

	public static void rotate() {
		int[][] temp = new int[n][n];
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				temp[r][c] = map[c][n - 1 - r];
			}
		}
		for (int i = 0; i < n; i++) {
			System.arraycopy(temp[i], 0, map[i], 0, n);
		}
	}

}