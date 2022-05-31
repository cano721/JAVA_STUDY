package elwlahd555.programmers;

import java.util.LinkedList;

public class 프로그래머스43162_네트워크 {
	public static void main(String[] args) {
		int n=3;
		int computers[][]= {{1,1,0},{1,1,0},{0,0,1}};
		System.out.println(solution(n, computers));
	}
	   private static class Point {
			int x, y;

			public Point(int x, int y) {
				super();
				this.x = x;
				this.y = y;
			}

		}
	    public static int solution(int n, int[][] computers) {
			int answer = 0;
			LinkedList<Point> que = new LinkedList<>();
			while (check(computers)) {
				outer:
				for (int i = 0; i < computers.length; i++) {
					for (int j = 0; j < computers[0].length; j++) {
						if (computers[i][j] == 1) {
							que.add(new Point(i, j));
							computers[i][j] = 0;
							break outer;
						}
					}
				}

				while (!que.isEmpty()) {
					Point p = que.poll();
					for (int i = 0; i < computers.length; i++) {
						if (computers[p.y][i] == 1) {
							computers[p.y][i] = 0;
							que.add(new Point(p.y, i));
						}
					}
				}
				answer++;
			}
			return answer;
	    }
	    public static boolean check(int[][] computers) {
			for (int i = 0; i < computers.length; i++) {
				for (int j = 0; j < computers[0].length; j++) {
					if (computers[i][j] == 1) {
						return true;
					}
				}
			}
			return false;
		}
}
