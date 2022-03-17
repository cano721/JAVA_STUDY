package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baekjoon14499_주사위_굴리기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int map[][] = new int[N][M];
		int dice[] = new int[7];
		int dx[] = { 0, 0, 0, -1, 1 };
		int dy[] = { 0, 1, -1, 0, 0 };
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		int now = 0;
		for (int i = 0; i < K; i++) {
			now = Integer.parseInt(st.nextToken());
			if (x + dx[now] < 0 || x + dx[now] >= N || y + dy[now] < 0 || y + dy[now] >= M) {
				continue;
			}
			x += dx[now];
			y += dy[now];
			dice = diceMove(dice, now);
			if (map[x][y] == 0) {
				map[x][y] = dice[1];
			} else {
				dice[1] = map[x][y];
				map[x][y] = 0;
			}
			System.out.println(dice[6]);
		}
	}

	private static int[] diceMove(int[] dice, int now) {
		int nextDice[] = new int[7];
		switch (now) {
		case 1:
			nextDice[1] = dice[3];
			nextDice[2] = dice[2];
			nextDice[3] = dice[6];
			nextDice[4] = dice[1];
			nextDice[5] = dice[5];
			nextDice[6] = dice[4];
			break;
		case 2:
			nextDice[1] = dice[4];
			nextDice[2] = dice[2];
			nextDice[3] = dice[1];
			nextDice[4] = dice[6];
			nextDice[5] = dice[5];
			nextDice[6] = dice[3];

			break;
		case 3:
			nextDice[1] = dice[2];
			nextDice[2] = dice[6];
			nextDice[3] = dice[3];
			nextDice[4] = dice[4];
			nextDice[5] = dice[1];
			nextDice[6] = dice[5];

			break;
		case 4:
			nextDice[1] = dice[5];
			nextDice[2] = dice[1];
			nextDice[3] = dice[3];
			nextDice[4] = dice[4];
			nextDice[5] = dice[6];
			nextDice[6] = dice[2];

			break;

		}
		return nextDice;
	}

}
