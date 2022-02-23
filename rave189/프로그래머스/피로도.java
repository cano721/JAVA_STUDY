package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();

	}
}

class Solution {
	int answer = 0;
	boolean[] visited;
	int[][] dungeons;

	/**
	 * ���� �Ƿε��� �־����� ��, ������ �� �� �ִ� �ִ� ������ ���ϴ� ����
	 * ������ �� ���� �� �� �ִ�.
	 * ������ ������ [�ּ� �ʿ� �Ƿε�, �Ҹ� �Ƿε�]�� �־�����.
	 * @param k ���� �Ƿε�
	 * @param dungeons �������� ����
	 * @return �� �� �ִ� �ִ� ���� ����
	 */
	public int solution(int k, int[][] dungeons) {
		init(dungeons);
		bruteforce(k, 0);
		return answer;
	}

	public void init(int[][] dungeons) {
		visited = new boolean[dungeons.length];
		this.dungeons = dungeons;
	}

	public void bruteforce(int k, int count) {
		for (int i = 0; i < dungeons.length; i++) {
			if (visited[i] || dungeons[i][0] > k)
				continue;
			visited[i] = true;
			bruteforce(k - dungeons[i][1], count + 1);
			visited[i] = false;
		}
		answer = Math.max(answer, count);
	}
}