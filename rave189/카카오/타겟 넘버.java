package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution s = new Solution();
		int[] numbers = { 1, 1, 1, 1, 1 };
		int target = 3;
		System.out.println(s.solution(numbers, target));
	}
}

class Solution {
	int answer = 0;
	int[] numbers;
	int target;

	/**
	 * N���� ���ڰ� �־�����.
	 * �� ���ڸ� ������ ���ϰ� ������ ���� ���� ��� ���� target�� ���������� �Ѵ�.
	 * ���� �� �ִ� ����� ���� ���ϴ� ����
	 * @param numbers N���� ��
	 * @param target ����� ���� ��
	 * @return target�� ���� �� �ִ� ����� ��
	 */
	public int solution(int[] numbers, int target) {
		init(numbers, target);
		search(0, 0);
		return answer;
	}

	public void init(int[] numbers, int target) {
		this.numbers = numbers;
		this.target = target;
	}

	public void search(int cur, int sum) {
		if (cur >= numbers.length) {
			if (sum == target)
				answer++;
			return;
		}
		search(cur + 1, sum - numbers[cur]);
		search(cur + 1, sum + numbers[cur]);
	}
}