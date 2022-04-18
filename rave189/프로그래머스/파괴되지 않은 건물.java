package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	int[][] area;

	/**
	 * NxM 크기의 지도가 있다.
	 * 이 지도에 스킬을 사용하여 건물을 파괴하거나 치료할 수 있다.
	 * 스킬은 (r1, c1) ~ (r2, c2) 범위를 파괴하거나 치유할 수 있다.
	 * 스킬을 모두 사용한 뒤 파괴되지 않은 건물의 개수를 구하는 문제
	 * 
	 * 풀이 방법은 생각해냄
	 * 근데 try catch로 예외를 체크하면서 배열에 저장하니 효율성 6, 7을 통과 못함
	 * area의 크기를 하나 늘려서 예외없이 바로 반영되게 하니 정답으로 통과
	 * @param board 지도
	 * @param skill 스킬의 배열
	 * @return 파괴되지 않은 건물의 개수
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