package elwlahd555.programmers;

import java.util.HashMap;
import java.util.Map;

public class 프로그래머스77486_다단계_칫솔_판매 {
	public static void main(String[] args) {
		String[] enroll = { "john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young" };
		String[] referral = { "-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward" };
		String[] seller = { "young", "john", "tod", "emily", "mary" };
		int[] amount = { 12, 4, 2, 5, 10 };
		System.out.println(solution(enroll, referral, seller, amount).toString());
	}

	public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		int[] answer = new int[enroll.length];

		Map<String, String> parentMap = new HashMap<>(); // <자신, 부모>
		Map<String, Integer> memberIndexMap = new HashMap<>(); // <자신, 자신의 순서>

		for (int i = 0; i < enroll.length; i++) {
			parentMap.put(enroll[i], referral[i]);
			memberIndexMap.put(enroll[i], i);
		}

		for (int i = 0; i < seller.length; i++) {

			String now = seller[i];
			int profit = 100 * amount[i];

			while (!now.equals("-")) {
				int profitForParent = profit / 10; // 부모에게 넘겨줄 금액
				int nowProfit = profit - profitForParent; // 자신이 가져갈 금액

				// 자신의 index에 금액만큼 더함
				answer[memberIndexMap.get(now)] += nowProfit;

				// 노드는 부모로 이동하면서 수익을 부모에게 넘겨준 금액으로 초기화
				now = parentMap.get(now);
				profit /= 10;

				// 10%의 금액이 1원 미만인 경우
				if (profit < 1) {
					break;
				}
			}
		}
        return answer;
	}
}
