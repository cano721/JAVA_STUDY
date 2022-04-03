package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][][] poliomino = { { { 1, 1, 1, 1 } }, { { 1, 1 }, { 1, 1 } }, { { 1, 0 }, { 1, 0 }, { 1, 1 } },
			{ { 1, 0 }, { 1, 1 }, { 0, 1 } }, { { 1, 1, 1 }, { 0, 1, 0 } }, { { 0, 1 }, { 0, 1 }, { 1, 1 } },
			{ { 0, 1 }, { 1, 1 }, { 1, 0 } } };
	static int[][] map;
	static int answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		bruteforce();
		System.out.println(answer);
	}

	public static void bruteforce() {
		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[0].length; j++)
				for (int t = 0; t < poliomino.length; t++)
					answer = Math.max(answer, getMaxPlace(i, j, poliomino[t]));
	}

	public static int getMaxPlace(int x, int y, int[][] tetromino) {
		int sum = -1;
		for (int j = 0; j < 4; j++) {
			tetromino = rotate(tetromino);
			sum = Math.max(sum, place(x, y, tetromino));
		}
		return sum;
	}

	public static int[][] rotate(int[][] tetromino) {
		int[][] rotate = new int[tetromino[0].length][tetromino.length];
		for (int i = 0; i < rotate.length; i++)
			for (int j = 0, t = tetromino.length - 1; j < rotate[0].length; j++, t--)
				rotate[i][j] = tetromino[t][i];
		return rotate;
	}

	public static int[][] symmetry(int[][] tetromino) {
		int[][] symmetry = new int[tetromino.length][tetromino[0].length];
		for (int i = 0; i < symmetry.length; i++)
			for (int j = 0, t = symmetry[0].length - 1; j < symmetry[0].length; j++, t--)
				symmetry[i][j] = tetromino[i][t];
		return symmetry;
	}

	public static int place(int x, int y, int[][] tetromino) {
		int sum = 0;
		for (int i = 0; i < tetromino.length; i++) {
			for (int j = 0; j < tetromino[0].length; j++) {
				if (tetromino[i][j] == 0)
					continue;
				try {
					sum += map[x + i][y + j];
				} catch (ArrayIndexOutOfBoundsException e) {
					return -1;
				}
			}
		}
		return sum;
	}
}