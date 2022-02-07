package 전체유형문제풀이;

import java.util.*;

public class PG77484_로또의최고순위와최저순위 {

	public static void main(String[] args) {
		
		int[] lottos = {44, 1, 0, 0, 31, 25};
		int[] win_nums= {31, 10, 45, 1, 6, 19};
		int[] result = solution(lottos,win_nums);
		System.out.println(result);

	}

	private static int[] solution(int[] lottos, int[] win_nums) {
		int[] answer = new int[2];

		int count = 0;
		for (int i = 0; i < lottos.length; i++) {
			for (int j = 0; j < win_nums.length; j++) {

				if (lottos[i] == win_nums[j]) {
					count++;
				}

			}
		}

		answer[1] = scoreCount(count);

		for (int i = 0; i < lottos.length; i++) {
			if (lottos[i] == 0) {
				count++;
			}
		}

		answer[0] = scoreCount(count);

		return answer;
	}

	private static int scoreCount(int count) {

		switch (count) {
		case 6:
			return 1;
		case 5:
			return 2;
		case 4:
			return 3;
		case 3:
			return 4;
		case 2:
			return 5;
		default:
			return 6;
		}
	}

}
