package elwlahd555.programmers;

import java.util.Arrays;
import java.util.Collections;

public class 프로그래머스42747_H_Index {
	public static void main(String[] args) {
		int[] citations = { 3, 0, 6, 1, 5 };
		System.out.println(solution(citations));
	}

	public static int solution(int[] citations) {
		int N = citations.length;
		Integer[] temp = new Integer[N];
		for (int i = 0; i < N; i++) {
			temp[i] = citations[i];
		}
		int answer = 0;
		Arrays.sort(temp, Collections.reverseOrder());

		while (answer < N) {
			if (temp[answer] <= answer)
				break;
			answer++;
		}
		return answer;
	}
}
