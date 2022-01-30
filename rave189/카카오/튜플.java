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
	 * �����ִ� ������ �����ִ� ���� �Ǵ� � ������ ������ ��ҵ��� ������ Ʃ��(tuple)�̶�� �Ѵ�.
	 * Ʃ���� ������ ���� ������ ������ �ִ�.
	 * 1. �ߺ��� ���Ұ� ���� �� �ִ�.
	 * 2. ���ҿ� ������ ������ ������, ������ ������ �ٸ��� ���� �ٸ� Ʃ���Դϴ�.
	 * 3. Ʃ���� ���� ������ �����մϴ�.
	 * ���� Ʃ���� ���� ���� �������� �̷���� �� �ִµ�, �� ������ ���Ҵ� ������ �ٲ� ��� ����.
	 * {{2}, {2, 1}, {2, 1, 3}, {2, 1, 3, 4}}
	 * {{2, 1, 3, 4}, {2}, {2, 1, 3}, {2, 1}}
	 * {{1, 2, 3}, {2, 1}, {1, 2, 4, 3}, {2}}
	 * (2, 1, 4, 3)�� Ʃ���� ��� ���� 3���� ��찡 ��� ���� Ʃ���� ��Ÿ����.
	 * Ʃ���� ������ �־�����, Ʃ���� ���ϴ� ����
	 * @param s Ʃ���� ����
	 * @return Ʃ��
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