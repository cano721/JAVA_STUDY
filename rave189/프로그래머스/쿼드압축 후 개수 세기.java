package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	int[][] map;
	int[] answer = { 0, 0 };

	/**
	 * 2^n x 2^n으로 이루어진 배열이 주어진다.
	 * 배열은 모두 0과 1로만 이루어져 있다.
	 * 이 중 특정 영역 S의 원소가 모두 같은 원소인 경우 압축을 하여 하나의 원소로 만들 수 있다.
	 * 그렇지 않은 경우 4개의 균일한 정사각형으로 나누어 다시 압축을 시도한다.
	 * 배열이 주어질 때 압축을 완료한 후 0과 1의 개수를 구하는 문제
	 * 
	 * 전체 배열이 0 또는 1로 이루어진 경우를 생각하지 못했다.
	 * @param arr 배열
	 * @return 0과 1의 개수
	 */
	public int[] solution(int[][] arr) {
		init(arr);
		Point left = new Point(0, 0);
		Point right = new Point(arr.length, arr.length);
		if (isValid(left, right)) {
			answer[map[left.x][left.y]]++;
			return answer;
		}
		search(left, right);
		return answer;
	}

	public void init(int[][] arr) {
		map = arr;
	}

	public void search(Point left, Point right) {
		if (right.x - left.x <= 1 && right.y - left.y <= 1) {
			answer[map[left.x][left.y]]++;
			return;
		}
		Point mid = left.getMidPoint(right);
		Point midTop = new Point(left.x, mid.y);
		Point midLeft = new Point(mid.x, left.y);
		Point midRight = new Point(mid.x, right.y);
		Point midBottom = new Point(right.x, mid.y);
		if (isValid(left, mid))
			answer[map[left.x][left.y]]++;
		else
			search(left, mid);

		if (isValid(midTop, midRight))
			answer[map[midTop.x][midTop.y]]++;
		else
			search(midTop, midRight);

		if (isValid(midLeft, midBottom))
			answer[map[midLeft.x][midLeft.y]]++;
		else
			search(midLeft, midBottom);

		if (isValid(mid, right))
			answer[map[mid.x][mid.y]]++;
		else
			search(mid, right);
	}

	public boolean isValid(Point left, Point right) {
		int type = map[left.x][left.y];
		for (int i = left.x; i < right.x; i++)
			for (int j = left.y; j < right.y; j++)
				if (type != map[i][j])
					return false;
		return true;
	}
}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point getMidPoint(Point o) {
		int midX = (x + o.x) / 2;
		int midY = (y + o.y) / 2;
		return new Point(midX, midY);
	}
}