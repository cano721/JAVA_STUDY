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
	 * 왼쪽에 v보다 큰값, v보다 작은 값을 저장하는 우선순위큐와
	 * 오른쪽에 v보다 큰값, v보다 작은 값을 저장하는 우선순위큐 4개를 사용해서 풀려고 했었다.
	 * 중간에 -2는 남을 수 있는거 아닌가해서 질문봤더니 굳이 다 저장할 필요 없이 최소값만 저장하면 됐다.
	 * 결국 TreeSet 2개로 바꿔서 해결할 수 있었다.
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