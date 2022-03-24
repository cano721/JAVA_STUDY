import java.io.*;
import java.util.*;

public class BOJ_16236 {
	static Queue<Info> q = new LinkedList<>();
	static int[][] arr;
	static boolean[][] checked;
	static int size = 2, count = 0, time = 0;

	public static class Info {
		int x;
		int y;
		int move;

		public Info(int x, int y, int move) {
			this.x = x;
			this.y = y;
			this.move = move;
		}
	}

	public static PriorityQueue<Info> pq = new PriorityQueue<>((o1, o2) -> {
		if (o1.move == o2.move) {
			if (o1.x == o2.x) {
				return o1.y - o2.y;
			} else {
				return o1.x - o2.x;
			}
		} else {
			return o1.move - o2.move;
		}
	});

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		StringTokenizer st;
		arr = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 9) {
					q.offer(new Info(i, j, 0));
					arr[i][j] = 0;
				}
			}
		}

		while (true) {
			cal(n);

			if (pq.isEmpty())
				break;

			Info info = pq.poll();
			time += info.move;
			arr[info.x][info.y] = 0;

			q.add(new Info(info.x, info.y, 0));
			count++;

			if (size == count) {
				size++;
				count = 0;
			}

			pq.clear();
		}

		System.out.println(time);
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static void cal(int n) {
		checked = new boolean[n][n];

		while (!q.isEmpty()) {
			Info tmp = q.poll();

			for (int i = 0; i < 4; i++) {
				int x = dx[i] + tmp.x;
				int y = dy[i] + tmp.y;

				if (x >= n || x < 0 || y >= n || y < 0)
					continue;

				if (!checked[x][y]) {
					if (arr[x][y] == size || arr[x][y] == 0) {
						checked[x][y] = true;
						q.offer(new Info(x, y, tmp.move + 1));
					}
					else if (arr[x][y] < size) {
						checked[x][y] = true;
						pq.offer(new Info(x, y, tmp.move + 1));
					}
				}
			}
		}
	}
}
