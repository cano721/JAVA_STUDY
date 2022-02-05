package Programmers;

import java.util.HashMap;
import java.util.HashSet;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	HashSet<String> type = new HashSet<>();
	HashMap<String, Integer> jewels = new HashMap<>();

	/**
	 * 보석이 진열대에 순서대로 진열되어 있다.
	 * 이 진열대에서 모든 종류의 보석을 적어도 1개 이상 포함하는 가장 짧은 구간을 찾는 문제
	 * 만약 짧은 구간이 여러 개라면 시작 번호가 작은 구간을 반환한다.
	 * @param gems 보석이 진열된 진열대
	 * @return
	 */
	public int[] solution(String[] gems) {
		int[] answer = new int[2];
		for (String gem : gems) {
			type.add(gem);
		}
		int start = 0, end = 0, min = Integer.MAX_VALUE;
		// 베이스는 투 포인터
		while (end < gems.length) {
			if (jewels.size() < type.size()) {
				jewels.put(gems[end], jewels.getOrDefault(gems[end], 0) + 1);
				end++;
			}

			// 크기가 달라질때까지 빼봐야 한다.
			while (jewels.size() == type.size()) {
				if (end - start < min) {
					min = end - start;
					answer[0] = start + 1;
					answer[1] = end;
				}
				jewels.put(gems[start], jewels.get(gems[start]) - 1);
				if (jewels.get(gems[start]) == 0)
					jewels.remove(gems[start]);
				start++;
			}
		}
		return answer;
	}
}