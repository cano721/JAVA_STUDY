package Programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
		int[] result = solution.solution(s);
		for (int v : result)
			System.out.println(v);
	}
}

class Solution {
	ArrayList<Integer> answer = new ArrayList<>();
	TreeMap<Integer, HashSet<Integer>> tuple = new TreeMap<>();

	/**
	 * 셀수있는 수량의 순서있는 열거 또는 어떤 순서를 따르는 요소들의 모음을 튜플(tuple)이라고 한다.
	 * 튜플은 다음과 같은 성질을 가지고 있다.
	 * 1. 중복된 원소가 있을 수 있다.
	 * 2. 원소에 정해진 순서가 있으며, 원소의 순서가 다르면 서로 다른 튜플입니다.
	 * 3. 튜플의 원소 개수는 유한합니다.
	 * 또한 튜플은 여러 개의 집합으로 이루어질 수 있는데, 이 집합의 원소는 순서가 바뀌어도 상관 없다.
	 * {{2}, {2, 1}, {2, 1, 3}, {2, 1, 3, 4}}
	 * {{2, 1, 3, 4}, {2}, {2, 1, 3}, {2, 1}}
	 * {{1, 2, 3}, {2, 1}, {1, 2, 4, 3}, {2}}
	 * (2, 1, 4, 3)의 튜플의 경우 위의 3가지 경우가 모두 같은 튜플을 나타낸다.
	 * 튜플의 집합이 주어질때, 튜플을 구하는 문제
	 * @param s 튜플의 집합
	 * @return 튜플
	 */
	public int[] solution(String s) {
		parse(s.substring(1, s.length() - 1));
		getTuple();
		return answer.stream().mapToInt(Integer::intValue).toArray();
	}

	public void parse(String s) {
		ArrayList<String> elementSet = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ',' && s.charAt(i - 1) == '}') {
				elementSet.add(s.substring(0, i));
				s = s.substring(i + 1);
				i = -1;
			}
		}
		elementSet.add(s);
		for (String e : elementSet) {
			HashSet<Integer> elements = new HashSet<>();
			String[] numbers = e.substring(1, e.length() - 1).split(",");
			for (String num : numbers)
				elements.add(Integer.parseInt(num));
			tuple.put(elements.size(), elements);
		}
	}

	public void getTuple() {
		for (int key : tuple.keySet()) {
			HashSet<Integer> elements = tuple.get(key);
			for (int v : answer)
				elements.remove(v);
			answer.add(elements.iterator().next());
		}
	}
}