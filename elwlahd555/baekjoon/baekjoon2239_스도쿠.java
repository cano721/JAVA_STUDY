package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class baekjoon2239_스도쿠 {
	private static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	private static int N = 9;
	private static int arr[][];
	private static ArrayList<Point> arrlist;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[N][N];
		arrlist = new ArrayList<Point>();
		for (int i = 0; i < N; i++) {
			String s = bf.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = s.charAt(j) - '0';
				if (arr[i][j] == 0) {
					arrlist.add(new Point(i, j));
				}
			}
		}
		sdoku(0);

		
		System.out.println(result);
	}
	private static String result="";
	private static void sdoku(int cnt) {
		// TODO Auto-generated method stub
		if (cnt == arrlist.size()) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					result+=arr[i][j];
				}
				result+="\n";
			}
			System.out.println(result);
			System.exit(0);
		}
		Point p = arrlist.get(cnt);
		for (int i = 1; i <= N; i++) {
			arr[p.x][p.y] = i;
			if (row(p.x)) {
				continue;
			}
			if (column(p.y)) {
				continue;
			}
			if (matrix(p)) {
				continue;
			}
			sdoku(cnt + 1);
		}
		arr[p.x][p.y]=0;

	}
	private static boolean matrix(Point p) {
		// TODO Auto-generated method stub
		boolean visit[] = new boolean[N + 1];
			for (int i = (p.x / 3) * 3; i < ((p.x / 3) +1) *3; i++) {
				for (int j = (p.y / 3) * 3; j < ((p.y / 3) +1) *3; j++) {
					if (arr[i][j] > 0) {
						if (visit[arr[i][j]]) {
							return true;
						} else {
							visit[arr[i][j]] = true;
							
						}
					}
				}
			}
		return false;
	}

	private static boolean column(int y) {
		// TODO Auto-generated method stub
		boolean visit[] = new boolean[N + 1];
		for (int i = 0; i < N; i++) {
			if (arr[i][y] > 0) {
				if (visit[arr[i][y]]) {
					return true;
				} else {
					visit[arr[i][y]] = true;

				}
			}
		}
		return false;
	}

	private static boolean row(int x) {
		// TODO Auto-generated method stub
		boolean visit[] = new boolean[N + 1];
		for (int i = 0; i < N; i++) {
			if (arr[x][i] > 0) {
				if (visit[arr[x][i]]) {
					return true;
				} else {
					visit[arr[x][i]] = true;

				}

			}
		}
		return false;
	}
}
