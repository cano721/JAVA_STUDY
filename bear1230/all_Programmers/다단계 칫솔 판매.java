import java.util.*;

class Solution {

	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		int[] answer = new int[enroll.length];

		HashMap<String, String> parent = new HashMap<String, String>();
		HashMap<String, Integer> name = new HashMap<String, Integer>();

		for (int i = 0; i < enroll.length; ++i) {
			parent.put(enroll[i], referral[i]);
			name.put(enroll[i], 0);
		}

		for (int i = 0; i < seller.length; ++i) {
			String curname = seller[i];
			int money = amount[i] * 100;

			while (!curname.equals("-")) {
				int remain = (int) (money * 0.1);
				name.put(curname, name.get(curname) + money - remain);
				curname = parent.get(curname);
				money = remain;

				if (money == 0)
					break;
			}
		}

		for (int i = 0; i < enroll.length; ++i) {
			answer[i] = name.get(enroll[i]);
		}

		return answer;
	}
}
