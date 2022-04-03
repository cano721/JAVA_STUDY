package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	int[][] map;
	int[][] dp;

	/**
	 * 0�� 1�� �̷���� �迭�� �־�����.
	 * �� �迭���� 1�� �̷���� ���� ū ���簢���� ���̸� ���ϴ� ����
	 * @param board �迭
	 * @return 1�� �̷���� ���� ū ���簢���� ����
	 */
	public int solution(int[][] board) {
		init(board);
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				if (map[i][j] == 0)
					continue;
				dp[i][j] = 1;
				try {
					int left = dp[i][j - 1];
					int up = dp[i - 1][j];
					int diagonal = dp[i - 1][j - 1];
					if (left == up && up == diagonal)
						dp[i][j] = left + 1;
					else if (left > 0 && up > 0 && diagonal > 0)
						dp[i][j] = Math.min(left, Math.min(up, diagonal)) + 1;
				} catch (Exception e) {
				}
			}
		}
		int answer = 0;
		for (int i = 0; i < dp.length; i++)
			for (int j = 0; j < dp[0].length; j++)
				answer = Math.max(answer, dp[i][j]);
		return answer * answer;
	}

	public void init(int[][] board) {
		map = board;
		dp = new int[board.length][board[0].length];
	}
}