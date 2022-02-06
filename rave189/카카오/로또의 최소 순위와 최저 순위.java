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
	 * 로또 당첨 번호와 구매한 로또의 번호가 주어진다.
	 * 구매한 로또 번호에는 0이라는 와일드 카드가 존재하여 어느 수나 될 수 있다.
	 * 구매한 로또가 될 수 있는 가장 높은 등수와 가장 낮은 등수를 구하는 문제
	 * @param lottos 구매한 로또 번호
	 * @param win_nums 당첨 번호
	 * @return 최고 등수, 최소 등수
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