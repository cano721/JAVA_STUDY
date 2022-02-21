package Programmers;

import java.util.HashSet;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();

	}
}

class Solution {
	/**
	 * N명의 사람이 끝말잇기를 한다.
	 * 사람들이 말한 단어가 주어졌을 때, 탈락자의 번호와 자신의 몇 번째 차례에 말하다가 탈락했는지를 구하는 문제
	 * 끝말잇기의 규칙은 끝말을 이어야 하고, 말했던 단어를 말하면 탈락이다.
	 * 탈락자가 생기지 않는다면 [0, 0]을 반환한다.
	 * @param n 사람의 수
	 * @param words 사람들이 말한 단어
	 * @return 탈락자의 번호 및 몇 번째 차례인지
	 */
	public int[] solution(int n, String[] words) {
		int count = 1;
		int index = 0;
		HashSet<String> hash = new HashSet<>();
		char prev = words[0].charAt(0);
		for (String word : words) {
			if (hash.contains(word) || prev != word.charAt(0))
				return new int[] { index + 1, count };
			if (++index == n) {
				index = 0;
				count++;
			}
			hash.add(word);
			prev = word.charAt(word.length() - 1);
		}
		return new int[] { 0, 0 };
	}
}