class Solution {
	static int[][] map;
	static int one = 0;
	static int zero = 0;

	public int[] solution(int[][] arr) {
		int[] answer = new int[2];
		map = arr;
		dfs(arr.length, 0, 0);

		answer[0] = zero;
		answer[1] = one;
		return answer;
	}

	public boolean check(int n, int x, int y) {
		int tmp = map[x][y];

		for (int i = x; i < x + n; i++) {
			for (int j = y; j < y + n; j++) {
				if (tmp != map[i][j])
					return false;
			}
		}

		if (tmp == 0)
			zero++;
		else
			one++;

		return true;
	}

	public void dfs(int n, int x, int y) {
		if (n == 1) {
			if (map[x][y] == 1)
				one++;
			else
				zero++;

			return;
		}

		if (check(n, x, y)) {
			return;
		}

		dfs(n / 2, x, y);
		dfs(n / 2, x + n / 2, y);
		dfs(n / 2, x, y + n / 2);
		dfs(n / 2, x + n / 2, y + n / 2);
	}
}
