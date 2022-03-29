import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[][] map;
	static int[] order;
	static int[][] seats;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int likeCnt, emptyCnt, R, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		order = new int[N * N + 1];
		map = new int[N * N + 1][4];

		for (int i = 1; i <= N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			order[i] = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 4; j++) {
				map[order[i]][j] = Integer.parseInt(st.nextToken());
			}
		}

		seats = new int[N][N];

		for (int i = 1; i <= N * N; i++) {
			likeCnt = -1;
			emptyCnt = -1;
			R = Integer.MAX_VALUE;
			C = Integer.MAX_VALUE;
			seat(order[i]);
		}

		int score = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];

					if (isIn(nx, ny) && contains(seats[nx][ny], map[seats[i][j]])) {
						cnt++;
					}
				}
				score += Math.pow(10, cnt - 1);
			}
		}
		System.out.println(score);
	}

	static void seat(int x) {
		int like = 0;
		int empty = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (seats[i][j] != 0)
					continue;
				like = 0;
				empty = 0;
				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if (isIn(nx, ny)) {
						if (contains(seats[nx][ny], map[x])) {
							like++;
						} else if (seats[nx][ny] == 0) {
							empty++;
						}
					}
				}

				if (like > likeCnt) {
					likeCnt = like;
					emptyCnt = empty;
					R = i;
					C = j;
				} else if (like == likeCnt) {
					if (empty > emptyCnt) {
						emptyCnt = empty;
						R = i;
						C = j;
					} else if (empty == emptyCnt) {
						if (i < R) {
							R = i;
							C = j;
						} else if (i == R) {
							C = Math.min(C, j);
						}
					}

				}
			}
		}

		seats[R][C] = x;
	}

	static boolean isIn(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}

	static boolean contains(int x, int[] students) {
		for (int i : students) {
			if (i == x) {
				return true;
			}
		}
		return false;
	}

}