package Programmers;

import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	HashMap<Character, Integer> hash = new HashMap<>();

	/**
	 * ��ų�� �ְ�, ���� ��ų�� �ִ�.
	 * ��ų�� ���� ������ �־�����, �������� ��ųƮ���� �־��� ��
	 * ������ ��ųƮ���� ������ ���ϴ� ����
	 * hash�� ������ �ε����� �ο��� �� ���� ���� �ִ� �ε������� ū ���� ������ ������ ���� �� ��� �� �ִ�.
	 * @param skill ��ų�� ���� ����
	 * @param skill_trees ��ųƮ��
	 * @return ������ ��ųƮ���� ��
	 */
	public int solution(String skill, String[] skill_trees) {
		init(skill);
		int answer = 0;
		loop: for (String skillTree : skill_trees) {
			int skillIdx = 1;
			for (char ch : skillTree.toCharArray()) {
				int index = hash.getOrDefault(ch, 0);
				if (index > skillIdx)
					continue loop;
				else if (index == skillIdx)
					skillIdx++;
			}
			answer++;
		}
		return answer;
	}

	public void init(String skill) {
		int count = 1;
		for (char ch : skill.toCharArray()) {
			hash.put(ch, count++);
		}
	}
}