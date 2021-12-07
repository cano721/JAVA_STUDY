package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] alphabet;
		int count = 0;
		loop: while (n-- > 0) {
			alphabet = new int[26];
			String line = br.readLine();
			alphabet[line.charAt(0) - 'a']++;
			for (int i = 1; i < line.length(); i++) {
				if (line.charAt(i - 1) != line.charAt(i) && alphabet[line.charAt(i) - 'a'] != 0)
					continue loop;
				alphabet[line.charAt(i) - 'a']++;
			}
			count++;
		}
		System.out.println(count);
	}
}