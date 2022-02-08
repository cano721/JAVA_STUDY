
public class lotto {
	public int[] solution(int[] lottos, int[] win_nums) {
		int[] answer = new int[2];
		int count = 0, zero = 0;
		for (int i = 0; i < 6; i++) {
			if (lottos[i] == 0)
				zero++;
			for (int j = 0; j < 6; j++) {
				if (lottos[i] == win_nums[j]) {
					count++;
					break;
				}
			}
		}
		int top = Math.max(count + zero, 1);
		int bottom = Math.max(count, 1);

		answer[0] = 7 - top;
		answer[1] = 7 - bottom;

		return answer;
		// return new int[]{a,b};
		// answer없이 이렇게도 가능!!
	}

}
