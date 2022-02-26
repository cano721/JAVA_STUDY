package elwlahd555.programmers;

import java.util.LinkedList;

public class 프로그래머스72410_신규_아이디_추천 {
	public static void main(String[] args) {
		String new_id=	"...!@BaT#*..y.abcdefghijklm";
		System.out.println(solution(new_id));
	}

	public static String solution(String new_id) {
		String answer = "";

		char arr[] = new_id.toLowerCase().toCharArray();
		LinkedList<Character> que = new LinkedList<>();
		for (char c : arr) {
			que.add(c);
		}
		int size = que.size();
		for (int i = 0; i < size; i++) {
			char c = que.poll();
			if ((c >= 'a' && c <= 'z') || c == '-' || c == '_' || c == '.' || (c >= '0' && c <= '9')) {
				que.add(c);
			}
		}
		size = que.size();
		char before = ' ';
		for (int i = 0; i < size; i++) {
			char c = que.poll();
			if (before == '.' && c == '.')
				continue;
			else {
				que.add(c);
			}
			before = c;
		}
		char first = que.pollFirst();

		if (first != '.') {
			que.addFirst(first);
		}

		if (que.size() == 0) {
			que.add('a');
		}
		if (que.size() >= 16) {
			while (que.size() > 15) {
				que.pollLast();
			}
		}
		char last = que.pollLast();
		if (last != '.') {
			que.addLast(last);
		}
		if (que.size() <= 2) {
			char c = que.get(que.size() - 1);
			while (que.size() <= 2) {
				que.add(c);
			}
		}
		for (Character c : que) {

			answer = answer.concat(c.toString());
		}
		return answer;
	}
}
