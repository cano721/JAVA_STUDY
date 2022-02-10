package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	/**
	 * 0에서 출발한다.
	 * 임의의 지점 k에서 k+a로 이동하려고 하면 a만큼의 비용이 든다.
	 * 임의의 지점 k에서 k*2로 이동하려고 하면 0만큼의 비용이 든다.
	 * 0에서 n까지 가기 위한 비용의 최솟값을 구하는 문제
	 * 
	 * 그리디로 푼다.
	 * @param n 도착 지점
	 * @return 비용의 최솟값
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