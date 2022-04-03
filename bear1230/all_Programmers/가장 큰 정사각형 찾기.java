class Solution {
	public int solution(int[][] board) {
		int row = board.length;
		int col = board[0].length;
		int answer = 0;

		if (Math.min(row, col) < 2)
			return 1;

		for (int i = 1; i < row; i++) {
			for (int j = 1; j < col; j++) {
				if (board[i][j] != 0) {
					board[i][j] = Math.min(board[i - 1][j - 1], Math.min(board[i - 1][j], board[i][j - 1])) + 1;
					answer = Math.max(answer, board[i][j]);
				}
			}
		}

		return answer * answer;
	}
}
