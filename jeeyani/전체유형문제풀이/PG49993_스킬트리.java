package 전체유형문제풀이;

import java.util.*;

/*
 * 
 * */

public class PG49993_스킬트리 {

	public static void main(String[] args) {

		String skill = "CBD";
		String[] skill_trees = { "BACDE", "CBADF", "AECB", "BDA" };
		int result = solution(skill, skill_trees);

		System.out.println(result);

	}

	private static int solution(String skill, String[] skill_trees) {
		int answer = 0;

		for (String tree : skill_trees) {
			//int idx = 0;
			int idx = -1;
			boolean chk = false;
			
			for (int i = 0; i < skill.length(); i++) {
				int temp = tree.indexOf(skill.charAt(i));

				//해당 스킬을 찍지 않은 경우
				if (temp == -1) {
					idx = Integer.MAX_VALUE;
					continue;
				}
				//스킬트리 순서 확인하기
				if (idx < temp) {
					//temp = idx;
					idx = temp;
				}
				//스킬순서가 잘못된 경우
				else {
					chk = true;
					break;
				}

			}
			if (!chk) answer++;
		}

		return answer;
	}

}