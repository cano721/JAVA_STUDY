class PG_카카오프렌즈컬러링북 {
	static int num, max;
	static int temp = 0;
	public int[] solution(int m, int n, int[][] picture) {
		num = 0;
		max = 0;
		int[] answer = new int[2];
		answer[0] = num;
		answer[1] = max;

		boolean[][] check = new boolean[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (picture[i][j] != 0 && !check[i][j]) {
					num++;
					dfs(i, j, picture, check);
				}
				if (temp > max)
					max = temp;
				temp = 0;
			}
		}

		answer[0] = num;
		answer[1] = max;

		return answer;
	}
	
	public static void dfs(int x, int y, int[][] picture, boolean[][] check) {
		if (check[x][y]) return;

		check[x][y] = true;
		temp++;

		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= picture.length || ny < 0 || ny >= picture[0].length)
				continue;

			if (picture[x][y] == picture[nx][ny] && !check[nx][ny]) {
				dfs(nx, ny, picture, check);
			}
		}
	}


}