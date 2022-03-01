package programmers;

public class d220301_행렬의곱셈 {

	public static void main(String[] args) {
		int[][] arr1 = {{1, 4}, {3, 2}, {4, 1}};
		int[][] arr2 = {{3, 3}, {3, 3}};
		int[][] arr = solution(arr1, arr2);
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] +" ");
			}
			System.out.println();
		}
	}

	private static int[][] solution(int[][] arr1, int[][] arr2) {
		int[][] answer = new int[arr1.length][arr2[0].length];
		for (int i = 0; i < arr1.length; i++) {
			for (int j = 0; j < arr2[0].length; j++) {
				for(int k = 0; k < arr1[i].length; k++) {
					answer[i][j] += arr1[i][k] * arr2[k][j];
				}
				
			}
		}
		return answer;
	}

}
