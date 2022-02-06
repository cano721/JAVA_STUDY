package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	int[][] map;

	/**
	 * ����� ũ�Ⱑ �־�����.
	 * �� ��Ŀ��� 1���� row x column������ ���ڰ� �� �پ� ������� �����ִ�.
	 * �� ��, (x1, y1), (x2, y2)�� �־����� �ش� ������ ���簢������ �׵θ��� ���ڵ���
	 * �ð�������� �� ĭ�� ȸ�����Ѿ� �Ѵ�.
	 * �� ȸ������ ���� ���� ���� ���ϴ� ����
	 * @param rows ���� ũ��
	 * @param columns ���� ũ��
	 * @param queries �־����� ����
	 * @return �� ȸ������ ���� ���� ���� ������ �迭
	 */
	public int[] solution(int rows, int columns, int[][] queries) {
		int[] answer = new int[queries.length];
		init(rows, columns);
		for (int i = 0; i < queries.length; i++) {
			answer[i] = getMin(queries[i]);
		}
		return answer;
	}

	public void init(int row, int column) {
		map = new int[row + 1][column + 1];
		int count = 1;
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= column; j++) {
				map[i][j] = count++;
			}
		}
	}

	public int getMin(int[] query) {
		int x1 = query[0], y1 = query[1], x2 = query[2], y2 = query[3];
		int tmp = map[x1][y1];
		int min = tmp;
		for (int i = x1; i < x2; i++) {
			map[i][y1] = map[i + 1][y1];
			min = Math.min(min, map[i][y1]);
		}
		for (int i = y1; i < y2; i++) {
			map[x2][i] = map[x2][i + 1];
			min = Math.min(min, map[x2][i]);
		}
		for (int i = x2; i > x1; i--) {
			map[i][y2] = map[i - 1][y2];
			min = Math.min(min, map[i][y2]);
		}
		for (int i = y2; i > y1; i--) {
			map[x1][i] = map[x1][i - 1];
			min = Math.min(min, map[x1][i]);
		}
		map[x1][y1 + 1] = tmp;
		return min;
	}
}