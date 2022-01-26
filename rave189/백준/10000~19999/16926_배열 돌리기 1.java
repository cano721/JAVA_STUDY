package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		while (r-- > 0)
			rotation();
		print();
	}

	public static void rotation() {
		int count = Math.min(map.length, map[0].length) / 2;
		for (int i = 0; i < count; i++)
			outLineRotation(i);
	}

	public static void outLineRotation(int n) {
		int tmp = map[n][n];
		int rightCol = map[n].length - n - 1;
		int bottomRow = map.length - n - 1;
		for (int i = n; i < rightCol; i++)
			map[n][i] = map[n][i + 1];
		for (int i = n; i < bottomRow; i++)
			map[i][rightCol] = map[i + 1][rightCol];
		for (int i = rightCol; i > n; i--)
			map[bottomRow][i] = map[bottomRow][i - 1];
		for (int i = bottomRow; i > n; i--)
			map[i][n] = map[i - 1][n];
		map[n + 1][n] = tmp;
	}

	public static void print() {
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++)
				answer.append(map[i][j]).append(' ');
			answer.append('\n');
		}
		System.out.println(answer);
	}
}