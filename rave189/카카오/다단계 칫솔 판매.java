package Programmers;

import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	int[] tree;
	HashMap<String, Integer> map = new HashMap<>();
	int toothPrice = 100, divide = 10;

	/**
	 * 다단계 조직에서는 각자가 칫솔을 팔아 얻은 수익의 10%를 자신을 추천해준 조직원에게 분배한다.
	 * 모든 판매원의 영업 실적이 주어질 때, 최종 분배 현황을 출력하는 문제
	 * 추천인이 없는 경우 '-'로 입력이 들어오고, i번째 추천인의 이름은 enroll의 i번째 전에 이미 나온 적이 있음이 보장된다.
	 * 칫솔은 100원이다.
	 * @param enroll 조직원
	 * @param referral 추천인
	 * @param seller 칫솔을 판 조직원
	 * @param amount 칫솔을 판매한 개수
	 * @return 전체 조직원의 분배 현황
	 */
	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		int[] answer = new int[enroll.length];
		init(enroll, referral);
		for (int i = 0; i < seller.length; i++) {
			int cur = map.get(seller[i]);
			int price = amount[i] * toothPrice;
			while (tree[cur] != cur) {
				// 분배금
				int distribute = price / divide;
				// 분배금을 제외한 금액 추가
				answer[cur] += price - distribute;
				// 분배금과 추천인 갱신
				price = distribute;
				cur = tree[cur];
			}
			// 마지막 root는 업데이트를 못해주므로 여기서 해준다.
			answer[cur] += price - (price / divide);
		}
		return answer;
	}

	public void init(String[] enroll, String[] referral) {
		tree = new int[enroll.length];
		for (int count = 0; count < enroll.length; count++) {
			map.put(enroll[count], count);
		}
		for (int i = 0; i < referral.length; i++) {
			// 없으면 자기 자신을 가리키도록
			if (referral[i].equals("-")) {
				tree[i] = i;
			} else {
				tree[i] = map.get(referral[i]);
			}
		}
	}
}