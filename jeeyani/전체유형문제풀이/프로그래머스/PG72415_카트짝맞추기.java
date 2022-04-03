package 전체유형문제풀이.프로그래머스;

import java.util.*;

/*
 * 
 * 
 * 최단거리(다익스트라) + 백트래킹
 * 
 * 1. 모든 보드를 방문 : 백트래킹
 * 2. 최소 키보드 입력 횟수 
 *  2-1. 한번에 이동 가능한 경우 : 다익스트라
 *  2-2. 한칸씩 이동하는 것이 최단거리일 가능성
 *  2-3. enter는 카드 한쌍당 무조건 2번
 * 
 * https://yjyoon-dev.github.io/kakao/2021/01/29/kakao-paircard/
 * */

public class PG72415_카트짝맞추기 {

	public static void main(String[] args) {

		int[][] board = { { 1, 0, 0, 3 }, { 2, 0, 0, 0 }, { 0, 0, 0, 2 }, { 3, 0, 1, 0 } };
		int r = 1;
		int c = 0;

		int result = solution(board, r, c);

		System.out.println(result);

	}

	public static int solution(int[][] board, int r, int c) {

		int answer = findBoard(board, r, c);
		return answer;
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static final int INF = (int) 1e9;

	public static int findBoard(int[][] board, int x, int y) {
		if (isFinished(board))
			return 0;

		int min = Integer.MAX_VALUE;

		for (int k = 1; k <= 6; k++) { //0은 없는 카드임으로 제외 해야함
			
			List<boardNode> list = new ArrayList<>();
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (k == board[i][j]) {
						list.add(new boardNode(i, j));
					}
				}
			}

			if (list.isEmpty()) continue;

			int aX = list.get(0).x;
			int aY = list.get(0).y;
			int bX = list.get(1).x;
			int bY = list.get(1).y;

			/*-------------------------------
			 * 
			 * 최소 방향키 이동
			 * 1. 카드1 -> 카드2 이동
			 * 2. 카드2 -> 카드1 이동
			 --------------------------------*/
			int card1 = getDist(board, x, y, aX, aY) + getDist(board, aX, aY, bX, bY) + 2;
			int card2 = getDist(board, x, y, bX, bY) + getDist(board, bX, bY, aX, aY) + 2;

			//짝을 맞췄으면 뒤집어 두기
			board[aX][aY] = 0;
			board[bX][bY] = 0;

			min = Math.min(min, Math.min(card1 + findBoard(board, bX, bY), card2 + findBoard(board, aX, aY)));

			//다시 초기화
			board[aX][aY] = k;
			board[bX][bY] = k;
		}
		return min;
	}

	public static int getDist(int[][] board, int x1, int y1, int x2, int y2) {
		int[][] dist = new int[4][4];

		for (int i = 0; i < 4; i++) {
			Arrays.fill(dist[i], INF);
		}

		PriorityQueue<boardNode> q = new PriorityQueue<>();
		q.add(new boardNode(0, x1, y1));
		dist[x1][y1] = 0;

		int d = 0;

		while (!q.isEmpty()) {
			boardNode node = q.poll();
			int x = node.x;
			int y = node.y;
			d = node.dist;

			//해당 짝 카드에 도착했다면 종료
			if (dist[x][y] < d) continue;
			if (x == x2 && y == y2)return d;

			for (int i = 0; i < 4; i++) {
				int cnt = 0;
				int nx = x;
				int ny = y;

				while (nx + dx[i] >= 0 && nx + dx[i] < 4 && ny + dy[i] >= 0 && ny + dy[i] < 4) {

					nx += dx[i];
					ny += dy[i];
					cnt++;

					//카드만나면 종료
					if (board[nx][ny] != 0) break;

					if (dist[nx][ny] > d + cnt) {
						dist[nx][ny] = d + cnt;
						q.add(new boardNode(d + cnt, nx, ny));
					}
				}
				
				//ctrl+방향키 이동한 횟수
				if (dist[nx][ny] > d + 1) {
					dist[nx][ny] = d + 1;
					q.add(new boardNode(d + 1, nx, ny));
				}

			}
		}
		return d;
	}

	public static boolean isFinished(int[][] board) {
		for (int[] bb : board) {
			for (int b : bb) {
				if (b != 0)
					return false;
			}
		}
		return true;
	}

	public static class boardNode implements Comparable<boardNode> {
		int dist;
		int x;
		int y;

		public boardNode(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public boardNode(int dist, int x, int y) {
			this.dist = dist;
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(boardNode o) {
			return dist - o.dist;
		}
	}
}