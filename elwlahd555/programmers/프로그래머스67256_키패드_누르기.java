package elwlahd555.programmers;

public class 프로그래머스67256_키패드_누르기 {
	private static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) {
		int numbers[] = { 1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5 };
		String hand = "right";
		System.out.println(solution(numbers, hand));
	}

	private static String solution(int[] numbers, String hand) {
		String answer = "";
		Point arr[] = { new Point(3, 1), new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(1, 0),
				new Point(1, 1), new Point(1, 2), new Point(2, 0), new Point(2, 1), new Point(2, 2) };

		Point left = new Point(3, 0);
		Point right = new Point(3, 2);
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7) {
				answer = answer.concat("L");
				if (numbers[i] == 1) {
					left.x = 0;
					left.y = 0;
				} else if (numbers[i] == 4) {
					left.x = 1;
					left.y = 0;
				} else if (numbers[i] == 7) {
					left.x = 2;
					left.y = 0;
				}
			} else if (numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) {
				answer = answer.concat("R");
				if (numbers[i] == 3) {
					right.x = 0;
					right.y = 2;
				} else if (numbers[i] == 6) {
					right.x = 1;
					right.y = 2;
				} else if (numbers[i] == 9) {
					right.x = 2;
					right.y = 2;
				}
			} else {
				int l = Math.abs(left.x - arr[numbers[i]].x) + Math.abs(left.y - arr[numbers[i]].y);
				int r = Math.abs(right.x - arr[numbers[i]].x) + Math.abs(right.y - arr[numbers[i]].y);
				if (l == r) {
					if (hand.equals("right")) {
						answer = answer.concat("R");
						right.x = arr[numbers[i]].x;
						right.y = arr[numbers[i]].y;
					} else {
						answer = answer.concat("L");
						left.x = arr[numbers[i]].x;
						left.y = arr[numbers[i]].y;
					}
				} else if (l < r) {
					answer = answer.concat("L");
					left.x = arr[numbers[i]].x;
					left.y = arr[numbers[i]].y;
				} else {
					answer = answer.concat("R");
					right.x = arr[numbers[i]].x;
					right.y = arr[numbers[i]].y;
				}
			}
		}
		return answer;
	}
}
