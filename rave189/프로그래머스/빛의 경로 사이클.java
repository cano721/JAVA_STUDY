package Programmers;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		String[] grid = { "SL", "LR" };
		for (int v : solution.solution(grid)) {
			System.out.println(v);
		}
	}
}

class Solution {
	boolean[][][] visited;
	char[][] map;
	int[] dx = { -1, 0, 1, 0 };
	int[] dy = { 0, -1, 0, 1 };

	/**
	 * ���ڿ� ���� ����� �Ѵ�.
	 * ���� ������ ���� �̵��Ѵ�.
	 * 1. S�� ���� ĭ�� ������ ��� �����Ѵ�.
	 * 2. L�� ���� ĭ�� ������ ��� ��ȸ���Ѵ�.
	 * 3. R�� ���� ĭ�� ������ ��� ��ȸ���Ѵ�.
	 * 4. ���� ���� �ٱ��� �� ��� �ݴ��� ������ ���ƿ´�.
	 * �̶�, ���� �̵��ϴ� ����Ŭ�� �����Ǵµ� ����Ŭ�� ���̸� ������������ ����ϴ� ����
	 * 
	 * 500x500�̱� ������ ��ͷ� �����ϸ� �����÷ο찡 �߻��Ѵ�.
	 * ����Ŭ�̱� ������ ��� ���� ���������� ��� �Ȱ���.
	 * (�ٱ��ʿ��� ��� �� �� �˾Ҵµ� �ƴϾ���)
	 * @param grid ������ ����
	 * @return ���� �̵��ϴ� ����Ŭ�� ����
	 */
	public int[] solution(String[] grid) {
		ArrayList<Integer> answer = new ArrayList<>();
		init(grid);
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				for (int t = 0; t < dx.length; t++) {
					if (visited[i][j][t + 4])
						continue;
					answer.add(dfs(i, j, t, 0));
				}
			}
		}
		answer.sort(Comparator.naturalOrder());
		return answer.stream().mapToInt(v -> v.intValue()).toArray();
	}

	public void init(String[] grid) {
		map = new char[grid.length][grid[0].length()];
		visited = new boolean[map.length][map[0].length][8];
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[i].length(); j++)
				map[i][j] = grid[i].charAt(j);
	}

	public int dfs(int x, int y, int direction, int moveCnt) {
		while (!visited[x][y][direction + 4]) {
			visited[x][y][direction + 4] = true;
			direction = getNextDirection(map[x][y], direction);
			visited[x][y][direction] = true;
			x = getNextX(x, direction);
			y = getNextY(y, direction);
			moveCnt++;
		}
		return moveCnt;
	}

	public int getNextDirection(char type, int direction) {
		if (type == 'S')
			return direction;
		else if (type == 'L')
			return (direction + 1) % dx.length;
		else {
			if (--direction < 0)
				return dx.length - 1;
			return direction;
		}
	}

	public int getNextX(int x, int direction) {
		int nextX = x + dx[direction];
		if (nextX >= map.length)
			return 0;
		else if (nextX < 0)
			return map.length - 1;
		return nextX;
	}

	public int getNextY(int y, int direction) {
		int nextY = y + dy[direction];
		if (nextY >= map[0].length)
			return 0;
		else if (nextY < 0)
			return map[0].length - 1;
		return nextY;
	}
}