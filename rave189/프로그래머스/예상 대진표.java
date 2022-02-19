package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();

	}
}

class Solution {
	/**
	 * N명의 사람이 있다.
	 * 1-2, 3-4, 5-6, 7-8 ... N-1-N번끼리 서로 경쟁을 한다.
	 * 이 때, a번 사람과 b번 사람이 몇 번 경기를 해야 만나는지 구하는 문제
	 * a번과 b번은 만나기 전까지 항상 이긴다.
	 * @param n 총 사람 수
	 * @param a a번 사람
	 * @param b b번 사람
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