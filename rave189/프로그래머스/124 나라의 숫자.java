package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();

	}
}

class Solution {
	/**
	 * 124����� ��� ���� 1, 2, 4�θ� ǥ���Ѵ�.
	 * 1 : 1
	 * 2 : 2
	 * 3 : 4
	 * 4 : 11
	 * 5 : 12
	 * 6 : 14
	 * 7 : 21
	 * 8 : 22
	 * 9 : 24
	 * 10 : 41
	 * ...
	 * �ڿ��� n�� �־��� ��, 124���󿡼� ����ϴ� ���ڷ� �ٲٴ� ����
	 * @param n �ڿ���
	 * @return 124���󿡼��� ��
	 */
	public String solution(int n) {
		StringBuilder answer = new StringBuilder();
		int t = 3;
		int digit = 1;
		while (n > t) {
			n -= t;
			t *= 3;
			digit++;
		}
		while (digit-- > 0) {
			t /= 3;
			if (1 <= n && n <= t) {
				answer.append(1);
			} else if (t + 1 <= n && n <= 2 * t) {
				answer.append(2);
				n -= t;
			} else {
				answer.append(4);
				n -= 2 * t;
			}
		}
		return answer.toString();
	}
}