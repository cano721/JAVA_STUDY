package elwlahd555.programmers;

import java.util.LinkedList;

public class 프로그래머스81302_거리두기_확인하기 {
	private static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) {
		String places[][] = { { "POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP" },
				{ "POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP" }, { "PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX" },
				{ "OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO" }, { "PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP" } };
		System.out.println(solution(places));
	}

	private static int[] solution(String[][] places) {
		int N = 5;
		int[] answer = new int[5];
		int dx[] = { -1, 1, 0, 0 };
		int dy[] = { 0, 0, -1, 1 };
		for (int k = 0; k < N; k++) {
			int ans = 1;
			LinkedList<Point> que = new LinkedList<Point>();
			char[][] arr = new char[N][N];
			for (int i = 0; i < N; i++) {
				arr[i] = places[k][i].toCharArray();
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == 'P') {
						que.add(new Point(i, j));
					}
				}
			}
			outer: while (!que.isEmpty()) {
				Point p = que.poll();
				for (int a = 0; a < 4; a++) {
					int x = p.x + dx[a];
					int y = p.y + dy[a];
					if (x < 0 || x >= N || y < 0 || y >= N || arr[x][y] == 'X') {
						continue;
					} else if (arr[x][y] == 'O') {
						for (int b = 0; b < 4; b++) {
							int xx = x + dx[b];
							int yy = y + dy[b];
							if (xx < 0 || xx >= N || yy < 0 || yy >= N || arr[xx][yy] == 'X'
									|| (p.x == xx && p.y == yy)) {
								continue;
							}
							if (arr[xx][yy] == 'P') {
								ans = 0;
								break outer;
							}
						}
					} else if (arr[x][y] == 'P') {
						ans = 0;
						break outer;
					}
				}
			}
			answer[k] = ans;
		}
		return answer;
	}
}
