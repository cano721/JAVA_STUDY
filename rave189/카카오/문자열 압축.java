package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		String s = 
//				"aabbaccc";
//				"ababcdcdababcdcd";
//				"abcabcdede";
//				"abcabcabcabcdededededede";
				"xababcdcdababcdcd";
		System.out.println(solution.solution(s));
	}
}

class Solution {
	/**
	 * ���ڿ��� �־����� ��, ���� ���ڿ��� ���ӵȴٸ� ���ӵ� Ƚ�� + ���ڿ��� �ٿ� �����ϵ��� �Ѵ�.
	 * ���ӵ� Ƚ���� 1�� ��� ������ �ʴ´�.
	 * ���ڿ��� 1�� �̻��� ������ �߶� ���� ���� ������� �������� ���� ������ �ּڰ��� ���ϴ� ���� 
	 * @param s ���ڿ�
	 * @return ���ڿ��� �������� ���� ���� �ּڰ�
	 */
	public int solution(String s) {
		int answer = s.length();
		for (int i = 0; i <= s.length() / 2; i++)
			answer = Math.min(answer, split(s, i + 1));
		return answer;
	}

	public int split(String s, int count) {
		String[] split = new String[s.length() / count + 1];
		for (int i = 0, idx = 0; i < s.length(); i += count, idx++)
			split[idx] = s.substring(i, Math.min(i + count, s.length()));
		int result = 0;
		for (int i = 0; i < split.length;) {
			String cur = split[i++];
			if (cur == null)
				break;
			int cnt = 1;
			while (i < split.length && cur.equals(split[i])) {
				i++;
				cnt++;
			}
			result += cur.length();
			if (cnt > 1)
				result += Integer.toString(cnt).length();
		}
		return result;
	}
}