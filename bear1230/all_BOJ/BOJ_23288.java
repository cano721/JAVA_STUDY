import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K, dir, cur_x, cur_y, total, point;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dice = { 6, 3, 4, 5, 2, 1 };
	static Queue<int[]> que = new LinkedList<int[]>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		total = 0;
		dir = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cur_x = 0;
		cur_y = 0;
		for (int i = 0; i < K; i++) {
			check();
			move();
			point = 1;
			bfs();
			total += map[cur_x][cur_y] * point;
		}
		System.out.println(total);
	}

	private static void check() {
		int nx = cur_x + dx[dir];
		int ny = cur_y + dy[dir];

		if (0 > nx && dir == 3) {
			dir = 1;

		} else if (nx >= N && dir == 1) {
			dir = 3;
		} else if (0 > ny && dir == 2) {
			dir = 0;
		} else if (ny >= M && dir == 0) {
			dir = 2;
		}

	}

	private static void move() {

		cur_x = cur_x + dx[dir];
		cur_y = cur_y + dy[dir];

		dice_change();
		dir_change();
	}

	private static void dir_change() {
		if (dice[0] > map[cur_x][cur_y]) {
			dir += 1;
		} else if (dice[0] < map[cur_x][cur_y]) {
			dir -= 1;
		}

		if (dir >= 4) {
			dir -= 4;

		} else if (dir < 0) {
			dir += 4;
		}
	}

	private static void dice_change() {
		int[] tmp = new int[6];
		for (int i = 0; i < 6; i++) {
			tmp[i] = dice[i];
		}

		if (dir == 0) {
			dice[0] = tmp[1];
			dice[1] = tmp[5];
			dice[2] = tmp[0];
			dice[3] = tmp[3];
			dice[4] = tmp[4];
			dice[5] = tmp[2];
		} else if (dir == 1) {
			dice[0] = tmp[3];
			dice[1] = tmp[1];
			dice[2] = tmp[2];
			dice[3] = tmp[5];
			dice[4] = tmp[0];
			dice[5] = tmp[4];
		} else if (dir == 2) {
			dice[0] = tmp[2];
			dice[1] = tmp[0];
			dice[2] = tmp[5];
			dice[3] = tmp[3];
			dice[4] = tmp[4];
			dice[5] = tmp[1];
		} else if (dir == 3) {
			dice[0] = tmp[4];
			dice[1] = tmp[1];
			dice[2] = tmp[2];
			dice[3] = tmp[0];
			dice[4] = tmp[5];
			dice[5] = tmp[3];
		}
	}

	private static void bfs() {
		visit = new boolean[N][M];
		que.add(new int[] { cur_x, cur_y });
		visit[cur_x][cur_y] = true;
		while (!que.isEmpty()) {
			int[] cur = que.poll();
			int x = cur[0];
			int y = cur[1];
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (0 > nx || nx >= N || 0 > ny || ny >= M)
					continue;

				if (map[x][y] == map[nx][ny] && visit[nx][ny] == false) {
					point++;
					visit[nx][ny] = true;
					que.add(new int[] { nx, ny });
				}
			}
		}

	}
}
