import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K, count;
	static int[][] map;
	static int[][][] visit, sharkDir;
	static List<Shark> list;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static private class Shark {
		int x, y, dir;

		public Shark(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		visit = new int[N][N][2];

		list = new LinkedList<>();
		for (int i = 0; i < M; i++) {
			list.add(new Shark(-1, -1, -1));
		}
		sharkDir = new int[M][4][4];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) {
					// 상어 번호
					visit[i][j][0] = map[i][j];
					visit[i][j][1] = K;
					list.get(map[i][j] - 1).x = i;
					list.get(map[i][j] - 1).y = j;
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			list.get(i).dir = Integer.parseInt(st.nextToken()) - 1;
		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 4; k++) {
					sharkDir[i][j][k] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
		}

		int time = 0;
		count = 0;

		while (true) {
			if (time > 1000 || count == M - 1) {
				break;
			}

			for (int i = 0; i < M; i++) {
				int dir = list.get(i).dir;
				if (dir == -1)
					continue;
				boolean flag = false;
				for (int j = 0; j < 4; j++) {
					int nd = sharkDir[i][dir][j];
					int nx = list.get(i).x + dx[nd];
					int ny = list.get(i).y + dy[nd];
					if (0 > nx || nx >= N || 0 > ny || ny >= N)
						continue;

					if (visit[nx][ny][0] == 0) {
						flag = true;
						list.get(i).x = nx;
						list.get(i).y = ny;
						list.get(i).dir = nd;
						break;
					}
				}

				if (flag == false) {
					for (int j = 0; j < 4; j++) {
						int nd = sharkDir[i][dir][j];
						int nx = list.get(i).x + dx[nd];
						int ny = list.get(i).y + dy[nd];
						if (0 > nx || nx >= N || 0 > ny || ny >= N)
							continue;

						if (visit[nx][ny][0] == i + 1) {
							list.get(i).x = nx;
							list.get(i).y = ny;
							list.get(i).dir = nd;
							break;
						}
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visit[i][j][1] != 0) {
						visit[i][j][1] -= 1;
						if (visit[i][j][1] == 0) {
							visit[i][j][0] = 0;
						}
					}
				}
			}
			map = new int[N][N];
			for (int i = 0; i < M; i++) {

				int x = list.get(i).x;
				int y = list.get(i).y;

				if (x == -1)
					continue;

				if (map[x][y] == 0) {
					map[x][y] = i + 1;
				}

				else {
					count += 1;
					list.get(i).x = -1;
					list.get(i).y = -1;
					list.get(i).dir = -1;
				}
			}
			for (int i = 0; i < M; i++) {
				int x = list.get(i).x;
				int y = list.get(i).y;

				if (x == -1)
					continue;

				visit[x][y][0] = i + 1;
				visit[x][y][1] = K;
			}
			time++;
		}
		if (time <= 1000)
			System.out.println(time);
		else
			System.out.println(-1);
	}

}