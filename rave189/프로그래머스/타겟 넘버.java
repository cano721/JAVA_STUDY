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
	 * N개의 숫자가 주어진다.
	 * 이 숫자를 적당히 더하고 적당히 빼서 구한 결과 값이 target과 같아지려고 한다.
	 * 만들 수 있는 경우의 수를 구하는 문제
	 * @param numbers N개의 수
	 * @param target 만들고 싶은 수
	 * @return target을 만들 수 있는 경우의 수
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