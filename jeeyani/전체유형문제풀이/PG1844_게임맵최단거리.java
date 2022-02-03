package 전체유형문제풀이;

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

	public static int solution(int[][] maps) {
		int answer = -1;

		Queue<nodeUser> q = new LinkedList<>();
		q.add(new nodeUser(0, 0)); //사용자의 캐릭터 시작위치

		while (!q.isEmpty()) {
			nodeUser user = q.poll();
			int x = user.x;
			int y = user.y;

			for (int i = 0; i < 4; i++) {

				int nx = x + dx[i];
				int ny = y + dy[i];

				//범위와 벽인 경우 무시
				if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5)
					continue;
				if (maps[nx][ny] == 0)
					continue;

				if (maps[nx][ny] == 1) {
					maps[nx][ny] = maps[x][y] + 1;
					q.add(new nodeUser(nx, ny));
				}
			}
		}

		if (maps[4][4] > 1) {
			answer = maps[4][4];
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
