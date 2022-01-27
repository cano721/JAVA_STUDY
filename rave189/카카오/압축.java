package Programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		Solution s = new Solution();
		String msg = // "KAKAO";
				"TOBEORNOTTOBEORTOBEORNOT";
		int[] result = s.solution(msg);
		for (int v : result)
			System.out.println(v);
	}

}

class Solution {
	Map<String, Integer> hash = new HashMap<>();
	ArrayList<Integer> answer = new ArrayList<>();
	int count = 1;

	/**
	 * ������ ���� �˰����� ���� �޼����� �����Ѵ�.
	 * 1. ���̰� 1�� ��� �ܾ �����ϵ��� ������ �ʱ�ȭ�Ѵ�.
	 * 2. �������� ���� �Է°� ��ġ�ϴ� ���� �� ���ڿ� w�� ã�´�.
	 * 3. w�� �ش��ϴ� ������ ���� ��ȣ�� ����ϰ�, �Է¿��� w�� �����Ѵ�.
	 * 4. �Է¿��� ó������ ���� ���� ���ڰ� �����ִٸ�(c), w+c�� �ش��ϴ� �ܾ ������ ����Ѵ�.
	 * 5. �ܰ� 2�� ���ư���.
	 * @param msg ������ ���ڿ�
	 * @return ���� ���
	 */
	public int[] solution(String msg) {
		init();
		for (int i = 0; i < msg.length();) {
			// ���� �� ���ڿ� ã��
			String longestStr = "";
			while (i < msg.length() && hash.containsKey(longestStr + msg.charAt(i)))
				longestStr += msg.charAt(i++);
			// ���Ű� ����? �ٵ� �����?
			answer.add(hash.get(longestStr));
			// ���� ���ڰ� �ִٸ� ���� �� ���ڿ��� ���ļ� ������ ������ ����Ѵ�.
			if (i < msg.length()) {
				longestStr += msg.charAt(i);
				hash.put(longestStr, count++);
			}
		}
		return answer.stream().mapToInt(Integer::intValue).toArray();
	}

	public void init() {
		// 1. �ʱ�ȭ
		for (int i = 0; i < 26; i++)
			hash.put((char) ('A' + i) + "", count++);
	}
}