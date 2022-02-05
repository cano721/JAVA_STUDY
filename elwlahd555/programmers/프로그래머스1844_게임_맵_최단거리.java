package elwlahd555.programmers;

import java.util.LinkedList;

public class 프로그래머스1844_게임_맵_최단거리 {
	private static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		int[][] maps = { { 1, 0, 1, 1, 1 }, { 1, 0, 1, 0, 1 }, { 1, 0, 1, 1, 1 }, { 1, 1, 1, 0, 1 },
				{ 0, 0, 0, 0, 1 } };
		System.out.println(solution(maps));
	}

	private static int solution(int[][] maps) {
		int answer = -1;
		LinkedList<Point> que = new LinkedList<Point>();
		int dx[] = { -1, 1, 0, 0 };
		int dy[] = { 0, 0, -1, 1 };
		que.add(new Point(0, 0));
		maps[0][0] = 0;
		int temp = 0;
		int n = 0;
		int m = 0;
		while (!que.isEmpty()) {

			int size = que.size();

			for (int i = 0; i < size; i++) {
				Point p = que.poll();
				if (p.x == maps.length - 1 && p.y == maps[0].length - 1) {
					n = p.x;
					m = p.y;
					answer = ++temp;
					break;
				}
				for (int k = 0; k < 4; k++) {
					int x = p.x + dx[k];
					int y = p.y + dy[k];
					if (x >= 0 && x < maps.length && y >= 0 && y < maps[0].length && maps[x][y] == 1) {
						que.add(new Point(x, y));
						maps[x][y] = 0;
					}
				}
			}
			temp++;
		}
		if (n != maps.length - 1 || m != maps[0].length - 1) {
			answer = -1;
		}
		return answer;
	}
}
