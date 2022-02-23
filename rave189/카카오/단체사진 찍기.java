package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();

	}
}

class Solution {
	int answer = 0;
	char[] people = { 'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T' };
	boolean[] visited = new boolean[people.length];
	int[] seat = new int[26];
	String[] conditions;

	/**
	 * 8���� ����� �ְ� N���� ������ �־�����.
	 * 8���� ����� N���� ������ �����Ͽ� ����� ����� ���� ���ϴ� ����
	 * ������ A~C<2�� ���� ���´�.
	 * ������ ���簡�� ���� ���� ���� ������ ���Ʈ������ ã�� �ɷ�����.
	 * @param n
	 * @param data
	 * @return
	 */
	public int solution(int n, String[] data) {
		conditions = data;
		bruteforce(1);
		return answer;
	}

	public void bruteforce(int depth) {
		if (depth > people.length) {
			if (isValid())
				answer++;
			return;
		}

		for (int i = 0; i < people.length; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			seat[people[i] - 'A'] = depth;
			bruteforce(depth + 1);
			visited[i] = false;
		}
	}

	public boolean isValid() {
		for (String condition : conditions) {
			char left = condition.charAt(0);
			char right = condition.charAt(2);
			char op = condition.charAt(3);
			int distance = condition.charAt(4) - '0';
			int realDistance = Math.abs(seat[right - 'A'] - seat[left - 'A']) - 1;
			if (op == '=') {
				if (realDistance != distance)
					return false;
			} else if (op == '<') {
				if (realDistance >= distance)
					return false;
			} else {
				if (realDistance <= distance)
					return false;
			}
		}
		return true;
	}
}