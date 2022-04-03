package 전체유형문제풀이.프로그래머스;

import java.util.*;


/*
 * 행렬의 곱
 * https://mathbang.net/562
 * 
 * */

public class PG12949_행렬의곱셈 {

	public static void main(String[] args) {

		int[][] arr1 = { { 1, 4 }, { 3, 2 }, { 4, 1 } };
		int[][] arr2 = { { 3, 3 }, { 3, 3 } };

		int[][] result = solution(arr1, arr2);

		System.out.println(result);

	}

	private static int[][] solution(int[][] arr1, int[][] arr2) {
		int n = arr1.length;
		int m = arr2[0].length;
		int[][] answer = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int k = 0; k < arr1[0].length; k++) {
					answer[i][j] += arr1[i][k] * arr2[k][j];
				}

			}
		}

		return answer;
	}

}