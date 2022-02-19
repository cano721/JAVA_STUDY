package Programmers;

import java.util.HashSet;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();

	}
}

class Solution {
	/**
	 * 전화번호가 주어진다.
	 * 어떤 전화번호가 다른 전화번호의 접두사가 된다면 false를 아니라면 true를 반환하는 문제
	 * 그냥 완전탐색
	 * @param phone_book 전화번호북
	 * @return 접두사인 경우가 있다면 false, 아니라면 true
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