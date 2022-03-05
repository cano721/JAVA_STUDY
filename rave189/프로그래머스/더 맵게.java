package Programmers;

import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	/**
	 * ���ĵ��� ���ں� ������ �־�����.
	 * ��� ������ ���ں� ������ K�̻��� �ǵ��� �Ϸ��� �Ѵ�.
	 * K�̻����� ����� ���� ���� ���ں� ������ ���� ���� + �� ��°�� ���� ���� * 2�� ���� ������ ���� ���ں� ������ ���� �� �ִ�.
	 * ��� ������ K�̻��� �Ǳ� ���� ������ �ּ� �� �� ����� �ϴ��� ���ϴ� ����
	 * ���� ��� ������ K�̻����� ���� �� ���ٸ� -1�� ����Ѵ�.
	 * @param scoville ���ĵ��� ���ں� ����
	 * @param K ����
	 * @return �ּ� Ƚ��
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