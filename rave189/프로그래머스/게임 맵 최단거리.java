package Programmers;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[][] maps = 
			{ { 1, 0, 1, 1, 1 }, { 1, 0, 1, 0, 1 }, { 1, 0, 1, 1, 1 }, { 1, 1, 1, 0, 1 }, { 0, 0, 0, 0, 1 } };
//		{{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,0},{0,0,0,0,1}};
		System.out.println(solution.solution(maps));
	}
}

class Solution {
	int[] dx = { 1, -1, 0, 0 };
	int[] dy = { 0, 0, 1, -1 };

	/**
	 * NxM 크기의 지도가 주어지면 (1, 1)에서 (N, M)까지 도달하기 위한 최소 이동 횟수를 구하는 문제
	 * 갈 수 없다면 -1을 출력한다. 
	 * @param maps 지도
	 * @return (N, M)까지 도달하기 위한 최소 이동 횟수
	 */
	public int solution(int[][] maps) {
		return bfs(maps, new Point(0, 0));
	}

	public int bfs(int[][] map, Point start) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[map.length][map[0].length];
		q.add(start);
		visited[start.x][start.y] = true;
		int move = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			move++;
			while (size-- > 0) {
				Point cur = q.poll();
				if (cur.x == map.length - 1 && cur.y == map[0].length - 1)
					return move;
				for (int i = 0; i < dx.length; i++) {
					int nextX = cur.x + dx[i];
					int nextY = cur.y + dy[i];
					try {
						if (visited[nextX][nextY] || map[nextX][nextY] == 0)
							continue;
						q.add(new Point(nextX, nextY));
						visited[nextX][nextY] = true;
					} catch (Exception e) {
					}
				}
			}
		}
		return -1;
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}