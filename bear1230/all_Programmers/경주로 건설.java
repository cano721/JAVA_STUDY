import java.util.*;

class Solution {
	public static int[] dr = { 0, 0, 1, -1 };
	public static int[] dc = { 1, -1, 0, 0 };

	static class Node {
		int r;
		int c;
		int dir;
		int total;

		public Node(int r, int c, int dir, int total) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.total = total;
		}

	}

	public static int[][] map;

	public static int solution(int[][] board) {

		boolean[][] visited = new boolean[board.length][board[0].length];

		visited[0][0] = true;

		map = new int[board.length][board.length];

		Queue<Node> que = new LinkedList<>();

		if (board[0][1] == 0) {
			visited[0][1] = true;
			que.add(new Node(0, 1, 1, 100));
			map[0][1] = 100;
		}

		if (board[1][0] == 0) {
			visited[1][0] = true;
			que.add(new Node(1, 0, 3, 100));
			map[1][0] = 100;
		}

		while (!que.isEmpty()) {
			Node now = que.poll();

			int r = now.r;
			int c = now.c;
			int dir = now.dir;
			int total = now.total;

			for (int i = 0; i < 4; i++) {
				if (r + dr[i] < board.length && r + dr[i] >= 0 && c + dc[i] < board[0].length && c + dc[i] >= 0
						&& board[r + dr[i]][c + dc[i]] == 0) {

					if (!visited[r + dr[i]][c + dc[i]]) {
						if (dir == 1 || dir == 2) { // 동 서
							if (i >= 2) {
								visited[r + dr[i]][c + dc[i]] = true;
								que.add(new Node(r + dr[i], c + dc[i], i + 1, total + 600));
								map[r + dr[i]][c + dc[i]] = total + 600;

							} else {
								visited[r + dr[i]][c + dc[i]] = true;
								que.add(new Node(r + dr[i], c + dc[i], i + 1, total + 100));
								map[r + dr[i]][c + dc[i]] = total + 100;

							}
						} else {
							if (i < 2) {
								visited[r + dr[i]][c + dc[i]] = true;

								que.add(new Node(r + dr[i], c + dc[i], i + 1, total + 600));
								map[r + dr[i]][c + dc[i]] = total + 600;

							} else {
								visited[r + dr[i]][c + dc[i]] = true;

								que.add(new Node(r + dr[i], c + dc[i], i + 1, total + 100));
								map[r + dr[i]][c + dc[i]] = total + 100;

							}
						}
					} else {
						if (dir == 1 || dir == 2) {
							if (i >= 2) {

								if (map[r + dr[i]][c + dc[i]] >= total + 600) {
									que.add(new Node(r + dr[i], c + dc[i], i + 1, total + 600));
									map[r + dr[i]][c + dc[i]] = total + 600;
								}
							} else {
								if (map[r + dr[i]][c + dc[i]] >= total + 100) {
									que.add(new Node(r + dr[i], c + dc[i], i + 1, total + 100));
									map[r + dr[i]][c + dc[i]] = total + 100;
								}
							}
						} else {
							if (i < 2) {
								if (map[r + dr[i]][c + dc[i]] >= total + 600) {
									que.add(new Node(r + dr[i], c + dc[i], i + 1, total + 600));
									map[r + dr[i]][c + dc[i]] = total + 600;
								}
							} else {
								if (map[r + dr[i]][c + dc[i]] >= total + 100) {
									que.add(new Node(r + dr[i], c + dc[i], i + 1, total + 100));
									map[r + dr[i]][c + dc[i]] = total + 100;
								}
							}
						}
					}
				}

			}

		}

		return map[board.length - 1][board.length - 1];
	}
}
