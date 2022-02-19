package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();

	}
}

class Solution {
	/**
	 * N���� ����� �ִ�.
	 * 1-2, 3-4, 5-6, 7-8 ... N-1-N������ ���� ������ �Ѵ�.
	 * �� ��, a�� ����� b�� ����� �� �� ��⸦ �ؾ� �������� ���ϴ� ����
	 * a���� b���� ������ ������ �׻� �̱��.
	 * @param n �� ��� ��
	 * @param a a�� ���
	 * @param b b�� ���
	 * @return
	 */
	public int solution(int n, int a, int b) {
		int answer = 0;
		while (a != b) {
			if (a % 2 == 1)
				a++;
			if (b % 2 == 1)
				b++;
			a /= 2;
			b /= 2;
			answer++;
		}
		return answer;
	}
}