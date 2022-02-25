import java.util.*;

class Solution {
	public int[] solution(int n, String[] words) {
		int[] answer = new int[2];
		int num = 1;
		int next = 1;

		HashSet<String> set = new HashSet<String>();
		char last = words[0].charAt(0); // 첫번째 글자로 초기화

		for (int i = 0; i < words.length; i++) {
			String s = words[i];
			if (set.contains(s) || s.charAt(0) != last) {
				answer[0] = num;
				answer[1] = next;
				break;

			}

			last = s.charAt(s.length() - 1);
			set.add(s);
			num++;
			if (num > n) {
				num = 1;
				next++;

			}

		}
		return answer;
	}

}
