package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class baekjoon9935_문자열_폭발 {
	// stack 사용
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String origin = br.readLine();
		String remove = br.readLine();
		Stack<Character> stack = new Stack<Character>();

		for (int i = 0; i < origin.length(); i++) {
			stack.push(origin.charAt(i));

			if (stack.size() >= remove.length()) {
				boolean flag = true;
				for (int j = 0; j < remove.length(); j++) {
					if (stack.get(stack.size() - remove.length() + j) != remove.charAt(j)) {
						flag = false;
						break;                                            
					}
				}
				if (flag) {
					for (int j = 0; j < remove.length(); j++) {
						stack.pop();
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(char ch : stack) {
			sb.append(ch);
		}
		
		System.out.println(sb.length() > 0 ? sb.toString() : "FRULA");
	}
}