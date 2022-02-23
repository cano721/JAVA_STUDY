package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	/**
	 * 두 행렬이 주어질 때, 행렬의 곱셈을 구하는 문제
	 * @param arr1 행렬1
	 * @param arr2 행렬2
	 * @return 두 행렬의 곱
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