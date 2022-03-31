package elwlahd555.programmers;

public class 프로그래머스92345_사라지는_발판 {
	public static void main(String[] args) {
		int board[][] = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };
		int aloc[] = { 1, 0 };
		int bloc[] = { 1, 2 };
		System.out.println(solution(board, aloc, bloc));
	}

	private static int boardRowLen, boardColLen;
	private static int dx[] = { -1, 1, 0, 0 };
	private static int dy[] = { 0, 0, -1, 1 };

	private static class Result {
		boolean win;
		int count;

		public Result(boolean win, int count) {
			this.win = win;
			this.count = count;
		}
	}

	public static int solution(int[][] board, int[] aloc, int[] bloc) {
		boardRowLen = board.length;
		boardColLen = board[0].length;
		return dfs(aloc[0], aloc[1], bloc[0], bloc[1], 0, 0, board).count;
	}

	private static Result dfs(int ax, int ay, int bx, int by, int aDepth, int bDepth, int[][] board) {
		boolean win = false;
		int minCount = 5 * 5;
		int maxCount = aDepth + bDepth;

		if (aDepth == bDepth && board[ax][ay] == 1) {
			for (int i = 0; i < 4; i++) {
				int axTmp = ax + dx[i];
				int ayTmp = ay + dy[i];
				if (isValid(axTmp, ayTmp, board)) {
					board[ax][ay] = 0;

					Result d = dfs(axTmp, ayTmp, bx, by, aDepth + 1, bDepth, board);
					win = win || !d.win;
					if (!d.win)
						minCount = Math.min(minCount, d.count);
					else
						maxCount = Math.max(maxCount, d.count);

					board[ax][ay] = 1;
				}
			}
		} else if (aDepth > bDepth && board[bx][by] == 1) {
			for (int i = 0; i < 4; i++) {
				int bxTmp = bx + dx[i];
				int byTmp = by + dy[i];
				if (isValid(bxTmp, byTmp, board)) {
					board[bx][by] = 0;

					Result d = dfs(ax, ay, bxTmp, byTmp, aDepth, bDepth + 1, board);
					win = win || !d.win;
					if (!d.win)
						minCount = Math.min(minCount, d.count);
					else
						maxCount = Math.max(maxCount, d.count);

					board[bx][by] = 1;
				}
			}
		}

		return new Result(win, win ? minCount : maxCount);
	}

	public static boolean isValid(int x, int y, int[][] board) {
		if (x < 0 || x >= boardRowLen || y < 0 || y >= boardColLen || board[x][y] == 0)
			return false;
		return true;
	}
}
