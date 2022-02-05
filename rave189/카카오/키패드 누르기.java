package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	Point left = new Point(3, 0);
	Point right = new Point(3, 2);

	/**
	 * 핸드폰 키패드에서 주어지는 숫자를 누르려고 한다.
	 * 1, 4, 7은 왼손으로 3, 6, 9는 오른손으로 누른다.
	 * 2, 5, 8, 0은 왼손과 오른손 중에 가까운 손으로 누른다.
	 * 두 손이 같은 거리를 가진다면 오른손 잡이는 오른손으로, 왼손 잡이는 왼손으로 누른다.
	 */
	public String solution(int[] numbers, String hand) {
		StringBuilder answer = new StringBuilder();
		for (int num : numbers) {
			if (num == 1 || num == 4 || num == 7) {
				left = new Point(num / 3, 0);
				answer.append('L');
			} else if (num == 3 || num == 6 || num == 9) {
				right = new Point(num / 3 - 1, 2);
				answer.append('R');
			} else {
				Point button = null;
				if (num == 0) {
					button = new Point(3, 1);
				} else {
					button = new Point(num / 3, 1);
				}
				int leftDist = Math.abs(left.x - button.x) + Math.abs(left.y - button.y);
				int rightDist = Math.abs(right.x - button.x) + Math.abs(right.y - button.y);
				if (leftDist < rightDist) {
					left = button;
					answer.append('L');
				} else if (rightDist < leftDist) {
					right = button;
					answer.append('R');
				} else {
					if (hand.equals("right")) {
						right = button;
						answer.append('R');
					} else {
						left = button;
						answer.append('L');
					}
				}
			}
		}
		return answer.toString();
	}
}

class Point {
	int x, y;

	public Point(int _x, int _y) {
		this.x = _x;
		this.y = _y;
	}
}