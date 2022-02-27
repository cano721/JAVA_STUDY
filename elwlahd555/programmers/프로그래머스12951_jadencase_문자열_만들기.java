package elwlahd555.programmers;

public class 프로그래머스12951_jadencase_문자열_만들기 {
	public static void main(String[] args) {
		String s = "3people unFollowed me";
		System.out.println(solution(s));
	}

	public static String solution(String s) {
		String answer = "";
		String arr[] = s.toLowerCase().split("");
		boolean flag = true;
		for (String st : arr) {
			answer += flag ? st.toUpperCase() : st;
			flag = st.equals(" ") ? true : false;
		}
		return answer;
	}
}
