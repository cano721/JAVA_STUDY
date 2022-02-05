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
	 * ������ �����뿡 ������� �����Ǿ� �ִ�.
	 * �� �����뿡�� ��� ������ ������ ��� 1�� �̻� �����ϴ� ���� ª�� ������ ã�� ����
	 * ���� ª�� ������ ���� ����� ���� ��ȣ�� ���� ������ ��ȯ�Ѵ�.
	 * @param gems ������ ������ ������
	 * @return
	 */
	public int[] solution(String[] gems) {
		int[] answer = new int[2];
		for (String gem : gems) {
			type.add(gem);
		}
		int start = 0, end = 0, min = Integer.MAX_VALUE;
		// ���̽��� �� ������
		while (end < gems.length) {
			if (jewels.size() < type.size()) {
				jewels.put(gems[end], jewels.getOrDefault(gems[end], 0) + 1);
				end++;
			}

			// ũ�Ⱑ �޶��������� ������ �Ѵ�.
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