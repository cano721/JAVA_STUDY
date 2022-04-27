import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static node B_center;
	static node E_center;
	static int[] dx = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dy = { -1, 0, 1, 1, 1, 0, -1, -1 };
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];

		ArrayList<node> B_list = new ArrayList<>();
		ArrayList<node> E_list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j);

				if (map[i][j] == 'B') {
					B_list.add(new node(i, j, 0, false));
				}

				else if (map[i][j] == 'E') {
					E_list.add(new node(i, j, 0, false));
				}
			}
		}

		if (B_list.get(0).x == B_list.get(1).x) {
			B_center = new node(B_list.get(1).x, B_list.get(1).y, 0, false);
		} else {
			B_center = new node(B_list.get(1).x, B_list.get(1).y, 0, true);
		}

		if (E_list.get(0).x == E_list.get(1).x) {
			E_center = new node(E_list.get(1).x, E_list.get(1).y, 0, false);
		} else {
			E_center = new node(E_list.get(1).x, E_list.get(1).y, 0, true);
		}

		PriorityQueue<node> pq = new PriorityQueue<>(new Comparator<node>() {

			@Override
			public int compare(node o1, node o2) {
				return o1.time - o2.time;
			}
		});

		pq.offer(B_center);
		boolean[][][] visited = new boolean[N][N][2];
		while (!pq.isEmpty()) {
			node tmp = pq.poll();

			if (tmp.x == E_center.x && tmp.y == E_center.y && tmp.stat == E_center.stat) {
				System.out.println(tmp.time);
				return;
			}

			if (tmp.stat) {
				if (tmp.x - 2 >= 0 && map[tmp.x - 2][tmp.y] != '1' && !visited[tmp.x - 1][tmp.y][0]) {
					pq.offer(new node(tmp.x - 1, tmp.y, tmp.time + 1, tmp.stat));
					visited[tmp.x - 1][tmp.y][0] = true;
				}
				if (tmp.x + 2 <= N - 1 && map[tmp.x + 2][tmp.y] != '1' && !visited[tmp.x + 1][tmp.y][0]) {
					pq.offer(new node(tmp.x + 1, tmp.y, tmp.time + 1, tmp.stat));
					visited[tmp.x + 1][tmp.y][0] = true;
				}
				if (tmp.y - 1 >= 0 && map[tmp.x - 1][tmp.y - 1] != '1' && map[tmp.x + 1][tmp.y - 1] != '1'
						&& map[tmp.x][tmp.y - 1] != '1' && !visited[tmp.x][tmp.y - 1][0]) {
					pq.offer(new node(tmp.x, tmp.y - 1, tmp.time + 1, tmp.stat));
					visited[tmp.x][tmp.y - 1][0] = true;
				}
				if (tmp.y + 1 <= N - 1 && map[tmp.x - 1][tmp.y + 1] != '1' && map[tmp.x + 1][tmp.y + 1] != '1'
						&& map[tmp.x][tmp.y + 1] != '1' && !visited[tmp.x][tmp.y + 1][0]) {
					pq.offer(new node(tmp.x, tmp.y + 1, tmp.time + 1, tmp.stat));
					visited[tmp.x][tmp.y + 1][0] = true;
				}
				if (check(tmp.x, tmp.y) && !visited[tmp.x][tmp.y][1]) {
					pq.offer(new node(tmp.x, tmp.y, tmp.time + 1, !tmp.stat));
					visited[tmp.x][tmp.y][1] = true;
				}
			}

			else {
				if (tmp.x - 1 >= 0 && map[tmp.x - 1][tmp.y - 1] != '1' && map[tmp.x - 1][tmp.y] != '1'
						&& map[tmp.x - 1][tmp.y + 1] != '1' && !visited[tmp.x - 1][tmp.y][1]) {
					pq.offer(new node(tmp.x - 1, tmp.y, tmp.time + 1, tmp.stat));
					visited[tmp.x - 1][tmp.y][1] = true;
				}
				if (tmp.x + 1 <= N - 1 && map[tmp.x + 1][tmp.y - 1] != '1' && map[tmp.x + 1][tmp.y] != '1'
						&& map[tmp.x + 1][tmp.y + 1] != '1' && !visited[tmp.x + 1][tmp.y][1]) {
					pq.offer(new node(tmp.x + 1, tmp.y, tmp.time + 1, tmp.stat));
					visited[tmp.x + 1][tmp.y][1] = true;
				}
				if (tmp.y - 2 >= 0 && map[tmp.x][tmp.y - 2] != '1' && !visited[tmp.x][tmp.y - 1][1]) {
					pq.offer(new node(tmp.x, tmp.y - 1, tmp.time + 1, tmp.stat));
					visited[tmp.x][tmp.y - 1][1] = true;
				}

				if (tmp.y + 2 <= N - 1 && map[tmp.x][tmp.y + 2] != '1' && !visited[tmp.x][tmp.y + 1][1]) {
					pq.offer(new node(tmp.x, tmp.y + 1, tmp.time + 1, tmp.stat));
					visited[tmp.x][tmp.y + 1][1] = true;
				}

				if (check(tmp.x, tmp.y) && !visited[tmp.x][tmp.y][0]) {
					pq.offer(new node(tmp.x, tmp.y, tmp.time + 1, !tmp.stat));
					visited[tmp.x][tmp.y][0] = true;
				}
			}

		}

		System.out.println(0);
	}

	private static boolean check(int x, int y) {
		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= N || ny < 0 || ny >= N)
				return false;

			if (map[nx][ny] == '1') {
				return false;
			}
		}
		return true;
	}

	static class node {

		int x;
		int y;
		int time;
		boolean stat; // true 세로, false 가로

		public node(int x, int y, int time, boolean stat) {
			this.x = x;
			this.y = y;
			this.time = time;
			this.stat = stat;
		}

	}

}