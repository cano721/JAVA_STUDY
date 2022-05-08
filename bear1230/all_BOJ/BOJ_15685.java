import java.io.*;
import java.util.*;

public class Main {

	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { 1, 0, -1, 0 };
	static boolean[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		map = new boolean[101][101];
		int x, y, d, g;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			map[x][y] = true;
			dragonCurve(x, y, d, g);
		}
		System.out.println(count());
	}

	private static int count() {
		int cnt = 0;
		for (int i = 1; i < 101; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] && map[i + -1][j] && map[i + -1][j + 1] && map[i][j + 1]) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static void dragonCurve(int x, int y, int d, int g) {
		ArrayList<Integer> list = new ArrayList<>();
		int nx = x + dx[d];
		int ny = y + dy[d];
		list.add(d);
		map[x + dx[d]][y + dy[d]] = true;

		for (int i = 0; i < g; i++) {
			int size = list.size() - 1;

			for (int j = size; j >= 0; j--) {
				int curr = (list.get(j) + 1) % 4;
				nx += dx[curr];
				ny += dy[curr];
				list.add(curr);
				map[nx][ny] = true;
			}
		}
	}
}