import java.util.*;

class Node {
	int x;
	int y;
	int w;

	Node(int x, int y, int w) {
		this.x = x;
		this.y = y;
		this.w = w;
	}
}

class Solution {
	public int[] solution(String[][] places) {
		int[] answer = new int[5];
		char[][] map = new char[5][5];
		boolean[][] visit = new boolean[5][5];

		// 상하좌우
		int[] dx = { 0, 0, -1, 1 };
		int[] dy = { -1, 1, 0, 0 };

		for (int i = 0; i < places.length; i++) {
			Queue<Node> que = new LinkedList<>();
			int ischeck = 1;

			for (int j = 0; j < places[i].length; j++) {
				for (int k = 0; k < places[i][j].length(); k++) {
					map[j][k] = places[i][j].charAt(k);
					visit[j][k] = false;
				}
			}

			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 5; k++) {
					char pos = map[j][k];
					if (!visit[j][k] && pos == 'P') {
						que.add(new Node(k, j, 0));

						while (!que.isEmpty()) {
							Node node = que.poll();
							int x = node.x;
							int y = node.y;
							int w = node.w;

							visit[y][x] = true;

							for (int l = 0; l < 4; l++) {
								int nextX = x + dx[l];
								int nextY = y + dy[l];

								if (0 <= nextX && nextX < 5 && 0 <= nextY && nextY < 5 && !visit[nextY][nextX]) {
									char nextPos = map[nextY][nextX];

									if (nextPos == 'O') {
										que.add(new Node(nextX, nextY, w + 1));
									}

									if (nextPos == 'P' && w + 1 <= 2) {
										ischeck = 0;
										break;
									} else if (nextPos == 'P' && w + 1 > 2) {
										que.add(new Node(nextX, nextY, 0));
									}
								}
							}

							if (ischeck == 0) {
								break;
							}
						}
					}
				}
			}

			answer[i] = ischeck;

		}
		return answer;
	}
}
