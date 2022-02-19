package Programmers;

import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();

	}
}

class Solution {
	/**
	 * 괄호 문자열이 주어질때, 다음과 같은 과정을 통해 나온 결과를 반환하는 문제
	 * 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다. 
	 * 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다. 
	 * 3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다. 
	 * 	3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다. 
	 * 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다. 
	 * 	4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다. 
	 * 	4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다. 
	 * 	4-3. ')'를 다시 붙입니다. 
	 * 	4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다. 
	 * 	4-5. 생성된 문자열을 반환합니다.
	 * @param s 괄호 문자열
	 * @return 올바른 괄호 문자열
	 */
	public String solution(String s) {
		return correctString(s);
	}

	public String correctString(String s) {
		if (s.equals(""))
			return s;
		String[] split = getUV(s);
		if (isValid(split[0])) {
			return split[0] + correctString(split[1]);
		} else {
			return "(" + correctString(split[1]) + ")" + reverse(split[0]);
		}
	}

	public String[] getUV(String s) {
		StringBuilder u = new StringBuilder();
		int idx = 0, count = 0;
		while (idx < s.length()) {
			if (s.charAt(idx) == '(')
				count++;
			else
				count--;
			u.append(s.charAt(idx++));
			if (count == 0)
				break;
		}
		String v = s.substring(idx);
		return new String[] { u.toString(), v };
	}

	public boolean isValid(String s) {
		Stack<Character> st = new Stack<>();
		for (char ch : s.toCharArray()) {
			try {
				if (ch == '(')
					st.add(ch);
				else
					st.pop();
			} catch (Exception e) {
				return false;
			}
		}
		return st.isEmpty();
	}

	public String reverse(String s) {
		char[] arr = s.toCharArray();
		StringBuilder result = new StringBuilder();
		for (int i = 1; i < arr.length - 1; i++) {
			result.append(arr[i] == '(' ? ')' : '(');
		}
		return result.toString();
	}
}