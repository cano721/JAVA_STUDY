package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static char[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < n; j++)
				map[i][j] = line.charAt(j);
		}
		int answer = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				answer = Math.max(answer, rowCnt(j));
				answer = Math.max(answer, colCnt(i));
				Point cur = new Point(i, j);
				if (j < n - 1 && map[i][j] != map[i][j + 1]) {
					Point next = new Point(i, j + 1);
					swap(cur, next);
					answer = Math.max(answer, rowCnt(i));
					answer = Math.max(answer, colCnt(j));
					answer = Math.max(answer, colCnt(j + 1));
					swap(cur, next);
				}

				if (i < n - 1 && map[i][j] != map[i + 1][j]) {
					Point next = new Point(i + 1, j);
					swap(cur, next);
					answer = Math.max(answer, colCnt(j));
					answer = Math.max(answer, rowCnt(i));
					answer = Math.max(answer, rowCnt(i + 1));
					swap(cur, next);
				}
			}
		}
		System.out.println(answer);
	}

	public static void swap(Point cur, Point next) {
		char tmp = map[cur.x][cur.y];
		map[cur.x][cur.y] = map[next.x][next.y];
		map[next.x][next.y] = tmp;
	}

	public static int rowCnt(int n) {
		int max = 1;
		int count = 1;
		for (int i = 1; i < map.length; i++) {
			if (map[n][i] == map[n][i - 1]) {
				count++;
				max = Math.max(max, count);
			} else
				count = 1;
		}
		return max;
	}

	public static int colCnt(int n) {
		int max = 1;
		int count = 1;
		for (int i = 1; i < map.length; i++) {
			if (map[i][n] == map[i - 1][n]) {
				count++;
				max = Math.max(max, count);
			} else
				count = 1;
		}
		return max;
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}