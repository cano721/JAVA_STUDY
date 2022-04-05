import java.io.*;
import java.util.*;

public class BOJ_14500 {
	static int[][][] Polyomino = { { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } },
			{ { 0, 0 }, { 0, 1 }, { 0, 2 }, { 0, 3 } }, { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 3, 0 } },
			{ { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 1 } }, { { 0, 0 }, { 1, 0 }, { 2, 0 }, { 1, 1 } },
			{ { 1, 0 }, { 0, 1 }, { 1, 1 }, { 2, 1 } }, { { 1, 0 }, { 0, 1 }, { 1, 1 }, { 1, 2 } },
			{ { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 2 } }, { { 1, 0 }, { 1, 1 }, { 1, 2 }, { 0, 2 } },
			{ { 0, 0 }, { 1, 0 }, { 2, 0 }, { 2, 1 } }, { { 2, 0 }, { 0, 1 }, { 1, 1 }, { 2, 1 } },
			{ { 0, 0 }, { 0, 1 }, { 0, 2 }, { 1, 0 } }, { { 0, 0 }, { 1, 0 }, { 1, 1 }, { 1, 2 } },
			{ { 0, 0 }, { 1, 0 }, { 0, 1 }, { 2, 0 } }, { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 2, 1 } },
			{ { 0, 0 }, { 1, 0 }, { 1, 1 }, { 2, 1 } }, { { 1, 0 }, { 1, 1 }, { 0, 1 }, { 0, 2 } },
			{ { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 2 } }, { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 2, 0 } } };
	
	static int[][] map;
	static int max = 0; 
	static int row, col;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		map = new int[row][col];

		for (int i = 0; i < row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < col; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				cal(i, j);
			}
		}
		System.out.println(max);
	}

	private static void cal(int n, int m) {
		for (int i = 0; i < Polyomino.length; i++) {
			int tmp = 0;
			boolean isOk = true;
			for (int j = 0; j < 4; j++) {
				int nRow = n + Polyomino[i][j][0];
				int nCol = m + Polyomino[i][j][1];
				if (nRow < row && nCol < col) {
					tmp += map[nRow][nCol];
				} else {
					isOk = false;
					break;
				}
			}
			if (isOk)
				max = Math.max(tmp, max);
		}
	}
}
