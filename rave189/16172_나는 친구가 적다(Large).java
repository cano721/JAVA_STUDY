package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 알파벳 소문자와 대문자로 이루어진 문자열이 있다.
 * 이 문자열에 숫자가 섞여 들어가 검색에 문제가 생겼다.
 * 알파벳 소문자, 대문자, 숫자로 이루어진 문자열 s와 찾고자 하는 문자열 k가 주어질 때
 * k가 s의 부분 문자열인지 구하는 문제
 * @author Rave
 *
 */
public class Main {

	static int[] table;

	/**
	 * kmp 알고리즘을 사용하여 푸는 문제
	 * 숫자를 미리 제거해주고 풀어야 정답을 맞출 수 있다.
	 * 처음에 StringBuilder의 deletecharAt을 사용해서 계속 시간초과가 났었음.
	 * deletecharAt은 지우고 뒤의 문자열을 땡겨오는 거라 엄청 느린데 왜 썻는지..
	 * 문자열 s를 입력으로 받으면서 replaceAll("[0-9]", "")를 사용하면 깔끔하게 지울 수 있다.
	 * pattern, matcher 사용법
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String saltS = br.readLine();
		StringBuilder s = new StringBuilder();
		String k = br.readLine();
		for (char ch : saltS.toCharArray())
			if (!isNumber(ch))
				s.append(ch);
		makeTable(s.toString());
		System.out.println(kmp(s.toString(), k, table) ? 1 : 0);
	}

	public static boolean isNumber(char ch) {
		return '0' <= ch && ch <= '9';
	}

	public static void makeTable(String s) {
		table = new int[s.length()];
		for (int i = 1, j = 0; i < s.length(); i++) {
			while (j > 0 && s.charAt(i) != s.charAt(j))
				j = table[j - 1];
			if (s.charAt(i) == s.charAt(j))
				table[i] = ++j;
		}
	}

	public static boolean kmp(String s, String k, int[] table) {
		for (int i = 0, j = 0; i < s.length(); i++) {
			while (j > 0 && s.charAt(i) != k.charAt(j))
				j = table[j - 1];
			if (s.charAt(i) == k.charAt(j))
				if (j++ == k.length() - 1)
					return true;
		}
		return false;
	}
}