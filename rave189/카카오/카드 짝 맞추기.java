package Programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[][] board = { { 1, 0, 0, 3 }, { 2, 0, 0, 0 }, { 0, 0, 0, 2 }, { 3, 0, 1, 0 } };
		int r = 1;
		int c = 0;
		System.out.println(solution.solution(board, r, c));
	}
}

class Solution {

	Point p;
	int[] dx = { 1, -1, 0, 0 };
	int[] dy = { 0, 0, 1, -1 };
	int cardCnt = 0;
	boolean[] isExistCard = new boolean[7];
	boolean[] permutationVisited = new boolean[7];
	ArrayList<int[]> orders = new ArrayList<>();
	int[][] map;

	/**
	 * 4x4 크기의 배열이 있다.
	 * 이 안에는 16장의 카드가 뒷면으로 뒤집혀 있다.
	 * 카드는 8가지의 캐릭터가 2장씩 있다.
	 * 카드를 2번 뒤집을 수 있는데, 같은 카드를 2장 모두 뒤집었다면 2장의 카드를 제거할 수 있다.
	 * 배열에는 이미 어느정도 카드가 제거되어 있다.
	 * 남은 카드는 1~6사이의 자연수이다.
	 * 배열의 0은 이미 제거된 카드를 의미한다.
	 * 현재 커서의 카드를 뒤집는 행위는 1의 비용이 든다.
	 * 현재 커서에서 상, 하, 좌, 우로 이동하는 행위도 1의 비용이 든다.
	 * 현재 커서에서 ctrl을 누르고 방향키를 누르면
	 * 누른 방향키의 방향으로 만나는 첫 번째 카드나 카드를 만나지 못하면 배열의 끝까지 이동한다.
	 * ctrl을 누르고 방향키를 누르는 것도 1의 비용이 든다.
	 * 모든 카드를 제거하는데 필요한 최소 비용을 구하는 문제
	 * @param board 4x4 배열
	 * @param r 처음 커서의 행
	 * @param c 처음 커서의 열
	 * @return 모든 카드를 제거하는데 필요한 최소 비용
	 */
	public int solution(int[][] board, int r, int c) {
		int answer = Integer.MAX_VALUE;
		init(board);
		// 카드를 제거하는 순서에 따라 비용이 달라진다.
		// 따라서 모든 경우의 수를 구하기 위해 카드를 제거하는 순서에 대한 순열을 구한다.
		nextPermutation(new int[cardCnt], 0);
		//구한 순열에 따라 카드를 제거해보고 최솟값인지 구한다.
		for (int[] order : orders) {
			// 초기화
			p = new Point(r, c);
			int move = 0;
			// 배열을 여러 번 사용해야 하므로 복사한다.
			map = new int[board.length][board[0].length];
			for (int i = 0; i < board.length; i++)
				System.arraycopy(board[i], 0, map[i], 0, map[i].length);
			// 실제로 카드를 제거해본다.
			for (int type : order) {
				// type의 카드를 찾고 뒤집는 행위까지
				move += findNext(type) + 1;
				// 찾았으므로 지운다.
				map[p.x][p.y] = 0;
				move += findNext(type) + 1;
				map[p.x][p.y] = 0;
			}
			answer = Math.min(answer, move);
		}
		return answer;
	}

	public void init(int[][] board) {
		for (int[] row : board)
			for (int card : row)
				if (card > 0) {
					cardCnt++;
					isExistCard[card] = true;
				}
		cardCnt /= 2;
	}

	public void nextPermutation(int[] order, int depth) {
		if (depth == cardCnt) {
			// order 배열을 ArrayList에 저장하게 되면
			// 같은 메모리 주소를 가지는 배열을 계속 넣게 되므로
			// 탐색 도중 바뀌게 되는 값이 ArrayList에까지 영향이 가서
			// 모든 배열이 마지막에 탐색한 배열로 바뀌게 된다.
			int[] copy = new int[order.length];
			System.arraycopy(order, 0, copy, 0, copy.length);
			orders.add(copy);
			return;
		}

		for (int i = 1; i < isExistCard.length; i++) {
			if (!isExistCard[i] || permutationVisited[i])
				continue;
			permutationVisited[i] = true;
			order[depth] = i;
			nextPermutation(order, depth + 1);
			permutationVisited[i] = false;
		}
	}

	public int findNext(int type) {
		boolean[][] visited = new boolean[map.length][map[0].length];
		Queue<Point> q = new LinkedList<>();
		q.add(p);
		visited[p.x][p.y] = true;
		int move = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				Point cur = q.poll();
				if (map[cur.x][cur.y] == type) {
					// 커서 이동
					p = cur;
					return move;
				}
				// 상하좌우 탐색
				for (int i = 0; i < dx.length; i++) {
					int nextX = cur.x + dx[i];
					int nextY = cur.y + dy[i];
					try {
						if (visited[nextX][nextY])
							continue;
						visited[nextX][nextY] = true;
						q.add(new Point(nextX, nextY));
					} catch (Exception e) {
					}
				}
				// ctrl 누를 시 탐색
				for (int i = 0; i < 4; i++) {
					Point next = ctrlMove(cur, i);
					try {
						if (visited[next.x][next.y])
							continue;
						visited[next.x][next.y] = true;
						q.add(next);
					} catch (Exception e) {
					}
				}
			}
			move++;
		}
		return -1;
	}

	public Point ctrlMove(Point cur, int direction) {
		if (direction == 0)
			return ctrlRight(cur);
		else if (direction == 1)
			return ctrlLeft(cur);
		else if (direction == 2)
			return ctrlUp(cur);
		else
			return ctrlDown(cur);
	}

	public Point ctrlRight(Point cur) {
		for (int i = cur.y + 1; i < map[cur.x].length; i++) {
			if (map[cur.x][i] > 0)
				return new Point(cur.x, i);
		}
		return new Point(cur.x, map[cur.x].length - 1);
	}

	public Point ctrlLeft(Point cur) {
		for (int i = cur.y - 1; i >= 0; i--) {
			if (map[cur.x][i] > 0)
				return new Point(cur.x, i);
		}
		return new Point(cur.x, 0);
	}

	public Point ctrlUp(Point cur) {
		for (int i = cur.x - 1; i >= 0; i--) {
			if (map[i][cur.y] > 0)
				return new Point(i, cur.y);
		}
		return new Point(0, cur.y);
	}

	public Point ctrlDown(Point cur) {
		for (int i = cur.x + 1; i < map.length; i++) {
			if (map[i][cur.y] > 0)
				return new Point(i, cur.y);
		}
		return new Point(map.length - 1, cur.y);
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}