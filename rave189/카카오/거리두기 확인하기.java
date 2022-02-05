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
	 * 5x5 크기의 대기실이 5개가 있다.
	 * 각 대기실이 거리두기를 지켰는지 확인하는 문제
	 * 각 대기실에는 응시자 P와 빈 테이블 O, 파티션 X로 구성되어 있다.
	 * 각 응시자는 맨해튼 거리가 2이하로 앉으면 안된다.
	 * 단, 파티션으로 막혀있을 시 앉을 수 있다.
	 * 
	 * 응시자들을 list로 만들고, 응시자들간의 거리를 브루트 포스로 탐색해본다.
	 * @param places 대기실 5개의 상황
	 * @return 각 대기실이 거리두기를 지켰는지
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
				// 거리가 1이면 무조건 안지킨 것이다.
				if (distance == 1)
					return false;
				// 2이면서 파티션이 없다면 안지킨 것이다.
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