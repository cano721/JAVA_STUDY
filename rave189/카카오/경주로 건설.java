package Programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[][] board =
//				{{0,0,0},{0,0,0},{0,0,0}};
//		{{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}};
				{ { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, { 0, 1, 0, 1 }, { 1, 0, 0, 0 } };
//		{{0,0,0,0,0,0},{0,1,1,1,1,0},{0,0,1,0,0,0},{1,0,0,1,0,1},{0,1,0,0,0,1},{0,0,0,0,0,0}};
		System.out.println(solution.solution(board));
	}
}

class Solution {

	int[][][] cost;
	int[][] board;
	int n;
	int[] dx = { 0, 1, 0, -1 };
	int[] dy = { 1, 0, -1, 0 };

	public int solution(int[][] board) {
		init(board);
		bfs(0, 0);
		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < dx.length; i++)
			answer = Math.min(answer, cost[n - 1][n - 1][i]);
		return answer;
	}

	public void init(int[][] board) {
		n = board.length;
		this.board = board;
		cost = new int[n][n][dx.length];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				Arrays.fill(cost[i][j], Integer.MAX_VALUE);
	}

	public void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		for (int i = 0; i < dx.length; i++) {
			q.add(new Point(x, y, i));
			cost[x][y][i] = 0;
		}
		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int i = 0; i < dx.length; i++) {
				int nextX = cur.x + dx[i];
				int nextY = cur.y + dy[i];
				try {
					if (board[nextX][nextY] == 1)
						continue;
					int nextCost = getNextCost(cur.direction, i);
					if (cost[cur.x][cur.y][cur.direction] + nextCost <= cost[nextX][nextY][i]) {
						cost[nextX][nextY][i] = cost[cur.x][cur.y][cur.direction] + nextCost;
						q.add(new Point(nextX, nextY, i));
					}
				} catch (Exception e) {
				}
			}
		}
	}

	public int getNextCost(int curDirection, int nextDirection) {
		if (curDirection == nextDirection || curDirection == (nextDirection + 2) % dx.length)
			return 100;
		return 600;
	}
}

class Point {
	int x, y, direction;

	public Point(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
}