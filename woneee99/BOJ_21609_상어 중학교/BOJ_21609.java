import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_21609{

	static int[] movex = { -1, 0, 1, 0 };
	static int[] movey = { 0, 1, 0, -1 };

	static int[][] group, mat;
	static int N, M, cnt, rainbow, answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		mat = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				mat[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			group = new int[N][N];

			int number = 1, max = 0, target = 0, rain = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					cnt = 0;
					rainbow = 0;
					if (group[i][j] == 0 && mat[i][j] > 0) {
						DFS(i, j, number, mat[i][j]);
						if (max < cnt || (max == cnt && rain <= rainbow)) {
							target = number;
							max = cnt;
							rain = rainbow;
						}
						number++;
					}
				}
			}

			if (max < 2)
				break;

			loop: for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (group[i][j] == target && mat[i][j] != 0) {
						remove(i, j, mat[i][j]);
						answer += max * max;
						break loop;
					}
				}
			}

			drop();
			rotate();
			drop();
		}

		System.out.println(answer);
	}

	public static void DFS(int x, int y, int number, int color) {
		group[x][y] = number;
		cnt++;
		if (mat[x][y] == 0)
			rainbow++;

		for (int d = 0; d < 4; d++) {
			int nx = x + movex[d];
			int ny = y + movey[d];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				continue;

			if ((group[nx][ny] == 0 && mat[nx][ny] == color) || (group[nx][ny] != number && mat[nx][ny] == 0)) {
				DFS(nx, ny, number, color);
			}
		}
	}

	public static void remove(int x, int y, int color) {
		mat[x][y] = -2;

		for (int d = 0; d < 4; d++) {
			int nx = x + movex[d];
			int ny = y + movey[d];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				continue;

			if (mat[nx][ny] == color || mat[nx][ny] == 0) {
				remove(nx, ny, color);
			}
		}
	}

	public static void drop() {
		for (int j = 0; j < N; j++) {
			int k = N - 1;
			for (int i = N - 1; i >= 0; i--) {
				if (mat[i][j] == -1) {
					k = i - 1;
				} else if (mat[i][j] != -2) {
					int temp = mat[i][j];
					mat[i][j] = -2;
					mat[k][j] = temp;
					k--;
				}
			}
		}
	}

	public static void rotate() {
		int[][] temp = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[N - j - 1][i] = mat[i][j];
			}
		}

		mat = temp;
	}
}