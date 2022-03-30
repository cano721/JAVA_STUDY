class Solution {

	static int[] dy = { 0, 0, 1, -1 };
	static int[] dx = { 1, -1, 0, 0 };
	static int col;
	static int row;

	public int solution(int[][] board, int[] aloc, int[] bloc) {
		int answer = 0;
		col = board.length;
		row = board[0].length;
		int dfs = dfs(aloc[0], aloc[1], bloc[0], bloc[1], board);
		answer = dfs;
		return answer;
	}

	public static int dfs(int cy, int cx, int ry, int rx, int[][] board) {
		if (board[cy][cx] == 0) {
			return 0;
		}
		int ret = 0;

		for (int i = 0; i < 4; i++) {
			int ny = cy + dy[i];
			int nx = cx + dx[i];
			if (0 <= ny && ny < col && 0 <= nx && nx < row && board[ny][nx] != 0) {
				board[cy][cx] = 0;
				int val = dfs(ry, rx, ny, nx, board) + 1;
				board[cy][cx] = 1;

				if (ret % 2 == 0 && val % 2 == 1)
					ret = val;
				else if (ret % 2 == 0 && val % 2 == 0)
					ret = Math.max(ret, val);
				else if (ret % 2 == 1 && val % 2 == 1)
					ret = Math.min(ret, val);
			}
		}
		return ret;
	}
}
