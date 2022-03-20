import java.io.*;
import java.util.*;

public class BOJ_16234 {
	static boolean[][] visited;
	static ArrayList<int[]> list;
	static int[][] arr;
	static int n, l, r;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		arr = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(cal());
	}

	static int cal() {
		int result = 0;
		while (true) {
			boolean isMove = false;
			visited = new boolean[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (!visited[i][j]) {
						int sum = bfs(i, j);
						if (list.size() > 1) {
							change(sum);
							isMove = true;
						}
					}
				}
			}
			if (!isMove)
				return result;
			result++;
		}

	}

	static void change(int sum) {
		int avg = sum / list.size();
		for (int i = 0; i < list.size(); i++) {
			int[] x = list.get(i);
			arr[x[0]][x[1]] = avg;
		}
	}

	static int bfs(int x1, int y1) {
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		list = new ArrayList<>();
		Queue<int[]> q = new LinkedList<>();

		q.add(new int[] { x1, y1 });
		list.add(new int[] { x1, y1 });
		visited[x1][y1] = true;

		int sum = arr[x1][y1];

		while (!q.isEmpty()) {

			int[] tmp = q.poll();

			for (int i = 0; i < 4; i++) {
				int x = dx[i] + tmp[0];
				int y = dy[i] + tmp[1];

				if (x < 0 || y < 0 || x >= n || y >= n)
					continue;

				int diff = Math.abs(arr[x][y] - arr[tmp[0]][tmp[1]]);

				if (!visited[x][y] && diff >= l && diff <= r) {
					visited[x][y] = true;
					sum += arr[x][y];
					list.add(new int[] { x, y });
					q.add(new int[] { x, y });
				}

			}
		}
		return sum;
	}
}
