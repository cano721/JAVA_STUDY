import java.io.BufferedReader;
import java.io.InputStreamReader;

public class D20211105_1_BJ_적록색약 {
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int cnt = 0;

		N = Integer.parseInt(br.readLine());
		char[][] map1 = new char[N][N];
		char[][] map2 = new char[N][N];
		boolean[][][] visited = new boolean[2][N][N];

		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			map1[i] = temp.toCharArray();
			map2[i] = temp.replaceAll("R", "G").toCharArray();
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[0][i][j]) {
					cnt++;
					dfs(i, j, map1[i][j], map1, visited[0]);
				}
			}
		}
		sb.append(cnt).append(" ");

		cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[1][i][j]) {
					cnt++;
					dfs(i, j, map2[i][j], map2, visited[1]);
				}
			}
		}
		sb.append(cnt);

		System.out.println(sb);
	}

	public static void dfs(int y, int x, char val, char[][] map, boolean[][] visit) {
		if (visit[y][x]) return;

		visit[y][x] = true;

		for (int i = 0; i < 4; i++) {
			int nextRow = y + dy[i];
			int nextCol = x + dx[i];
			if (check(nextRow, nextCol) && val == map[nextRow][nextCol]) {
				dfs(nextRow, nextCol, val, map, visit);
			}
		}
	}

	public static boolean check(int row, int col) {
		return !(row < 0 || row >= N || col < 0 || col >= N);
	}
}
