package Programmers;

import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();

	}
}

class Solution {
	/**
	 * ��ȣ ���ڿ��� �־�����, ������ ���� ������ ���� ���� ����� ��ȯ�ϴ� ����
	 * 1. �Է��� �� ���ڿ��� ���, �� ���ڿ��� ��ȯ�մϴ�. 
	 * 2. ���ڿ� w�� �� "�������� ��ȣ ���ڿ�" u, v�� �и��մϴ�. ��, u�� "�������� ��ȣ ���ڿ�"�� �� �̻� �и��� �� ����� �ϸ�, v�� �� ���ڿ��� �� �� �ֽ��ϴ�. 
	 * 3. ���ڿ� u�� "�ùٸ� ��ȣ ���ڿ�" �̶�� ���ڿ� v�� ���� 1�ܰ���� �ٽ� �����մϴ�. 
	 * 	3-1. ������ ��� ���ڿ��� u�� �̾� ���� �� ��ȯ�մϴ�. 
	 * 4. ���ڿ� u�� "�ùٸ� ��ȣ ���ڿ�"�� �ƴ϶�� �Ʒ� ������ �����մϴ�. 
	 * 	4-1. �� ���ڿ��� ù ��° ���ڷ� '('�� ���Դϴ�. 
	 * 	4-2. ���ڿ� v�� ���� 1�ܰ���� ��������� ������ ��� ���ڿ��� �̾� ���Դϴ�. 
	 * 	4-3. ')'�� �ٽ� ���Դϴ�. 
	 * 	4-4. u�� ù ��°�� ������ ���ڸ� �����ϰ�, ������ ���ڿ��� ��ȣ ������ ����� �ڿ� ���Դϴ�. 
	 * 	4-5. ������ ���ڿ��� ��ȯ�մϴ�.
	 * @param s ��ȣ ���ڿ�
	 * @return �ùٸ� ��ȣ ���ڿ�
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