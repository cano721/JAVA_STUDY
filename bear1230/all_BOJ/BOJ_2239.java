import java.io.*;
import java.util.*;

public class Main {
	static int[][] sudoku;
	static boolean[][] row, col, square;
	static boolean visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sudoku = new int[9][9];
		row = new boolean[9][10];
		col = new boolean[9][10];
		square = new boolean[9][10];

		for (int i = 0; i < 9; i++) {
			char[] tmp = br.readLine().toCharArray();
			for (int j = 0; j < 9; j++) {
				sudoku[i][j] = tmp[j] - '0';
				if (sudoku[i][j] != 0) {
					col[j][sudoku[i][j]] = true;
					row[i][sudoku[i][j]] = true;
					square[changeIdx(i, j)][sudoku[i][j]] = true;
				}
			}
		}
		dfs(0);
	}

	private static void dfs(int cnt) {
		if (visit)
			return;
		if (cnt == 81) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++)
					System.out.print(sudoku[i][j]);
				System.out.println();
			}
			visit = true;
			return;
		}

		int x = cnt % 9;
		int y = cnt / 9;

		if (sudoku[y][x] != 0)
			dfs(cnt + 1);
		else {
			for (int k = 1; k <= 9; k++) {
				if (!col[x][k] && !row[y][k] && !square[changeIdx(y, x)][k]) {
					sudoku[y][x] = k;
					col[x][k] = true;
					row[y][k] = true;
					square[changeIdx(y, x)][k] = true;
					dfs(cnt + 1);
					sudoku[y][x] = 0;
					col[x][k] = false;
					row[y][k] = false;
					square[changeIdx(y, x)][k] = false;
				}
			}
		}
	}

	private static int changeIdx(int y, int x) {
		return (y / 3) * 3 + x / 3;
	}
}