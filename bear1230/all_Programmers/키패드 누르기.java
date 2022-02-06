/*
(0,0) (0,1) (0,2)
(1,0) (1,1) (1,2)
(2,0) (2,1) (2,2)
(3,0) (3,1) (3,2)

x = (키패드 - 1) / 3 
y = (키패드 - 1) % 3 
거리 = |(x - 이동할 키패드 x)| + |(y - 이동할 키패드 y)|
*/
class Solution {
	public String solution(int[] numbers, String hand) {
		String answer = "";
		int left = 10;
		int right = 12;

		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7) {
				answer += "L";
				left = numbers[i];
			} else if (numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) {
				answer += "R";
				right = numbers[i];
			} else {
				if (numbers[i] == 0)
					numbers[i] = 11;

				int leftnum = dist(left, numbers[i]);
				int rightnum = dist(right, numbers[i]);

				if (leftnum > rightnum) {
					answer += "R";
					right = numbers[i];
				} else if (leftnum < rightnum) {
					answer += "L";
					left = numbers[i];
				} else {
					if (hand.equals("left")) {
						answer += "L";
						left = numbers[i];
					} else {
						answer += "R";
						right = numbers[i];
					}
				}

			}
		}
		return answer;
	}

	public int dist(int cur, int next) {

		int x1 = (cur - 1) / 3;
		int y1 = (cur - 1) % 3;
		int x2 = (next - 1) / 3;
		int y2 = (next - 1) % 3;

		return Math.abs(x1 - x2) + Math.abs(y1 - y2);

	}
}
