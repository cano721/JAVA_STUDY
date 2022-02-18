import java.util.Stack;

class PG_괄호회전하기 {
	public int solution(String s) {
		int count = 0;
		StringBuilder sb = new StringBuilder(s);
		for (int i = 0; i < s.length(); i++) {
			if (cal(sb.toString())) count++;

			sb.append(sb.charAt(0));
			sb.deleteCharAt(0);
		}
		return count;
	}

	boolean cal(String s) {
		Stack<Character> stack = new Stack<>();
		char[] cr = s.toCharArray();
		for (int i = 0; i < cr.length; i++) {
			char ch = s.charAt(i);
			if (ch == '{' || ch == '(' || ch == '[')
				stack.add(ch);
			else if (stack.empty()) {
				return false;
			} else if (ch == '}') {
				if (stack.pop() != '{')
					return false;
			} else if (ch == ')') {
				if (stack.pop() != '(')
					return false;
			} else {
				if (stack.pop() != '[')
					return false;
			}
		}
		if (!stack.isEmpty())
			return false;
		return true;
	}
}