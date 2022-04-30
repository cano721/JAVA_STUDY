package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon17822_원판_돌리기 {
	private static int N, M;
	private static int dx[] = { -1, 1, 0, 0 };
	private static int dy[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int map[][] = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int rotation[][] = new int[T][3];
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				rotation[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < T; i++) {
			map = rotate(map, rotation[i]);
			map = check(map);
		}
		int answer = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				answer += map[i][j];
			}
		}
		System.out.println(answer);
	}

	private static int[][] check(int[][] map) {
		int temp[][] = new int[N + 1][M + 1];
		boolean checked = false;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				temp[i][j] = map[i][j];
				if (map[i][j] == 0) {
					continue;
				}
				for (int k = 0; k < 4; k++) {
					int x = i + dx[k];
					int y = j + dy[k];
					if (x == 0 || x > N) {
						continue;
					}
					if (y == 0) {
						y = M;
					} else if (y > M) {
						y = 1;
					}
					if (map[i][j] == map[x][y]) {
						temp[i][j] = 0;
						temp[x][y] = 0;
						checked = true;
					}
				}
			}
		}
		if (!checked) {
			int cnt = 0;
			double sum = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					if (temp[i][j] > 0) {
						cnt++;
						sum += temp[i][j];
					}
				}
			}
			double avg = sum / cnt;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					if (temp[i][j] == 0) {
						continue;
					}
					if (temp[i][j] > avg) {
						temp[i][j]--;
					} else if (temp[i][j] < avg) {
						temp[i][j]++;
					}
				}
			}
		}
		return temp;
	}

	private static int[][] rotate(int[][] map, int[] rotate) {
		int temp[][] = new int[N + 1][M + 1];
		int k = rotate[2] % M;
		if (rotate[1] == 0) {
			for (int i = 1; i <= N; i++) {
				if (i % rotate[0] == 0) {
					for (int j = 1; j <= M; j++) {
						int y = j + k;
						if (y > M) {
							y -= M;
						}
						temp[i][y] = map[i][j];
					}
				} else {
					for (int j = 1; j <= M; j++) {
						temp[i][j] = map[i][j];
					}
				}
			}
		} else {
			for (int i = 1; i <= N; i++) {
				if (i % rotate[0] == 0) {
					for (int j = 1; j <= M; j++) {
						int y = j - k;
						if (y <= 0) {
							y += M;
						}
						temp[i][y] = map[i][j];
					}
				} else {
					for (int j = 1; j <= M; j++) {
						temp[i][j] = map[i][j];
					}
				}
			}
		}
		return temp;
	}
}
