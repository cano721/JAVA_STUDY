package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * ���ĺ� �ҹ��ڿ� �빮�ڷ� �̷���� ���ڿ��� �ִ�.
 * �� ���ڿ��� ���ڰ� ���� �� �˻��� ������ �����.
 * ���ĺ� �ҹ���, �빮��, ���ڷ� �̷���� ���ڿ� s�� ã���� �ϴ� ���ڿ� k�� �־��� ��
 * k�� s�� �κ� ���ڿ����� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	static int[] table;

	/**
	 * kmp �˰����� ����Ͽ� Ǫ�� ����
	 * ���ڸ� �̸� �������ְ� Ǯ��� ������ ���� �� �ִ�.
	 * ó���� StringBuilder�� deletecharAt�� ����ؼ� ��� �ð��ʰ��� ������.
	 * deletecharAt�� ����� ���� ���ڿ��� ���ܿ��� �Ŷ� ��û ������ �� ������..
	 * ���ڿ� s�� �Է����� �����鼭 replaceAll("[0-9]", "")�� ����ϸ� ����ϰ� ���� �� �ִ�.
	 * pattern, matcher ����
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