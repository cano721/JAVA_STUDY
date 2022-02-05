import java.util.*;

class Solution {
	class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public int solution(int[][] maps) {
		int row = maps.length;
		int col = maps[0].length;
		int[][] d = new int[row][col];
		boolean[][] visit = new boolean[row][col];

		int[] dx = { 0, 0, 1, -1 };
		int[] dy = { 1, -1, 0, 0 };

		Queue<Point> que = new LinkedList<Solution.Point>();
		que.offer(new Point(0, 0));
		visit[0][0] = true;
		d[0][0] = 1;

		while (!que.isEmpty()) {
			Point now = que.poll();
			int x = now.x;
			int y = now.y;

			for (int i = 0; i < 4; i++) {
				int rx = x + dx[i];
				int ry = y + dy[i];

				if (rx >= 0 && rx < row && ry >= 0 && ry < col) {
					if (!visit[rx][ry] && maps[rx][ry] == 1) {
						visit[rx][ry] = true;
						que.offer(new Point(rx, ry));
						d[rx][ry] = d[x][y] + 1;
					}
				}
			}
		}

		int answer = d[row - 1][col - 1];
		if (answer == 0)
			answer = -1;
		
		return answer;
	}
}
