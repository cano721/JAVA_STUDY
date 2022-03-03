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
	 * 블록판이 있다.
	 * 이 블록에는 몇 가지 종류의 문자로 구성되어 있다.
	 * 이 블록에서 2x2 형태로 같은 문자가 붙어있다면 문자를 지울 수 있다.
	 * 문자를 지우면 중력에 의해 위쪽에 있는 문자들이 내려온다.
	 * 하나의 블록은 여러 개의 2x2에 포함될 수 있다.
	 * 지워지는 문자의 개수를 구하는 문제
	 * @param m 세로 길이
	 * @param n 가로 길이
	 * @param board 블록판
	 * @return 지워지는 문자의 총 개수
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