package Programmers;

import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		int[] a = // { 9, -1, -5 };
				{ -16, 27, 65, -2, 58, -92, -71, -68, -61, -33 };
		System.out.println(solution.solution(a));
	}
}

class Solution {
	/**
	 * ���ʿ� v���� ū��, v���� ���� ���� �����ϴ� �켱����ť��
	 * �����ʿ� v���� ū��, v���� ���� ���� �����ϴ� �켱����ť 4���� ����ؼ� Ǯ���� �߾���.
	 * �߰��� -2�� ���� �� �ִ°� �ƴѰ��ؼ� �����ô��� ���� �� ������ �ʿ� ���� �ּҰ��� �����ϸ� �ƴ�.
	 * �ᱹ TreeSet 2���� �ٲ㼭 �ذ��� �� �־���.
	 * @param a
	 * @return
	 */
	public int solution(int[] a) {
		int answer = 0;
		TreeSet<Integer> right = new TreeSet<>();
		TreeSet<Integer> left = new TreeSet<>();
		for (int v : a)
			right.add(v);
		for (int v : a) {
			right.remove(v);
			int leftMin = left.isEmpty() ? Integer.MAX_VALUE : left.first();
			int rightMin = right.isEmpty() ? Integer.MAX_VALUE : right.first();
			if (leftMin >= v || rightMin >= v)
				answer++;
			left.add(v);
		}
		return answer;
	}
}