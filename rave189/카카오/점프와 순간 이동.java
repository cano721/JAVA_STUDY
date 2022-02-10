package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	/**
	 * 0���� ����Ѵ�.
	 * ������ ���� k���� k+a�� �̵��Ϸ��� �ϸ� a��ŭ�� ����� ���.
	 * ������ ���� k���� k*2�� �̵��Ϸ��� �ϸ� 0��ŭ�� ����� ���.
	 * 0���� n���� ���� ���� ����� �ּڰ��� ���ϴ� ����
	 * 
	 * �׸���� Ǭ��.
	 * @param n ���� ����
	 * @return ����� �ּڰ�
	 */
	public int solution(int n) {
		int answer = 0;
		while (n > 0) {
			if (n % 2 == 0)
				n /= 2;
			else {
				n--;
				answer++;
			}
		}
		return answer;
	}
}