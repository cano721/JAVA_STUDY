package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		String add = "";
		for (int i = 0; i < line.length(); i++) {
			if (isPalindrome(line + add))
				break;
			add = line.charAt(i) + add;
		}
		System.out.println(line.length() + add.length());
	}

	public static boolean isPalindrome(String str) {
		for (int i = 0; i < str.length() / 2; i++) {
			if (str.charAt(i) != str.charAt(str.length() - 1 - i))
				return false;
		}
		return true;
	}
}