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
	 * 2^n x 2^n���� �̷���� �迭�� �־�����.
	 * �迭�� ��� 0�� 1�θ� �̷���� �ִ�.
	 * �� �� Ư�� ���� S�� ���Ұ� ��� ���� ������ ��� ������ �Ͽ� �ϳ��� ���ҷ� ���� �� �ִ�.
	 * �׷��� ���� ��� 4���� ������ ���簢������ ������ �ٽ� ������ �õ��Ѵ�.
	 * �迭�� �־��� �� ������ �Ϸ��� �� 0�� 1�� ������ ���ϴ� ����
	 * 
	 * ��ü �迭�� 0 �Ǵ� 1�� �̷���� ��츦 �������� ���ߴ�.
	 * @param arr �迭
	 * @return 0�� 1�� ����
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