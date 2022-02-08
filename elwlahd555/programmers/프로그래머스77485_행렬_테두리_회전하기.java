package elwlahd555.programmers;

public class 프로그래머스77485_행렬_테두리_회전하기 {
	public static void main(String[] args) {
		int rows = 6;
		int columns = 6;
		int queries[][] = { { 2, 2, 5, 4 }, { 3, 3, 6, 6 }, { 5, 1, 6, 3 } };
		System.out.println(solution(rows, columns, queries));
	}

	public static int[] solution(int rows, int columns, int[][] queries) {
		int[] answer = new int[queries.length];
		int[][] arr = new int[rows + 1][columns + 1];
		int num = 1;
		for (int i = 1; i < rows + 1; i++) {
			for (int j = 1; j < columns + 1; j++) {
				arr[i][j] = num++;
			}
		}
		for (int k = 0; k < queries.length; k++) {
			int x1 = queries[k][0];
			int y1 = queries[k][1];
			int x2 = queries[k][2];
			int y2 = queries[k][3];
			int min = Integer.MAX_VALUE;
			int temp1 = arr[x1][y1];
			int temp2 = arr[x1][y1];
			for (int i = y1; i < y2; i++) {
				if (temp1 < min) {
					min = temp1;
				}
				temp2 = arr[x1][i + 1];
				arr[x1][i + 1] = temp1;
				temp1 = temp2;
			}
			for (int i = x1; i < x2; i++) {
				if (temp1 < min) {
					min = temp1;
				}
				temp2 = arr[i + 1][y2];
				arr[i + 1][y2] = temp1;
				temp1 = temp2;
			}
			for (int i = y2; i > y1; i--) {
				if (temp1 < min) {
					min = temp1;
				}
				temp2 = arr[x2][i - 1];
				arr[x2][i - 1] = temp1;
				temp1 = temp2;
			}
			for (int i = x2; i > x1; i--) {
				if (temp1 < min) {
					min = temp1;
				}
				temp2 = arr[i - 1][y1];
				arr[i - 1][y1] = temp1;
				temp1 = temp2;
			}
			answer[k] = min;
		}
		return answer;
	}
}
