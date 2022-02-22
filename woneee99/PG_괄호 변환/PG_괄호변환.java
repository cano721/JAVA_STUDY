import java.util.Stack;

public class PG_괄호변환 {
	static int idx = 0;

	public static void main(String[] args) {
		String p = "(()())()";

		String answer = "";

		answer = cal(p);
		System.out.println(answer);
	}

	static String cal(String s) {
		String answer = "";

		if (s.isEmpty())
			return s;

		boolean ck = check(s);
		String u = s.substring(0, idx);
		String v = s.substring(idx, s.length());

		if (ck) {
			return u + cal(v);
		} else {
			answer = "(" + cal(v) + ")";

			for (int i = 1; i < u.length() - 1; i++) {
				char c = u.charAt(i);
				if (c == '(')
					answer += ')';
				else
					answer += '(';
			}
		}

		return answer;
	}

	static boolean check(String str) {
		Stack<Character> s = new Stack<>();
		boolean chk = true;
		int left = 0, right = 0;

		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == '(') {
				left++;
				s.add(c);
			} else {
				right++;
				if (s.isEmpty())
					chk = false;
				else {
					s.pop();
				}
			}
			if (left == right) {
				idx = i + 1;
				return chk;
			}
		}
		return chk;
	}

}
