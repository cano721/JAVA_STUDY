package elwlahd555.programmers;

public class 프로그래머스92344_파괴되지_않은_건물 {
	public static void main(String[] args) {
		int board[][] = { { 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5 }, { 5, 5, 5, 5, 5 } };
		int skill[][] = { { 1, 0, 0, 3, 4, 4 }, { 1, 2, 0, 2, 3, 2 }, { 2, 1, 0, 3, 1, 2 }, { 1, 0, 1, 3, 3, 1 } };
		System.out.println(solution(board, skill));
	}

	public static int solution(int[][] board, int[][] skill) {
		int answer = 0;
		int N = board.length;
		int M = board[0].length;
		int sum[][] = new int[N + 1][M + 1];
		for (int i = 0; i < skill.length; i++) {
			int power = skill[i][5];
			if (skill[i][0] == 1) {
				power *= -1;
			}
			int x1 = skill[i][1];
			int y1 = skill[i][2];
			int x2 = skill[i][3];
			int y2 = skill[i][4];

			sum[x1][y1] += power;
			sum[x1][y2 + 1] += (power * -1);
			sum[x2 + 1][y1] += (power * -1);
			sum[x2 + 1][y2 + 1] += power;

		}
		// 좌우
		for (int y = 1; y < M; y++) {
			for (int x = 0; x < N; x++) {
				sum[x][y] += sum[x][y - 1];
			}
		}
		// 상하
		for (int x = 1; x < N; x++) {
			for (int y = 0; y < M; y++) {
				sum[x][y] += sum[x - 1][y];
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (board[i][j] + sum[i][j] > 0) {
					answer++;
				}
			}
		}
		return answer;
	}
}
