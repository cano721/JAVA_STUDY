import java.io.*;
import java.util.*;

public class BOJ_14500 {
	static int[][][] polynominos = {
		{{0, 0}, {0, 1}, {1, 0}, {1, 1}},
		{{0, 0}, {0, 1}, {0, 2}, {0, 3}}, {{0, 0}, {1, 0}, {2, 0}, {3, 0}},
		{{0, 0}, {0, 1}, {0, 2}, {1, 1}}, {{0, 0}, {1, 0}, {2, 0}, {1, 1}}, {{1, 0}, {0, 1}, {1, 1}, {2, 1}}, {{1, 0}, {0, 1}, {1, 1}, {1, 2}},
		{{0, 0}, {0, 1}, {0, 2}, {1, 2}}, {{1, 0}, {1, 1}, {1, 2}, {0, 2}}, {{0, 0}, {1, 0}, {2, 0}, {2, 1}}, {{2, 0}, {0, 1}, {1, 1}, {2, 1}}, {{0, 0}, {0, 1}, {0, 2}, {1, 0}}, {{0, 0}, {1, 0}, {1, 1}, {1, 2}}, {{0, 0}, {1, 0}, {0, 1}, {2, 0}}, {{0, 0}, {0, 1}, {1, 1}, {2, 1}},
		{{0, 0}, {1, 0}, {1, 1}, {2, 1}}, {{1, 0}, {1, 1}, {0, 1}, {0, 2}}, {{0, 0}, {0, 1}, {1, 1}, {1, 2}}, {{0, 1}, {1, 1}, {1, 0}, {2, 0}}
	};
	static int[][] matrix;
	static int maxRes = 0, Row, Col;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Row = Integer.parseInt(st.nextToken());
		Col = Integer.parseInt(st.nextToken());
		matrix = new int[Row][Col];
		for (int i = 0; i < Row; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < Col; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < Row; i++) {
			for(int j = 0; j < Col; j++) {
				calculate(i, j);
			}
		}
		System.out.println(maxRes);
	}

	private static void calculate(int row, int col) {
		for (int i = 0; i < polynominos.length; i++) {
			int tmpRes = 0;
			boolean isOk = true;
			for (int j = 0; j < 4; j++) {
				int nRow = row + polynominos[i][j][0];
				int nCol = col + polynominos[i][j][1];
				if(nRow < Row && nCol < Col) {
					tmpRes += matrix[nRow][nCol];
				} else {
					isOk = false;
					break;
				}
			}
			if(isOk) maxRes = Math.max(tmpRes, maxRes);
		}
	}
}