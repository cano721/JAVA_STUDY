package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		String s = 
//				"aabbaccc";
//				"ababcdcdababcdcd";
//				"abcabcdede";
//				"abcabcabcabcdededededede";
				"xababcdcdababcdcd";
		System.out.println(solution.solution(s));
	}
}

class Solution {
	/**
	 * 문자열이 주어졌을 때, 같은 문자열이 연속된다면 연속된 횟수 + 문자열을 붙여 압축하도록 한다.
	 * 연속된 횟수가 1일 경우 붙이지 않는다.
	 * 문자열을 1개 이상의 단위로 잘라서 위와 같은 방법으로 압축했을 때의 길이의 최솟값을 구하는 문제 
	 * @param s 문자열
	 * @return 문자열을 압축했을 때의 가장 최솟값
	 */
	public int solution(String s) {
		int answer = s.length();
		for (int i = 0; i <= s.length() / 2; i++)
			answer = Math.min(answer, split(s, i + 1));
		return answer;
	}

	public int split(String s, int count) {
		String[] split = new String[s.length() / count + 1];
		for (int i = 0, idx = 0; i < s.length(); i += count, idx++)
			split[idx] = s.substring(i, Math.min(i + count, s.length()));
		int result = 0;
		for (int i = 0; i < split.length;) {
			String cur = split[i++];
			if (cur == null)
				break;
			int cnt = 1;
			while (i < split.length && cur.equals(split[i])) {
				i++;
				cnt++;
			}
			result += cur.length();
			if (cnt > 1)
				result += Integer.toString(cnt).length();
		}
		return result;
	}
}