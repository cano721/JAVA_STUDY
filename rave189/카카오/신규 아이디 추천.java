package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();

	}
}

class Solution {
	/**
	 * 신규 이용자의 아이디가 주어지면 다음과 같은 과정을 통해 신규 아이디를 추천해주려고 한다.
	 * 과정을 끝낸 문자열을 출력하는 문제
	 * 1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
	 * 2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
	 * 3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
	 * 4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
	 * 5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
	 * 6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
	 * 	만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
	 * 7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
	 * 
	 * 정규표현식으로 거의 모든 과정을 표현할 수 있다.
	 * @param new_id 입력 문자열
	 * @return 모든 과정을 수행한 문자열
	 */
	public String solution(String new_id) {
		new_id = new_id.toLowerCase();
		new_id = removeInValidChar(new_id);
		new_id = new_id.replaceAll("\\.{2,}", "\\.");
		new_id = removeFirstCloseChar(new_id);
		new_id = removeLastCloseChar(new_id);
		if (new_id.equals(""))
			new_id = "a";
		if (new_id.length() >= 16)
			new_id = new_id.substring(0, 15);
		new_id = removeLastCloseChar(new_id);
		if (new_id.length() <= 2) {
			char lastCh = new_id.charAt(new_id.length() - 1);
			while (new_id.length() < 3)
				new_id += lastCh;
		}
		return new_id;
	}

	public String removeInValidChar(String id) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < id.length(); i++) {
			char ch = id.charAt(i);
			if (isValid(ch))
				result.append(ch);
		}
		return result.toString();
	}

	public boolean isValid(char ch) {
		return ch == '-' || ch == '_' || ch == '.' || ('a' <= ch && ch <= 'z') || ('0' <= ch && ch <= '9');
	}

	public String removeFirstCloseChar(String id) {
		if (id.length() > 0 && id.charAt(0) == '.')
			return id.substring(1);
		return id;
	}

	public String removeLastCloseChar(String id) {
		if (id.length() > 0 && id.charAt(id.length() - 1) == '.')
			return id.substring(0, id.length() - 1);
		return id;
	}
}