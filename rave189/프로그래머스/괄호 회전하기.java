package Programmers;

import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();

	}
}

class Solution {
	/**
	 * (), [], {}은 모두 올바른 괄호 문자열이다.
	 * (A), [A], {A}도 모두 올바른 괄호 문자열이다.
	 * A와 B가 올바른 괄호 문자열이라면 AB도 올바른 괄호 문자열이다.
	 * 문자열을 왼쪽으로 x만큼 회전시킬 때, s가 올바른 괄호 문자열이 되는 x의 개수를 구하는 문제
	 * 
	 * 다 돌려보면서 올바른 괄호 문자열인지 판별한다.
	 * @param s 문자열
	 * @return 회전시킨 후 올바른 괄호 문자열이 되는 개수
	 */
	public int solution(String s) {
		int answer = 0;
		StringBuilder result = new StringBuilder(s);
		for (int i = 0; i < s.length(); i++) {
			if (isValid(result.toString())) {
				answer++;
			}
			result.append(result.charAt(0));
			result.deleteCharAt(0);
		}
		return answer;
	}

	public boolean isValid(String s) {
		Stack<Character> st = new Stack<>();
		for (char ch : s.toCharArray()) {
			try {
				if (ch == '[' || ch == '{' || ch == '(')
					st.add(ch);
				else if (ch == ']') {
					if (st.peek() != '[')
						return false;
					st.pop();
				} else if (ch == '}') {
					if (st.peek() != '{')
						return false;
					st.pop();
				} else {
					if (st.peek() != '(')
						return false;
					st.pop();
				}
			} catch (Exception e) {
				return false;
			}
		}
		return st.isEmpty();
	}
}