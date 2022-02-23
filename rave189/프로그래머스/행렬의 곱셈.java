package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	/**
	 * �� ����� �־��� ��, ����� ������ ���ϴ� ����
	 * @param arr1 ���1
	 * @param arr2 ���2
	 * @return �� ����� ��
	 */
	public int[][] solution(int[][] arr1, int[][] arr2) {
		int[][] answer = new int[arr1.length][arr2[0].length];
		for (int i = 0; i < answer.length; i++) {
			for (int j = 0; j < answer[i].length; j++) {
				int sum = 0;
				for (int t = 0; t < arr2.length; t++)
					sum += arr1[i][t] * arr2[t][j];
				answer[i][j] = sum;
			}
		}
		return answer;
	}
}