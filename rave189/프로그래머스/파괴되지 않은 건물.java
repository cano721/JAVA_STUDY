package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	int[][] area;

	/**
	 * NxM ũ���� ������ �ִ�.
	 * �� ������ ��ų�� ����Ͽ� �ǹ��� �ı��ϰų� ġ���� �� �ִ�.
	 * ��ų�� (r1, c1) ~ (r2, c2) ������ �ı��ϰų� ġ���� �� �ִ�.
	 * ��ų�� ��� ����� �� �ı����� ���� �ǹ��� ������ ���ϴ� ����
	 * 
	 * Ǯ�� ����� �����س�
	 * �ٵ� try catch�� ���ܸ� üũ�ϸ鼭 �迭�� �����ϴ� ȿ���� 6, 7�� ��� ����
	 * area�� ũ�⸦ �ϳ� �÷��� ���ܾ��� �ٷ� �ݿ��ǰ� �ϴ� �������� ���
	 * @param board ����
	 * @param skill ��ų�� �迭
	 * @return �ı����� ���� �ǹ��� ����
	 */
	public int solution(int[][] board, int[][] skill) {
		area = new int[board.length + 1][board[0].length + 1];
		for (int[] s : skill) {
			int r1 = s[1], c1 = s[2], r2 = s[3], c2 = s[4];
			int degree = s[5] * (s[0] == 1 ? -1 : 1);

			area[r1][c1] += degree;
			area[r1][c2 + 1] -= degree;
			area[r2 + 1][c1] -= degree;
			area[r2 + 1][c2 + 1] += degree;
		}
		prefixSum();
		int answer = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] + area[i][j] >= 1)
					answer++;
			}
		}
		return answer;
	}

	public void prefixSum() {
		for (int i = 0; i < area.length; i++) {
			for (int j = 1; j < area[0].length; j++) {
				area[i][j] += area[i][j - 1];
			}
		}

		for (int i = 1; i < area.length; i++) {
			for (int j = 0; j < area[0].length; j++) {
				area[i][j] += area[i - 1][j];
			}
		}
	}
}