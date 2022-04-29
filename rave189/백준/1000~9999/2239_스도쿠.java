package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 스도쿠가 주어지면 해결하는 문제
 * 정답은 사전식으로 가장 앞서는 것을 출력한다.
 * @author Rave
 *
 */
public class Main {

	static final int SIZE = 9, DIVIDE = 3;
	static int[][] map = new int[SIZE][SIZE];
	static ArrayList<Point> zeros = new ArrayList<>();

	/**
	 * 0을 배열로 보관한 후 하나씩 숫자를 대입해본다.
	 * 1부터 9까지 순서대로 대입해보며 가장 먼저 완성되는 스도쿠가 사전식으로 가장 앞서는 것이다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < SIZE; i++) {
			String line = br.readLine();
			for (int j = 0; j < SIZE; j++) {
				map[i][j] = line.charAt(j) - '0';
				if (map[i][j] == 0)
					zeros.add(new Point(i, j));
			}
		}
		bruteforce(0);
	}

	public static void bruteforce(int cur) {
		if (cur == zeros.size()) {
			print();
			System.exit(0);
		}

		Point p = zeros.get(cur);
		for (int i = 1; i <= 9; i++) {
			if (isPossibleRow(p, i) && isPossibleCol(p, i) && isPossibleBlock(p, i)) {
				map[p.x][p.y] = i;
				bruteforce(cur + 1);
				map[p.x][p.y] = 0;
			}
		}
	}

	public static boolean isPossibleRow(Point p, int num) {
		for (int i = 0; i < SIZE; i++)
			if (map[p.x][i] == num)
				return false;
		return true;
	}

	public static boolean isPossibleCol(Point p, int num) {
		for (int i = 0; i < SIZE; i++)
			if (map[i][p.y] == num)
				return false;
		return true;
	}

	public static boolean isPossibleBlock(Point p, int num) {
		int x = (p.x / DIVIDE) * DIVIDE;
		int y = (p.y / DIVIDE) * DIVIDE;
		for (int i = x; i < x + DIVIDE; i++)
			for (int j = y; j < y + DIVIDE; j++)
				if (map[i][j] == num)
					return false;
		return true;
	}

	public static void print() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++)
				System.out.print(map[i][j]);
			System.out.println();
		}
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}
}