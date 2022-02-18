package 전체유형문제풀이;

import java.util.*;

/*
 * 
 * stack 자료 구조
 * 
 * */
public class PG76502_괄호회전하기 {

	public static void main(String[] args) {

		String s = "}]()[{";

		int result = solution(s);

		System.out.println(result);

	}

	private static int solution(String s) {
		int answer = 0;

		Stack<String> stack = new Stack<>();

		for (int i = 0; i < s.length(); i++) {

			stack.clear(); //초기화
			//돌때마다 제일 앞에 괄호 잘라서 뒤에 붙여주기
			s = s.substring(1, s.length()) + s.charAt(0);

			for (int j = 0; j < s.length(); j++) {
				
				String temp = s.substring(j, j + 1);

				if (stack.isEmpty()) {
					stack.push(temp);
					continue;
				}

				if ("]".equals(temp)) {
					if ("[".equals(stack.peek()))
						stack.pop();
					continue;
				} else if ("}".equals(temp)) {
					if ("{".equals(stack.peek()))
						stack.pop();
					continue;
				} else if (")".equals(temp)) {
					if ("(".equals(stack.peek()))
						stack.pop();
					continue;
				}
				
				// (,{,[ 인 경우 스택에 담기
				stack.push(temp);
			}

			//올바른 괄호라면 cnt+1
			if (stack.isEmpty())
				answer++;

		}

		return answer;
	}

}