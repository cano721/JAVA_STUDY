package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * ���ڿ��� �־�����.
 * �ش� ���ڿ����� ���� ���ڿ��� �����ִ�.
 * ���ڿ����� ���� ���ڿ��� ������ �� ���ڸ� ����ϴ� ����
 * ������ ���ڰ� �� ���ڿ��̸� FRULA�� ����Ѵ�.
 * @author Rave
 *
 */
public class Main {

	/**
	 * �ð��� ������ ���� �Ű� �Ƚᵵ �Ǵ� ����
	 * ���ɰ� ���� �����ؼ� String +�� ����ϸ� �޸� �ʰ��� �ٷ� ���´�.
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