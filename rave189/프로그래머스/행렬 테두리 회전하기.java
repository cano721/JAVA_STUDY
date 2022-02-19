package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	int[][] map;

	/**
	 * 행렬의 크기가 주어진다.
	 * 이 행렬에는 1부터 row x column까지의 숫자가 한 줄씩 순서대로 적혀있다.
	 * 이 때, (x1, y1), (x2, y2)가 주어지면 해당 영역의 직사각형에서 테두리의 숫자들을
	 * 시계방향으로 한 칸씩 회전시켜야 한다.
	 * 각 회전마다 가장 작은 값을 구하는 문제
	 * @param rows 행의 크기
	 * @param columns 열의 크기
	 * @param queries 주어지는 영역
	 * @return 각 회전마다 가장 작은 값을 저장한 배열
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