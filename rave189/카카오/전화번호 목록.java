package Programmers;

import java.util.HashSet;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();

	}
}

class Solution {
	/**
	 * ��ȭ��ȣ�� �־�����.
	 * � ��ȭ��ȣ�� �ٸ� ��ȭ��ȣ�� ���λ簡 �ȴٸ� false�� �ƴ϶�� true�� ��ȯ�ϴ� ����
	 * �׳� ����Ž��
	 * @param phone_book ��ȭ��ȣ��
	 * @return ���λ��� ��찡 �ִٸ� false, �ƴ϶�� true
	 */
	public boolean solution(String[] phone_book) {
		HashSet<String> hash = new HashSet<>();
		for (String phone : phone_book)
			hash.add(phone);
		for (String phone : phone_book) {
			hash.remove(phone);
			for (int i = 0; i < phone.length(); i++) {
				if (hash.contains(phone.substring(0, i)))
					return false;
			}
			hash.add(phone);
		}
		return true;
	}
}