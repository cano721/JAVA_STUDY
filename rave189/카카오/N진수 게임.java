package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		int n = 16;
		int t = 16;
		int m = 2;
		int p = 2;
		System.out.println(solution.solution(n, t, m, p));
	}
}

class Solution {
	/**
	 * n���� ������ �Ѵ�.
	 * n���� ������ 0���� �����Ͽ� ���ư��鼭 ���ڸ� ���ϴ� �����̴�.
	 * ���ڰ� �� �ڸ��� �Ѿ�� �� �ڸ��� ��� ���Ѵ�.
	 * 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1, 0, 1, 1, 1, 2, 1, 3 ...
	 * ���⿡ ���׷��̵��Ͽ� n�������� ���ڸ� ���ʴ�� ���̷��� �Ѵ�.
	 * ���ؾ��ϴ� t���� ���ڸ� ���ϴ� ����
	 * @param n ����
	 * @param t ���ؾ��ϴ� ������ ����
	 * @param m �� ��� ��
	 * @param p �ڱ� ����
	 * @return ���ؾ��ϴ� ���ڵ�
	 */
	public String solution(int n, int t, int m, int p) {
		StringBuilder answer = new StringBuilder();
		String num = "0";
		while (t > 0) {
			if (p - num.length() <= 0) {
				answer.append(num.charAt(p - 1));
				t--;
				p += m;
			}
			p -= num.length();
			num = add(num, n);
		}
		return answer.toString();
	}

	public String add(String num, int n) {
		StringBuilder result = new StringBuilder();
		int idx = num.length() - 1;
		int carry = 0;
		while (true) {
			if (idx < 0) {
				if (carry != 0)
					result.append(carry);
				break;
			}
			int nextNumber;
			if (idx == num.length() - 1)
				nextNumber = changeCharToInt(num.charAt(idx--)) + 1 + carry;
			else
				nextNumber = changeCharToInt(num.charAt(idx--)) + carry;
			if (nextNumber >= n) {
				carry = 1;
				nextNumber -= n;
			} else
				carry = 0;
			result.append(changeIntToChar(nextNumber));
		}
		return result.reverse().toString();
	}

	public int changeCharToInt(char ch) {
		if ('A' <= ch && ch <= 'F')
			return ch - 'A' + 10;
		return ch - '0';
	}

	public char changeIntToChar(int n) {
		if (n >= 10)
			return (char) (n - 10 + 'A');
		return (char) (n + '0');
	}
}