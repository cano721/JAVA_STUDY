import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] map;
	static int[][] day;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		day = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ans = Math.max(dfs(i, j), max_day);
			}
		}
		System.out.println(ans);

	}

	public static int dfs(int x, int y) {
		if (day[x][y] != 0) { 
			return day[x][y];
		}

		int cnt = 1;
		for (int i = 0; i < 4; i++) { 
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && nx < n && ny >= 0 && ny < n) { 
				if (map[x][y] < map[x + dx[i]][y + dy[i]]) { 
					cnt = Math.max(dfs(nx, ny) + 1, cnt);
					day[x][y] = cnt;
				}
			}
		}

		return cnt;
	}
}