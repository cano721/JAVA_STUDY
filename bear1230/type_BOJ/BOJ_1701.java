import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		int ans = 0;
		for (int i = 0; i < s.length(); i++) {
			int n = 0;
			String str = s.substring(i, s.length());

			int[] pi = new int[str.length()];

			for (int j = 1; j < str.length(); j++) {
				while (n > 0 && str.charAt(n) != str.charAt(j)) {
					n = pi[n - 1];
				}

				if (str.charAt(n) == str.charAt(j)) {
					n += 1;
				}
				pi[j] = n;
				ans = Math.max(ans, pi[j]);
			}
		}
		System.out.println(ans);
	}
}
