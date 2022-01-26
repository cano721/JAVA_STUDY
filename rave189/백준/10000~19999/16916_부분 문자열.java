package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문자열 S와 P가 주어진다.
 * P가 S의 부분 문자열인지 구하는 문제
 * 맞으면 1 아니면 0을 출력한다. 
 * @author Rave
 *
 */
public class Main {

	/**
	 * kmp알고리즘을 사용하여 빠른 시간안에 구한다.
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String p = br.readLine();
		int[] table = makeTable(s);
		for (int i = 0, j = 0; i < s.length(); i++) {
			// 이전의 같았던 문자로 돌아간다.
			while (j > 0 && s.charAt(i) != p.charAt(j))
				j = table[j - 1];
			if (s.charAt(i) == p.charAt(j))
				// 같으면서 j와 p의 길이가 같다면 부분 문자열이다.
				// 아니면 j값 1증가
				if (j == p.length() - 1) {
					System.out.println(1);
					return;
				} else
					j++;
		}
		System.out.println(0);
	}

	/**
	 * 접두사와 접미사가 같은 최대 길이를 저장한다.
	 * @param s
	 * @return
	 */
	public static int[] makeTable(String s) {
		int[] table = new int[s.length()];
		for (int i = 1, j = 0; i < s.length(); i++) {
			// 이전에 같았던 문자로 돌아간다.
			while (j > 0 && s.charAt(i) != s.charAt(j))
				j = table[j - 1];
			// 같으면 ++j를 해서 최대 길이를 저장한다.
			if (s.charAt(i) == s.charAt(j))
				table[i] = ++j;
		}
		return table;
	}
}