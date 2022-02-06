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
	 * �ٴܰ� ���������� ���ڰ� ĩ���� �Ⱦ� ���� ������ 10%�� �ڽ��� ��õ���� ���������� �й��Ѵ�.
	 * ��� �Ǹſ��� ���� ������ �־��� ��, ���� �й� ��Ȳ�� ����ϴ� ����
	 * ��õ���� ���� ��� '-'�� �Է��� ������, i��° ��õ���� �̸��� enroll�� i��° ���� �̹� ���� ���� ������ ����ȴ�.
	 * ĩ���� 100���̴�.
	 * @param enroll ������
	 * @param referral ��õ��
	 * @param seller ĩ���� �� ������
	 * @param amount ĩ���� �Ǹ��� ����
	 * @return ��ü �������� �й� ��Ȳ
	 */
	public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
		int[] answer = new int[enroll.length];
		init(enroll, referral);
		for (int i = 0; i < seller.length; i++) {
			int cur = map.get(seller[i]);
			int price = amount[i] * toothPrice;
			while (tree[cur] != cur) {
				// �й��
				int distribute = price / divide;
				// �й���� ������ �ݾ� �߰�
				answer[cur] += price - distribute;
				// �й�ݰ� ��õ�� ����
				price = distribute;
				cur = tree[cur];
			}
			// ������ root�� ������Ʈ�� �����ֹǷ� ���⼭ ���ش�.
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
			// ������ �ڱ� �ڽ��� ����Ű����
			if (referral[i].equals("-")) {
				tree[i] = i;
			} else {
				tree[i] = map.get(referral[i]);
			}
		}
	}
}