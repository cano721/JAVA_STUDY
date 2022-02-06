package Programmers;

import java.util.HashSet;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	int[] result = { 6, 6, 5, 4, 3, 2, 1 };

	/**
	 * �ζ� ��÷ ��ȣ�� ������ �ζ��� ��ȣ�� �־�����.
	 * ������ �ζ� ��ȣ���� 0�̶�� ���ϵ� ī�尡 �����Ͽ� ��� ���� �� �� �ִ�.
	 * ������ �ζǰ� �� �� �ִ� ���� ���� ����� ���� ���� ����� ���ϴ� ����
	 * @param lottos ������ �ζ� ��ȣ
	 * @param win_nums ��÷ ��ȣ
	 * @return �ְ� ���, �ּ� ���
	 */
	public int[] solution(int[] lottos, int[] win_nums) {
		int[] answer = new int[2];
		HashSet<Integer> hash = new HashSet<>();
		for (int num : win_nums) {
			hash.add(num);
		}
		int zeroCnt = 0, collect = 0;
		for (int num : lottos) {
			if (num == 0) {
				zeroCnt++;
			} else if (hash.contains(num)) {
				hash.remove(num);
				collect++;
			}
		}
		answer[0] = result[collect + Math.min(zeroCnt, hash.size())];
		answer[1] = result[collect];
		return answer;
	}
}