package elwlahd555.programmers;

public class 프로그래머스42842_카펫 {
	public static void main(String[] args) {
		int brown = 8;
		int yellow = 1;
		System.out.println(solution(brown, yellow));
	}

	public static int[] solution(int brown, int yellow) {
		int[] answer = new int[2];
		int size = brown + yellow;
		outer: for (int i = 3; i < 5000; i++) {
			for (int j = 3; j < 5000; j++) {
				if (i * j == size && (i + j) * 2 - 4 == brown) {
					if (i > j) {
						answer[0] = i;
						answer[1] = j;
					} else {
						answer[0] = j;
						answer[1] = i;
					}
					break outer;
				}
			}
		}
		return answer;
	}
}
