package Programmers;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		String[][] places = { { "POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP" },
				{ "POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP" }, { "PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX" },
				{ "OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO" }, { "PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP" } };
	}
}

class Solution {
	ArrayList<Point> candidates = new ArrayList<>();

	/**
	 * 5x5 ũ���� ������ 5���� �ִ�.
	 * �� ������ �Ÿ��α⸦ ���״��� Ȯ���ϴ� ����
	 * �� ���ǿ��� ������ P�� �� ���̺� O, ��Ƽ�� X�� �����Ǿ� �ִ�.
	 * �� �����ڴ� ����ư �Ÿ��� 2���Ϸ� ������ �ȵȴ�.
	 * ��, ��Ƽ������ �������� �� ���� �� �ִ�.
	 * 
	 * �����ڵ��� list�� �����, �����ڵ鰣�� �Ÿ��� ���Ʈ ������ Ž���غ���.
	 * @param places ���� 5���� ��Ȳ
	 * @return �� ������ �Ÿ��α⸦ ���״���
	 */
	public int[] solution(String[][] places) {
		int[] answer = new int[places.length];
		for (int i = 0; i < places.length; i++) {
			addCandidate(places[i]);
			answer[i] = isFollow(places[i]) ? 1 : 0;
			candidates.clear();
		}
		return answer;
	}

	public void addCandidate(String[] place) {
		for (int i = 0; i < place.length; i++) {
			for (int j = 0; j < place[i].length(); j++) {
				if (place[i].charAt(j) == 'P')
					candidates.add(new Point(i, j));
			}
		}
	}

	public boolean isFollow(String[] place) {
		for (int i = 0; i < candidates.size(); i++) {
			for (int j = i + 1; j < candidates.size(); j++) {
				int distance = getManhattanDist(candidates.get(i), candidates.get(j));
				// �Ÿ��� 1�̸� ������ ����Ų ���̴�.
				if (distance == 1)
					return false;
				// 2�̸鼭 ��Ƽ���� ���ٸ� ����Ų ���̴�.
				else if (distance == 2 && !isPartition(place, candidates.get(i), candidates.get(j))) {
					return false;
				}
			}
		}
		return true;
	}

	public int getManhattanDist(Point p1, Point p2) {
		return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
	}

	public boolean isPartition(String[] place, Point p1, Point p2) {
		for (int i = Math.min(p1.x, p2.x); i <= Math.max(p1.x, p2.x); i++) {
			for (int j = Math.min(p1.y, p2.y); j <= Math.max(p1.y, p2.y); j++) {
				if (place[i].charAt(j) == 'O')
					return false;
			}
		}
		return true;
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}