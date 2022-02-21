package elwlahd555.socartest;

import java.util.Stack;

/*

문제 설명
다음 규칙을 지키는 문자열을 올바른 괄호 문자열이라고 정의합니다.

(), [], {} 는 모두 올바른 괄호 문자열입니다.
만약 A가 올바른 괄호 문자열이라면, (A), [A], {A} 도 올바른 괄호 문자열입니다. 예를 들어, [] 가 올바른 괄호 문자열이므로, ([]) 도 올바른 괄호 문자열입니다.
만약 A, B가 올바른 괄호 문자열이라면, AB 도 올바른 괄호 문자열입니다. 예를 들어, {} 와 ([]) 가 올바른 괄호 문자열이므로, {}([]) 도 올바른 괄호 문자열입니다.
올바른 괄호 문자열에서 딱 하나의 문자가 제거된 문자열이 있을 때, 당신은 이 문자열에 제거된 문자를 다시 삽입함으로써 다시 올바른 괄호 문자열로 만들고자 합니다. 이때, 문자열을 삽입할 수 있는 위치가 여러 개일 수 있습니다.

올바른 괄호 문자열에서 딱 하나의 문자가 제거된 문자열 s가 매개변수로 주어집니다. s에 제거된 문자 하나를 삽입해서 s를 올바른 괄호 문자열로 만들 수 있는 위치의 개수를 return 하도록 solution 함수를 완성해주세요.

제한사항
1 ≤ s의 길이 ≤ 500,000
입출력 예
s	result
"[]([[]){}"	3
"{([()]))}"	4
"(()()()"	7

 */
public class test_괄호_변환 {
	public static void main(String[] args) {
		String s = "{([()]))}";
		System.out.println(solution(s));
	}

	private static int solution(String s) {
		int answer = 0;
		int bigLeft = 0;
		int bigRight = 0;
		int midLeft = 0;
		int midRight = 0;
		int smlLeft = 0;
		int smlRight = 0;
		char need = '0';
		char sToChar[] = s.toCharArray();
		for (int i = 0; i < sToChar.length; i++) {
			if (sToChar[i] == '(') {
				smlLeft++;
			} else if (sToChar[i] == ')') {
				smlRight++;
			} else if (sToChar[i] == '{') {
				midLeft++;
			} else if (sToChar[i] == '}') {
				midRight++;
			} else if (sToChar[i] == '[') {
				bigLeft++;
			} else if (sToChar[i] == ']') {
				bigRight++;
			}
		}
		if (bigLeft != bigRight) {
			if (bigLeft > bigRight) {
				need = ']';
			} else {
				need = '[';
			}
		} else if (midLeft != midRight) {
			if (midLeft > midRight) {
				need = '}';
			} else {
				need = '{';
			}
		} else if (smlLeft != smlRight) {
			if (smlLeft > smlRight) {
				need = ')';
			} else {
				need = '(';
			}
		}
		for (int i = 0; i < sToChar.length + 1; i++) {
			if (check(s.substring(0, i) + need + s.substring(i, s.length()))) {
				answer++;
			}
		}
		return answer;
	}

	private static boolean check(String word) {
		Stack<String> st = new Stack<>();
		for (String s : word.split("")) {
			if (s.equals("(") || s.equals("{") || s.equals("["))
				st.push(s);
			else if (s.equals(")") && !st.isEmpty() && !st.peek().equals("(")) {
				return false;
			} else if (s.equals("]") && !st.isEmpty() && !st.peek().equals("[")) {
				return false;
			} else if (s.equals("}") && !st.isEmpty() && !st.peek().equals("{")) {
				return false;
			} else if (st.isEmpty()) {
				return false;
			} else {
				st.pop();
			}
		}
		return st.size() < 1;
	}

}
