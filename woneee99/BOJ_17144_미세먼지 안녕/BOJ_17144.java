import java.io.*;
import java.util.*;

public class BOJ_17144 {
	static List<Integer> cleaner = new ArrayList<>();
	static int[][] arr;
	static int r, c;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());

		arr = new int[r][c];

		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < c; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == -1)
					cleaner.add(i);
			}
		}
		while (t-- > 0) {
			cal();
			clear();

		}
		int result = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (arr[i][j] > 0)
					result += arr[i][j];
			}
		}

		System.out.println(result);
	}

	public static void clear() {
		int top = cleaner.get(0);

		// 왼쪽
		for (int x = top - 1; x > 0; x--) {
			arr[x][0] = arr[x - 1][0];
		}

		// 위
		for (int y = 0; y < c - 1; y++) {
			arr[0][y] = arr[0][y + 1];
		}

		// 오른쪽
		for (int x = 1; x <= top; x++) {
			arr[x - 1][c - 1] = arr[x][c - 1];
		}

		// 아래
		for (int y = c - 1; y > 1; y--) {
			arr[top][y] = arr[top][y - 1];
		}

		arr[top][1] = 0;

		int bottom = cleaner.get(1);

		// 왼쪽
		for (int x = bottom + 1; x < r - 1; x++) {
			arr[x][0] = arr[x + 1][0];
		}
		// 아래
		for (int y = 0; y < c - 1; y++) {
			arr[r - 1][y] = arr[r - 1][y + 1];
		}
		// 오른쪽
		for (int x = r - 1; x >= bottom; x--) {
			arr[x][c - 1] = arr[x - 1][c - 1];
		}
		// 위
		for (int y = c - 1; y > 1; y--) {
			arr[bottom][y] = arr[bottom][y - 1];
		}

		arr[bottom][1] = 0;

	}

	public static void cal() {
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		int[][] dust = new int[r][c];

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {

				if (arr[i][j] > 0) {
					int spread = arr[i][j] / 5;
					int dustCnt = 0;

					for (int k = 0; k < 4; k++) {

						int nx = i + dx[k];
						int ny = j + dy[k];

						if (nx < 0 || nx >= r || ny < 0 || ny >= c || arr[nx][ny] == -1)
							continue;

						dust[nx][ny] += spread;
						dustCnt++;
					}

					arr[i][j] -= dustCnt * spread;
				}

			}
		}

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				arr[i][j] += dust[i][j];
			}
		}

	}
}
