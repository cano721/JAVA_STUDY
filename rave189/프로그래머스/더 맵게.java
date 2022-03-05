package Programmers;

import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	/**
	 * 음식들의 스코빌 지수가 주어진다.
	 * 모든 음식의 스코빌 지수가 K이상이 되도록 하려고 한다.
	 * K이상으로 만들기 위해 가장 스코빌 지수가 작은 음식 + 두 번째로 작은 음식 * 2를 통해 음식을 합쳐 스코빌 지수를 높일 수 있다.
	 * 모든 음식이 K이상이 되기 위해 음식을 최소 몇 번 섞어야 하는지 구하는 문제
	 * 만약 모든 음식을 K이상으로 만들 수 없다면 -1을 출력한다.
	 * @param scoville 음식들의 스코빌 지수
	 * @param K 조건
	 * @return 최소 횟수
	 */
	public int solution(int[] scoville, int K) {
		int answer = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int v : scoville)
			pq.add(v);
		while (pq.size() > 1 && pq.peek() < K) {
			pq.add(pq.poll() + (pq.poll() * 2));
			answer++;
		}
		return pq.peek() < K ? -1 : answer;
	}
}