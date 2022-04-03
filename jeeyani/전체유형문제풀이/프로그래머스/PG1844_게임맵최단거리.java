package 전체유형문제풀이.프로그래머스;

import java.util.*;

/*bfs*/

public class PG1844_게임맵최단거리 {

	public static void main(String[] args) {
		int[][] maps = { { 1, 0, 1, 1, 1 }, { 1, 0, 1, 0, 1 }, { 1, 0, 1, 1, 1 }, { 1, 1, 1, 0, 1 },
				{ 0, 0, 0, 0, 1 } };
		int result = solution(maps);
		System.out.println(result);

	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] visited;

	public static int solution(int[][] maps) {
		int answer = -1;

		int n = maps.length;
		int m = maps[0].length;
		visited = new boolean[n][m];

		Queue<nodeUser> q = new LinkedList<>();
		q.offer(new nodeUser(0, 0)); //사용자의 캐릭터 시작위치
		visited[0][0] = true;

		while (!q.isEmpty()) {
			nodeUser user = q.poll();
			int x = user.x;
			int y = user.y;

			//도착했으면 종료
			if (x == n - 1 && y == m - 1) {
				answer = maps[x][y];
				break;
			}

			for (int i = 0; i < 4; i++) {

				int nx = x + dx[i];
				int ny = y + dy[i];

				//범위를 넘어선 경우, 벽인 경우, 방문한 곳 무시
				if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				if (maps[nx][ny] == 0) continue;
				if (visited[nx][ny]) continue;

				maps[nx][ny] = maps[x][y] + 1;
				q.offer(new nodeUser(nx, ny));
				visited[nx][ny] = true;

			}
		}

		return answer;
	}

	public static class nodeUser {
		int x;
		int y;

		public nodeUser(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
