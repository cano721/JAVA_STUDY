package 전체유형문제풀이.프로그래머스;

public class PG72410_신규아이디추천 {

	public static void main(String[] args) {

		String new_id = "...!@BaT#*..y.abcdefghijklm";
		String new_id2 = "z-+.^.";
		String new_id3 = "=.=";

		String res = solution(new_id);

		System.out.print(res);
	}

	private static String solution(String new_id) {
		String answer = "";

		//1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
		answer = replace(new_id);
		
		//2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
		answer = remove(answer);
		
		//3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
		answer = close(answer);
		
		//4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
		answer = frontend(answer);
		
		//5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
		answer = emptied(answer);
		
		//6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
	    //만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
		answer = lengthLong(answer);
		
		//7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
		answer = addStr(answer);

		return answer;
	}

	// 2자 이하면 마지막 문자 추가하기
	private static String addStr(String answer) {

		if (answer.length() <= 2) {
			for (int i = answer.length(); i < 3; i++) {
				answer = answer += answer.charAt(answer.length() - 1);
			}
		}

		return answer;
	}

	// 16자 이상이면 삭제
	private static String lengthLong(String answer) {

		if (answer.length() >= 16) {
			answer = answer.substring(0, 15);
		}

		StringBuffer sb = new StringBuffer(answer);

		if (answer.endsWith(".")) {
			sb.deleteCharAt(answer.length() - 1);
		}

		return sb.toString();
	}

	// 비어 있는 문자열이라면 a 추가
	private static String emptied(String answer) {

		if (answer.length() == 0) {
			answer = "a";
		}

		return answer;
	}

	// 처음 혹은 끝에 '.'가 있으면 제거
	private static String frontend(String answer) {

		StringBuffer sb = new StringBuffer(answer);

		if (answer.length() > 0) {
			if (answer.charAt(0) == '.') {
				sb.deleteCharAt(0);

			}else if (answer.charAt(answer.length() - 1) == '.') {
				sb.deleteCharAt(answer.length() - 1);
			}
		}
		
		return sb.toString();
	}

	// 연속된 마침표 하나로 치환
	private static String close(String answer) {

		String temp = answer.toString().replace("..", ".");
		while (temp.contains("..")) {
			temp = temp.replace("..", ".");
		}

		return temp;
	}

	// 지정된 문자외 제거
	private static String remove(String answer) {
		String temp = "";

		for (int i = 0; i < answer.length(); i++) {
			char t = answer.charAt(i);

			if (t >= 'a' && t <= 'z') {
				temp += t;

			} else if (t >= '0' && t <= '9') {
				temp += t;

			} else if (t == '-' || t == '_' || t == '.') {
				temp += t;
			}

		}

		return temp;
	}

	// 대문자를 소문자로 전환
	private static String replace(String new_id) {

		new_id = new_id.toLowerCase();

		return new_id;
	}

}
