class PG_행렬테두리회전하기 {
	public int[] solution(int rows, int columns, int[][] queries) {
		int cnt = 1;
		int[][] arr = new int[rows + 1][columns + 1];
		for (int i = 1; i <= rows; i++) {
			for (int j = 1; j <= columns; j++) {
				arr[i][j] = cnt;
				cnt++;
			}
		}

		int[] answer = new int[queries.length];

		for (int i = 0; i < queries.length; i++) {
			answer[i] = cal(arr, queries, i);
		}
		return answer;
	}

	public int cal(int[][] arr, int[][] queries, int loc) {
		int srow = queries[loc][0], scol = queries[loc][1];
		int erow = queries[loc][2], ecol = queries[loc][3];

		int tmp = arr[srow][scol];
		int min = tmp;

		for (int i = srow; i <= erow - 1; i++) {
			arr[i][scol] = arr[i + 1][scol];
			if (arr[i][scol] < min)
				min = arr[i][scol];
		}
		for (int i = scol; i <= ecol - 1; i++) {
			arr[erow][i] = arr[erow][i + 1];
			if (arr[erow][i] < min)
				min = arr[erow][i];
		}
		for (int i = erow; i > srow; i--) {
			arr[i][ecol] = arr[i - 1][ecol];
			if (arr[i][ecol] < min)
				min = arr[i][ecol];
		}
		for (int i = ecol; i > scol; i--) {
			arr[srow][i] = arr[srow][i - 1];
			if (arr[srow][i] < min)
				min = arr[srow][i];
		}
		arr[srow][scol + 1] = tmp;
		return min;
	}
}
