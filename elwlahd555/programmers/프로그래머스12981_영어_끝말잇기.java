package elwlahd555.programmers;

import java.util.HashSet;
import java.util.Set;

public class 프로그래머스12981_영어_끝말잇기 {
	public static void main(String[] args) {
		int n = 3;
		String[] words = { "tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank" };
		System.out.println(solution(n, words));
	}

	public static int[] solution(int n, String[] words) {
		int[] answer = new int[2];
		char last = words[0].charAt(words[0].length() - 1);
		Set<String> check = new HashSet<String>();
		check.add(words[0]);
		int result = 0;
		for (int i = 1; i < words.length; i++) {
			if (check.contains(words[i])) {
				result = i;
				break;
			} else {
				check.add(words[i]);
			}
			if (words[i].charAt(0) != last) {
				result = i;
				break;
			} else {
				last = words[i].charAt(words[i].length() - 1);
			}
		}
		if (result == 0) {
			answer[0] = 0;
			answer[1] = 0;
		} else {
			answer[0] = result % n + 1;
			answer[1] = result / n + 1;
		}

		return answer;
	}
}
