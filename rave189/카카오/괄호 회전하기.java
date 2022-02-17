package Programmers;

import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();

	}
}

class Solution {
	/**
	 * (), [], {}�� ��� �ùٸ� ��ȣ ���ڿ��̴�.
	 * (A), [A], {A}�� ��� �ùٸ� ��ȣ ���ڿ��̴�.
	 * A�� B�� �ùٸ� ��ȣ ���ڿ��̶�� AB�� �ùٸ� ��ȣ ���ڿ��̴�.
	 * ���ڿ��� �������� x��ŭ ȸ����ų ��, s�� �ùٸ� ��ȣ ���ڿ��� �Ǵ� x�� ������ ���ϴ� ����
	 * 
	 * �� �������鼭 �ùٸ� ��ȣ ���ڿ����� �Ǻ��Ѵ�.
	 * @param s ���ڿ�
	 * @return ȸ����Ų �� �ùٸ� ��ȣ ���ڿ��� �Ǵ� ����
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