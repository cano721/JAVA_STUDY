package elwlahd555.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class baekjoon5875_오타 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int len = str.length();
		int left = 0;
		int right = 0;
		int total = 0;
		int result = 0;

		for (int i = 0; i < len; i++) {
			char value = str.charAt(i);
			if (value == '(') {
				left++;
				total++;
			} else {
				right++;
				total--;
			}
			if (total == 1) {
				left = 0;
			}
			if (total == -1) {
				result = right;
				break;
			}
		}
		if (total == 2) {
			result = left;
		}
		System.out.println(result);
	}
}
