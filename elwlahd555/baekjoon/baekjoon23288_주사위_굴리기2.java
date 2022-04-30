package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.concurrent.LinkedBlockingDeque;

public class baekjoon23288_주사위_굴리기2 {
	private static int map[][];
	private static int N, M, K, direct, X, Y;
	private static int dice[] = { 1, 2, 3, 4, 5, 6 };
	private static int dx[] = { -1, 1, 0, 0 };
	private static int dy[] = { 0, 0, -1, 1 };

	private static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		boolean visited[][] = null;
		map = new int[N + 1][M + 1];
		X = 1;
		Y = 1;
		direct = 3;
		int answer = 0;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		LinkedList<Point> que = new LinkedList<Point>();
		while (K > 0) {
			int nx = X + dx[direct];
			int ny = Y + dy[direct];
			if (nx <= 0 || nx > N || ny <= 0 || ny > M) {
				if (direct == 1 || direct == 3) {
					direct--;
				} else {
					direct++;
				}
				nx = X + dx[direct];
				ny = Y + dy[direct];
			}
			dice = lotation();
			X = nx;
			Y = ny;
			if (dice[5] > map[X][Y]) {
				switch (direct) {
				case 0:
					direct = 3;
					break;
				case 1:
					direct = 2;
					break;
				case 2:
					direct = 0;
					break;
				case 3:
					direct = 1;
					break;
				}
			} else if (dice[5] < map[X][Y]) {
				switch (direct) {
				case 0:
					direct = 2;
					break;
				case 1:
					direct = 3;
					break;
				case 2:
					direct = 1;
					break;
				case 3:
					direct = 0;
					break;
				}
			}
			que.add(new Point(X, Y));
			visited = new boolean[N + 1][M + 1];
			visited[X][Y] = true;
			int cnt = 0;
			while (!que.isEmpty()) {
				Point p = que.poll();
				for (int k = 0; k < 4; k++) {
					int x = p.x + dx[k];
					int y = p.y + dy[k];
					if (x <= 0 || x > N || y <= 0 || y > M) {
						continue;
					}
					if (map[x][y] == map[p.x][p.y] && !visited[x][y]) {
						que.add(new Point(x, y));
						visited[x][y] = true;
					}
				}
				cnt++;
			}
			answer += cnt * map[X][Y];
			K--;
		}
		System.out.println(answer);
	}

	private static int[] lotation() {
		int tempDice[] = new int[6];
		switch (direct) {
		case 0:
			tempDice[0] = dice[4];
			tempDice[1] = dice[0];
			tempDice[2] = dice[2];
			tempDice[3] = dice[3];
			tempDice[4] = dice[5];
			tempDice[5] = dice[1];

			break;
		case 1:
			tempDice[0] = dice[1];
			tempDice[1] = dice[5];
			tempDice[2] = dice[2];
			tempDice[3] = dice[3];
			tempDice[4] = dice[0];
			tempDice[5] = dice[4];

			break;
		case 2:
			tempDice[0] = dice[2];
			tempDice[1] = dice[1];
			tempDice[2] = dice[5];
			tempDice[3] = dice[0];
			tempDice[4] = dice[4];
			tempDice[5] = dice[3];

			break;
		case 3:
			tempDice[0] = dice[3];
			tempDice[1] = dice[1];
			tempDice[2] = dice[0];
			tempDice[3] = dice[5];
			tempDice[4] = dice[4];
			tempDice[5] = dice[2];
			break;
		}
		return tempDice;
	}
}
