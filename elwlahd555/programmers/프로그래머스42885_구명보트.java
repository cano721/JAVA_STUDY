package elwlahd555.programmers;

import java.util.Arrays;

public class 프로그래머스42885_구명보트 {
	public static void main(String[] args) {
		int[] people = { 70, 50, 80, 50 };
		int limit = 100;
		System.out.println(solution(people, limit));
	}

	public static int solution(int[] people, int limit) {
		int answer = 0;
		Arrays.sort(people);

		int index = 0;
		for (int i = people.length - 1; i >= 0; i--) {
			if (index < i) {
				break;
			}
			if (people[i] + people[index] <= limit) {
				index++;
			}
			answer++;
		}
		return answer;
	}
}
