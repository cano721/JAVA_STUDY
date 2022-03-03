package Programmers;

import java.util.HashSet;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		int m = 4;
		int n = 5;
		String[] board = { "CCBDE", "AAADE", "AAABF", "CCBBF" };
		System.out.println(solution.solution(m, n, board));
	}
}

class Solution {
	char[][] map;
	HashSet<Integer>[] removeList;
	int answer = 0;

	/**
	 * ������� �ִ�.
	 * �� ��Ͽ��� �� ���� ������ ���ڷ� �����Ǿ� �ִ�.
	 * �� ��Ͽ��� 2x2 ���·� ���� ���ڰ� �پ��ִٸ� ���ڸ� ���� �� �ִ�.
	 * ���ڸ� ����� �߷¿� ���� ���ʿ� �ִ� ���ڵ��� �����´�.
	 * �ϳ��� ����� ���� ���� 2x2�� ���Ե� �� �ִ�.
	 * �������� ������ ������ ���ϴ� ����
	 * @param m ���� ����
	 * @param n ���� ����
	 * @param board �����
	 * @return �������� ������ �� ����
	 */
	public int solution(int m, int n, String[] board) {
		map = new char[m][n];
		removeList = new HashSet[m];
		for (int i = 0; i < m; i++) {
			removeList[i] = new HashSet<>();
			for (int j = 0; j < n; j++) {
				map[i][j] = board[i].charAt(j);
			}
		}
		System.out.println();
		while (true) {
			if (!bomb())
				break;
			remove();
			down();
		}
		return answer;
	}

	public boolean bomb() {
		boolean isBomb = false;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				try {
					char ch1 = map[i][j];
					char ch3 = map[i][j + 1];
					char ch2 = map[i + 1][j];
					char ch4 = map[i + 1][j + 1];
					if (ch1 == '0')
						continue;
					if (ch1 == ch2 && ch2 == ch3 && ch3 == ch4) {
						removeList[i].add(j);
						removeList[i].add(j + 1);
						removeList[i + 1].add(j);
						removeList[i + 1].add(j + 1);
						isBomb = true;
					}
				} catch (Exception e) {
				}
			}
		}
		return isBomb;
	}

	public void remove() {
		for (int i = 0; i < removeList.length; i++) {
			for (int j : removeList[i]) {
				map[i][j] = '0';
				answer++;
			}
			removeList[i].clear();
		}
	}

	public void down() {
		for (int i = 0; i < map[0].length; i++) {
			for (int j = map.length - 1; j >= 0; j--) {
				if (map[j][i] == '0') {
					for (int t = j - 1; t >= 0; t--) {
						if (map[t][i] != '0') {
							char tmp = map[j][i];
							map[j][i] = map[t][i];
							map[t][i] = tmp;
							break;
						}
					}
				}
			}
		}
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}