package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	// 위 아래 왼쪽 오른쪽 앞 뒤
	static int[] dice = { 0, 0, 0, 0, 0, 0 };
	static int x;
	static int y;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		int command = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		while (command-- > 0) {
			int direction = Integer.parseInt(st.nextToken());
			if (move(direction))
				answer.append(dice[0]).append('\n');
		}
		System.out.println(answer);
	}

	public static boolean move(int direction) {
		try {
			int value = map[x + dx[direction - 1]][y + dy[direction - 1]];
			swap(direction);
			x += dx[direction - 1];
			y += dy[direction - 1];
			if (value == 0)
				map[x][y] = dice[1];
			else {
				dice[1] = value;
				map[x][y] = 0;
			}
			return true;
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

	public static void swap(int direction) {
		if (direction == 1)
			dice = new int[] { dice[2], dice[3], dice[1], dice[0], dice[4], dice[5] };
		else if (direction == 2)
			dice = new int[] { dice[3], dice[2], dice[0], dice[1], dice[4], dice[5] };
		else if (direction == 3)
			dice = new int[] { dice[5], dice[4], dice[2], dice[3], dice[0], dice[1] };
		else
			dice = new int[] { dice[4], dice[5], dice[2], dice[3], dice[1], dice[0] };
	}
}