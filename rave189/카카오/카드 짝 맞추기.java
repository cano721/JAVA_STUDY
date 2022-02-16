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
	 * 4x4 ũ���� �迭�� �ִ�.
	 * �� �ȿ��� 16���� ī�尡 �޸����� ������ �ִ�.
	 * ī��� 8������ ĳ���Ͱ� 2�徿 �ִ�.
	 * ī�带 2�� ������ �� �ִµ�, ���� ī�带 2�� ��� �������ٸ� 2���� ī�带 ������ �� �ִ�.
	 * �迭���� �̹� ������� ī�尡 ���ŵǾ� �ִ�.
	 * ���� ī��� 1~6������ �ڿ����̴�.
	 * �迭�� 0�� �̹� ���ŵ� ī�带 �ǹ��Ѵ�.
	 * ���� Ŀ���� ī�带 ������ ������ 1�� ����� ���.
	 * ���� Ŀ������ ��, ��, ��, ��� �̵��ϴ� ������ 1�� ����� ���.
	 * ���� Ŀ������ ctrl�� ������ ����Ű�� ������
	 * ���� ����Ű�� �������� ������ ù ��° ī�峪 ī�带 ������ ���ϸ� �迭�� ������ �̵��Ѵ�.
	 * ctrl�� ������ ����Ű�� ������ �͵� 1�� ����� ���.
	 * ��� ī�带 �����ϴµ� �ʿ��� �ּ� ����� ���ϴ� ����
	 * @param board 4x4 �迭
	 * @param r ó�� Ŀ���� ��
	 * @param c ó�� Ŀ���� ��
	 * @return ��� ī�带 �����ϴµ� �ʿ��� �ּ� ���
	 */
	public int solution(int[][] board, int r, int c) {
		int answer = Integer.MAX_VALUE;
		init(board);
		// ī�带 �����ϴ� ������ ���� ����� �޶�����.
		// ���� ��� ����� ���� ���ϱ� ���� ī�带 �����ϴ� ������ ���� ������ ���Ѵ�.
		nextPermutation(new int[cardCnt], 0);
		//���� ������ ���� ī�带 �����غ��� �ּڰ����� ���Ѵ�.
		for (int[] order : orders) {
			// �ʱ�ȭ
			p = new Point(r, c);
			int move = 0;
			// �迭�� ���� �� ����ؾ� �ϹǷ� �����Ѵ�.
			map = new int[board.length][board[0].length];
			for (int i = 0; i < board.length; i++)
				System.arraycopy(board[i], 0, map[i], 0, map[i].length);
			// ������ ī�带 �����غ���.
			for (int type : order) {
				// type�� ī�带 ã�� ������ ��������
				move += findNext(type) + 1;
				// ã�����Ƿ� �����.
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
			// order �迭�� ArrayList�� �����ϰ� �Ǹ�
			// ���� �޸� �ּҸ� ������ �迭�� ��� �ְ� �ǹǷ�
			// Ž�� ���� �ٲ�� �Ǵ� ���� ArrayList������ ������ ����
			// ��� �迭�� �������� Ž���� �迭�� �ٲ�� �ȴ�.
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
					// Ŀ�� �̵�
					p = cur;
					return move;
				}
				// �����¿� Ž��
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
				// ctrl ���� �� Ž��
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