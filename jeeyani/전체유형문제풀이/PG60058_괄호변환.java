package 전체유형문제풀이;

import java.util.*;

/* 구현 */

public class PG60058_괄호변환 {

	static int idx = 0;

	public static void main(String[] args) {

		String p = "()))((()";

		String result = solution(p);

		System.out.println(result);

	}

	public static String solution(String p) {
		String answer = "";

		//1.입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
		if (p.isEmpty()) return p;

		//2.문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리
		boolean strCHK = isCor(p);
		String u = p.substring(0, idx);
		String v = p.substring(idx, p.length());

		//3.문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
		//3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
		if (strCHK) {
			return u + solution(v);
		}

		//4.문자열 u가 "올바른 괄호 문자열"이 아니라면
		else {

			//4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
			//4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다. 
			//4-3. ')'를 다시 붙입니다.    
			answer = "(" + solution(v) + ")";

			//4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다. 
			for (int i = 1; i < u.length() - 1; i++) {

				if (u.charAt(i) == '(') {
					answer += ")";
				} else
					answer += "(";
			}

		}
		return answer;
	}

	//올바른 괄호 문자열인지 체크
	public static boolean isCor(String p) {
		boolean chk = true;
		int open = 0;
		int close = 0;

		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < p.length(); i++) {

			if (p.charAt(i) == '(') {
				open++;
				stack.push(p.charAt(i));
			} else {
				close++;

				if (stack.isEmpty())
					chk = false;

				else {
					stack.pop();
				}

			}
			//균형잡힌 괄호문자열인 경우 해당 위치의 인덱스번호 기억하기
			if (open == close) {
				idx = i + 1;
				return chk;
			}
		}

		return chk;
	}

}