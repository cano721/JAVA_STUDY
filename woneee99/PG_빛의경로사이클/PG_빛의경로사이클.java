import java.util.*;

/*
 * sol)
 * 방향 d의 순서는 반시계방향으로 이어지게 정함 
 *  아래 -> 왼 -> 위 -> 오른
 * 방문 처리를 하면서 빛의 경로 구함
 */

public class PG_빛의경로사이클 {
	static int[] dr = { -1, 0, 1, 0 }, dc = { 0, -1, 0, 1 };
	static boolean[][][] visited;
	static ArrayList<Integer> arr = new ArrayList<>();

	public static void main(String[] args) {
		String[] grid = { "SL", "LR" };

		int row = grid.length;
		int col = grid[0].length();
		visited = new boolean[row][col][4];


		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				for (int k = 0; k < 4; k++) {
					if (!visited[i][j][k])
						arr.add(cal(grid, row, col, k));
				}
			}
		}
		
		int[] answer = new int[arr.size()];
		for(int i=0; i<answer.length; i++) answer[i] = arr.get(i);
		Arrays.sort(answer);
	}

	static int cal(String[] grid, int r, int c, int k) {
		int dir = 0;

		while (true) {
			if (visited[r][c][k])
				break;

			dir++;
			visited[r][c][k] = true;
			if (grid[r].charAt(c) == 'L')
				dir = dir == 0 ? 3 : dir - 1;
			else if (grid[r].charAt(c) == 'R')
				dir = dir == 3 ? 0 : dir + 1;

			r = (r + dr[dir] + r) % r;
			c = (c + dr[dir] + c) % c;
		}

		return dir;
	}
}
