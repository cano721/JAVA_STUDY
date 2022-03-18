package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int command = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		int[] dice = new int[6];
		st = new StringTokenizer(br.readLine());
		while (command-- > 0) {
			int direction = Integer.parseInt(st.nextToken()) - 1;
			int nextX = x + dx[direction];
			int nextY = y + dy[direction];
			try {
				int next = map[nextX][nextY];
				dice = move(dice, direction);
				if (next == 0)
					map[nextX][nextY] = dice[1];
				else {
					dice[1] = next;
					map[nextX][nextY] = 0;
				}
				x = nextX;
				y = nextY;
				answer.append(dice[0]).append('\n');
			} catch (ArrayIndexOutOfBoundsException e) {
			}
		}
		System.out.print(answer);
	}

	public static int[] move(int[] dice, int direction) {
		if (direction == 0) {
			return new int[] { dice[2], dice[3], dice[1], dice[0], dice[4], dice[5] };
		} else if (direction == 1) {
			return new int[] { dice[3], dice[2], dice[0], dice[1], dice[4], dice[5] };
		} else if (direction == 2) {
			return new int[] { dice[5], dice[4], dice[2], dice[3], dice[0], dice[1] };
		} else
			return new int[] { dice[4], dice[5], dice[2], dice[3], dice[1], dice[0] };
	}
}