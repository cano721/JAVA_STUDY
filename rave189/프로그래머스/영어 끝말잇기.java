package Programmers;

import java.util.HashSet;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();

	}
}

class Solution {
	/**
	 * N���� ����� �����ձ⸦ �Ѵ�.
	 * ������� ���� �ܾ �־����� ��, Ż������ ��ȣ�� �ڽ��� �� ��° ���ʿ� ���ϴٰ� Ż���ߴ����� ���ϴ� ����
	 * �����ձ��� ��Ģ�� ������ �̾�� �ϰ�, ���ߴ� �ܾ ���ϸ� Ż���̴�.
	 * Ż���ڰ� ������ �ʴ´ٸ� [0, 0]�� ��ȯ�Ѵ�.
	 * @param n ����� ��
	 * @param words ������� ���� �ܾ�
	 * @return Ż������ ��ȣ �� �� ��° ��������
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