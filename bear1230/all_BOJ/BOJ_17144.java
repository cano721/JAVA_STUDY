import java.util.*;
import java.io.*;

public class Main {
	private static int r, c, t;
	private static int[][] map, nmap;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < t; i++) {
			spreadDust();
		}

		int ans = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] > 0) {
					ans += map[i][j];
				}
			}
		}

		System.out.println(ans);

	}

	public static void spreadDust() {
		ArrayList<node> list = new ArrayList<>();
		nmap = new int[r][c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] > 0) {// 먼지인 경우
					int cnt = 0;
					for (int d = 0; d < 4; d++) {
						int ny = i + dy[d];
						int nx = j + dx[d];
						if (ny < 0 || ny > r - 1 || nx < 0 || nx > c - 1 || map[ny][nx] == -1)
							continue;

						nmap[ny][nx] += map[i][j] / 5;
						cnt++;
					}
					nmap[i][j] += map[i][j] - (map[i][j] / 5) * cnt;
				} else if (map[i][j] == -1) {
					nmap[i][j] = -1;
					list.add(new node(i, j));
				}
			}
		}

		for (int i = 0; i < r; i++) {
			map[i] = Arrays.copyOf(nmap[i], c);
		}

		cleanAir(list.get(0), list.get(1));

	}

	public static void cleanAir(node up, node down) {
		for (int i = 0; i < c - 1; i++) {
			if (nmap[up.y][i] == -1 || nmap[down.y][i] == -1) {
				map[up.y][i + 1] = 0;
				map[down.y][i + 1] = 0;

			} else {
				map[up.y][i + 1] = nmap[up.y][i];
				map[down.y][i + 1] = nmap[down.y][i];
			}

			map[0][i] = nmap[0][i + 1];

			map[r - 1][i] = nmap[r - 1][i + 1];
		}

		for (int j = 0; j < r - 1; j++) {

			if (j < up.y) {
				map[j + 1][0] = nmap[j][0];
				map[j][c - 1] = nmap[j + 1][c - 1];

			} else if (j >= down.y) {
				map[j + 1][c - 1] = nmap[j][c - 1];
				map[j][0] = nmap[j + 1][0];
			}
		}

		map[up.y][up.x] = -1;
		map[down.y][down.x] = -1;

	}

	static class node {
		int y;
		int x;

		public node(int y, int x) {
			this.y = y;
			this.x = x;
		}

	}

}