package elwlahd555.programmers;

import java.util.Stack;

public class 프로그래머스60058_괄호_변환 {
	public static void main(String[] args) {
		String p="(()())()";
		System.out.println(solution(p));
	}
	public static String solution(String p) {
		// 1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
		if (p.equals(""))
			return "";

		StringBuilder answer = new StringBuilder();
		StringBuilder sb = new StringBuilder(p);
		StringBuilder u = new StringBuilder();
		StringBuilder v = new StringBuilder();

		int open = 0; // ( 인 경우
		int close = 0; // ) 인경우
		// 2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다.
		// 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == '(')
				open++;
			if (sb.charAt(i) == ')')
				close++;
			// 균형 괄호 문자열 경우
			if (open == close) {
				u.append(sb.substring(0, i + 1));
				v.append(sb.substring(i + 1));
				break;
			}
		}

		// 3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
		// 3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
		if (isCorrect(u.toString())) {
			return u.append(solution(v.toString())).toString();
		}

		// 4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
		// 4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
		// 4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
		// 4-3. ')'를 다시 붙입니다.
		answer.append("(").append(solution(v.toString())).append(")");
		u.deleteCharAt(0);
		u.deleteCharAt(u.length() - 1);
		answer.append(swap(u.toString()));
		return answer.toString();
	}

	// 옳바른 문자열인지 확인
	public static boolean isCorrect(String p) {
		Stack<String> st = new Stack<>();
		for (String s : p.split("")) {
			// ( 만 스택에 넣어줌
			if (s.equals("("))
				st.push(s);
			// 스택엔 ( 만 있으므로 )를 만나면 ( 를 빼줌
			if (s.equals(")") && !st.isEmpty())
				st.pop();
		}
		// 스택 사이즈가 0이면 옳바른 문자열
		// 0이 아닌 경우 ( 가 남아있음
		return st.size() < 1;
	}

	// 4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
	public static String swap(String u) {
		StringBuilder swapString = new StringBuilder();
		for (char c : u.toCharArray()) {
			if (c == '(')
				swapString.append(")");
			if (c == ')')
				swapString.append("(");
		}
		return swapString.toString();
	}
}
