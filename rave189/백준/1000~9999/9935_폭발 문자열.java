package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 문자열이 주어진다.
 * 해당 문자열에는 폭발 문자열이 섞여있다.
 * 문자열에서 폭발 문자열을 제거한 후 문자를 출력하는 문제
 * 제거한 문자가 빈 문자열이면 FRULA를 출력한다.
 * @author Rave
 *
 */
public class Main {

	/**
	 * 시간은 오히려 별로 신경 안써도 되는 문제
	 * 테케가 조금 빡빡해서 String +을 사용하면 메모리 초과가 바로 나온다.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		String str = br.readLine();
		String bomb = br.readLine();
		int length = bomb.length();
		Stack<Character> st = new Stack<>();
		loop: for (int i = 0; i < str.length(); i++) {
			st.add(str.charAt(i));
			if (st.size() >= length) {
				for (int j = 1; j <= length; j++)
					if (bomb.charAt(length - j) != st.get(st.size() - j))
						continue loop;
				for (int j = 0; j < bomb.length(); j++)
					st.pop();
			}
		}
		for (char ch : st)
			answer.append(ch);
		System.out.print(answer.length() == 0 ? "FRULA" : answer);
	}
}