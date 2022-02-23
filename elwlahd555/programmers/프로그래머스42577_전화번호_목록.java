package elwlahd555.programmers;

import java.util.Arrays;

public class 프로그래머스42577_전화번호_목록 {
	public static void main(String[] args) {
		String phone_book[]={"12", "123", "1235", "567", "88"};
		System.out.println(solution(phone_book));
	}

	public static boolean solution(String[] phone_book) {
		boolean answer = true;
		Arrays.sort(phone_book);
		for (int i = 0; i < phone_book.length - 1; i++) {
			if(phone_book[i].length()>phone_book[i+1].length()) {
				continue;
			}
			if (phone_book[i].equals(phone_book[i + 1].substring(0, phone_book[i].length()))) {
				return false;
			}
		}
		return answer;
	}
}
