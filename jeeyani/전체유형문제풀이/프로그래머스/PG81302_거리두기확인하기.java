package 전체유형문제풀이.프로그래머스;

import java.util.*;

public class PG81302_거리두기확인하기 {

	public static void main(String[] args) {

		String[][] places = { { "POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP" },
				{ "POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP" }, { "PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX" },
				{ "OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO" }, { "PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP" } };

		int[] result = solution(places);

		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}

	}

	private static int[] solution(String[][] places) {
		int[] answer = new int[places.length];

		for (int i = 0; i < places.length; i++) {
			answer[i] = getDistance(places[i]);
		}
		return answer;
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] visited;

	private static int getDistance(String[] place) {
		Queue<locNode> q = new LinkedList<>();

		for (int i = 0; i < place.length; i++) {
			for (int j = 0; j < 5; j++) {

				//응시자가 앉아 있는 자리인 경우
				if (place[i].charAt(j) == 'P') {

					visited = new boolean[5][5];
					visited[i][j] = true;

					q.offer(new locNode(i, j));

					while (!q.isEmpty()) {
						locNode node = q.poll();
						int x = node.x;
						int y = node.y;

						for (int k = 0; k < 4; k++) {
							int nx = x + dx[k];
							int ny = y + dy[k];

							//범위 내부인 경우와 방문하지 않은 경우
							if ((nx >= 0 && nx < 5 && ny >= 0 && ny < 5) && !visited[nx][ny]) {
								
								int dist = Math.abs(nx-x)+Math.abs(ny-y);

								if (place[nx].charAt(ny) == 'P' && dist <= 2)
									return 0;
								
								else if(place[nx].charAt(ny) == 'O' && dist < 2) {
									visited[nx][ny] = true;
									q.offer(new locNode(nx, ny));
								}
								
							}

						}

					}
				}
			}
		}

		return 1;
	}

	public static class locNode {
		int x;
		int y;

		public locNode(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
