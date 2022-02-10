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
	 * 스킬이 있고, 선행 스킬이 있다.
	 * 스킬을 배우는 순서가 주어지고, 여러가지 스킬트리가 주어질 때
	 * 가능한 스킬트리의 개수를 구하는 문제
	 * hash로 각각의 인덱스를 부여한 후 현재 배울수 있는 인덱스보다 큰 수면 못배우고 나머지 경우는 다 배울 수 있다.
	 * @param skill 스킬을 배우는 순서
	 * @param skill_trees 스킬트리
	 * @return 가능한 스킬트리의 수
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