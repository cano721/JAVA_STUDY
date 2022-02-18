package programmers;

public class d220218_괄호회전하기 {

	public static void main(String[] args) {
		String s = "}]()[{";
		int answer = solution(s);
		System.out.println(answer);
	}

	private static int solution(String s) {
		int answer = 0;
		for (int i = 0; i < s.length(); i++) {
			if(check괄호(s)) answer ++;
			char a = s.charAt(0);
			s = s.substring(1) + a;
		}
		
		return answer;
	}

	private static boolean check괄호(String s) {
		boolean isCheck = false;
		if(s.length() % 2 == 1) return isCheck;
		String temp = "";
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
				temp += s.charAt(i);
			}
			else {
				if(s.charAt(i) == ')') {
					if(temp.length() != 0 && temp.charAt(temp.length()-1) == '(') {
						temp = temp.substring(0, temp.length() - 1);
					}else {
						return isCheck;
					}
				}else if(s.charAt(i) == '}') {
					if(temp.length() != 0 && temp.charAt(temp.length()-1) == '{') {
						temp = temp.substring(0, temp.length() - 1);
					}else {
						return isCheck;
					}
				}else if(s.charAt(i) == ']') {
					if(temp.length() != 0 && temp.charAt(temp.length()-1) == '[') {
						temp = temp.substring(0, temp.length() - 1);
					}else {
						return isCheck;
					}
				}
			}
		}
		return true;
	}

}
