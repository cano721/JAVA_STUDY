package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class baekjoon11559_puyo_puyo {
	private static class Point {
		int x, y, puyo;

		public Point(int x, int y, int puyo) {
			super();
			this.x = x;
			this.y = y;
			this.puyo = puyo;
		}

	}

	private static char arr[][];
	private static LinkedList<Point> que;
	private static int dx[] = { -1, 1, 0, 0 };
	private static int dy[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new char[12][6];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		que = new LinkedList<Point>();
		int answer = 0;
		while (check()) {
			while (!que.isEmpty()) {
				Point p = que.poll();
				arr[p.x][p.y] = '.';
				for (int k = 0; k < 4; k++) {
					int x = p.x + dx[k];
					int y = p.y + dy[k];
					if (x < 0 || x >= 12 || y < 0 || y >= 6) {
						continue;
					}
					if (arr[x][y] == p.puyo) {
						que.add(new Point(x, y, arr[x][y]));
					}
				}
			}
			arr = downMap();
			answer++;
		}
		System.out.println(answer);
	}

	private static char[][] downMap() {
		char temp[][] = new char[12][6];
		for (int i = 0; i < 6; i++) {
			int k = 11;
			for (int j = 11; j >= 0; j--) {
				if (arr[j][i] != '.') {
					temp[k][i] = arr[j][i];
					k--;
				}
			}
			for (int j = k; j >= 0; j--) {
				temp[j][i] = '.';
			}
		}
		return temp;
	}

	private static boolean check() {
		LinkedList<Point> temp = new LinkedList<Point>();
		boolean tempVisited[][] = new boolean[12][6];
		Point start=null;
		outer: for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				if (arr[i][j] != '.') {
					start=new Point(i,j,arr[i][j]);
					temp.add(new Point(i, j, arr[i][j]));
					tempVisited[i][j] = true;
					break outer;
				}
			}
		}
		int num= temp.size();
		while (!temp.isEmpty()) {
			int size = temp.size();
			for (int i = 0; i < size; i++) {
				Point p = temp.poll();
				for (int k = 0; k < 4; k++) {
					int x = p.x + dx[k];
					int y = p.y + dy[k];
					if (x < 0 || x >= 12 || y < 0 || y >= 6 || tempVisited[x][y] || arr[x][y] == '.') {
						continue;
					}
					if (p.puyo == arr[x][y]) {
						temp.add(new Point(x, y, arr[x][y]));
						tempVisited[x][y] = true;
					}
				}
			}
			if(temp.size()>0) {
				num+=temp.size();
			}else {
				if (num >= 4) {
					que.add(start);
				}
				temp.clear();
				outer: for (int i = 0; i < arr.length; i++) {
					for (int j = 0; j < arr[0].length; j++) {
						if (arr[i][j] != '.' && !tempVisited[i][j]) {
							start=new Point(i,j,arr[i][j]);
							temp.add(new Point(i, j, arr[i][j]));
							tempVisited[i][j] = true;
							num=1;
							break outer;
						}
					}
				}
			}
		}
		if (que.size() > 0) {
			return true;
		}
		return false;
	}
}
