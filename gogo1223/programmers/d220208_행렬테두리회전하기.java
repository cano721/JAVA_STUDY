package programmers;

public class d220208_행렬테두리회전하기 {

	public static void main(String[] args) {
		int rows = 6; 
		int columns = 6; 
		int[][] queries = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};
		int[] answer = solution(rows, columns, queries);
		for (int i = 0; i < answer.length; i++) {
			System.out.print(answer[i] + " ");
		}
	}

	private static int[] solution(int rows, int columns, int[][] queries) {
		int[] answer = new int[queries.length];
		int[][] arr = new int[rows][columns];
		int num = 1;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				arr[i][j] = num++;
			}
		}
		int index = 0;
		for (int[] query : queries) {
			int x1 = query[0] - 1; int y1 = query[1] - 1; 
			int x2 = query[2] - 1; int y2 = query[3] - 1; 
			int temp = arr[x1][y1];
			int min = temp;
			
			for (int i = x1; i < x2; i++) {
				arr[i][y1] = arr[i+1][y1];
				min = Math.min(min, arr[i][y1]);
			}
			for (int i = y1; i < y2; i++) {
				arr[x2][i] = arr[x2][i+1];
				min = Math.min(min, arr[x2][i]);
			}
			for (int i = x2; i > x1; i--) {
				arr[i][y2] = arr[i-1][y2];
				min = Math.min(min, arr[i][y2]);
			}
			for (int i = y2; i > y1; i--) {
				arr[x1][i] = arr[x1][i-1];
				min = Math.min(min, arr[x1][i]);
			}
			arr[x1][y1+1] = temp;
			answer[index++] = min;
		}
		return answer;
	}

}
