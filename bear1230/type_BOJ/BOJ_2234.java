import java.util.*;
import java.io.*;

public class Main {
	static int n, m, result;
	static int[][] map;
	static int[][] visit;
	static int[] dr = { 0, -1, 0, 1 };
	static int[] dc = { -1, 0, 1, 0 };
	static HashMap<Integer, Integer> room;

	static class point {
		int r;
		int c;

		public point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[m][n];
		visit = new int[m][n];
		room = new HashMap<Integer, Integer>();
		result = 0;
		int max = 0;

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int index = 1;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (visit[i][j] == 0) {
					room.put(index, BFS(i, j, index));
					max = Math.max(max, room.get(index));
					index++;
				}
			}
		}
		System.out.println(index - 1);
		System.out.println(max);
		System.out.println(result);
	}

	public static int BFS(int i, int j, int index) {

		int count = 0;
		visit[i][j] = index;
		Queue<point> q = new LinkedList<>();
		Queue<Integer> link = new LinkedList<>();
		q.add(new point(i, j));

		while (!q.isEmpty()) {
			point cur = q.poll();
			count++;
			int nowValue = map[cur.r][cur.c];

			for (int k = 0; k < 4; k++) {
				int nr = cur.r + dr[k];
				int nc = cur.c + dc[k];

				if (nowValue % 2 == 0) {
					if (check(nr, nc) == false) {
						nowValue /= 2;
						continue;
					}

					if (visit[nr][nc] == 0) {
						visit[nr][nc] = index;
						q.offer(new point(nr, nc));
					}
				} else {
					if (check(nr, nc) == false) {
						nowValue /= 2;
						continue;
					}
					if (visit[nr][nc] != 0 && visit[nr][nc] != index) {
						if (!link.contains(visit[nr][nc]))
							link.offer(visit[nr][nc]);
					}
				}
				nowValue /= 2;
			}

		}

		while (!link.isEmpty()) {
			int now = link.poll();
			result = Math.max(result, count + room.get(now));
		}

		return count;
	}

	public static boolean check(int nr, int nc) {
		if (nr < 0 || nr >= m || nc < 0 || nc >= n)
			return false;
		return true;
	}

}