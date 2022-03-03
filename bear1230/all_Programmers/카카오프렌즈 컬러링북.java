import java.util.*;

class Node {
	int x;
	int y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Solution {
	static boolean visit[][];
	static int max;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	public int[] solution(int m, int n, int[][] picture) {

		int[] answer = new int[2];
		int numberOfArea = 0;
		int maxSizeOfOneArea = 0;

		max = 0;
		visit = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (picture[i][j] == 0) {
					visit[i][j] = true;
					continue;
				} else if (visit[i][j] == true) {
					continue;
				} else {
					bfs(i, j, picture);
					numberOfArea++;
				}
			}
		}
		maxSizeOfOneArea = max;
		answer[0] = numberOfArea;
		answer[1] = maxSizeOfOneArea;
		return answer;
	}

	public void bfs(int x, int y, int[][] picture) {
		Queue<Node> que = new LinkedList<>();
		que.add(new Node(x, y));
		visit[x][y] = true;
		int count = 1;

		while (!que.isEmpty()) {
			Node node = que.poll();
			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				if (nx >= 0 && ny >= 0 && visit.length > nx && visit[0].length > ny && visit[nx][ny] == false) {
					if (picture[nx][ny] != 0 && picture[nx][ny] == picture[node.x][node.y]) {
						que.add(new Node(nx, ny));
						visit[nx][ny] = true;
						count++;
					}
				}
			}
		}
		max = Math.max(count, max);
	}
}
