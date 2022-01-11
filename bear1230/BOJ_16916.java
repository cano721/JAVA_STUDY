import java.util.*;
import java.io.*;

public class Main {
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String p = br.readLine();
		KMP(s, p);
		System.out.println(ans);
	}

	static int[] getTable(String p) {
		int[] table = new int[p.length()];
		int j = 0;
		for (int i = 1; i < p.length(); i++) {
			while (j > 0 && p.charAt(i) != p.charAt(j)) {
				j = table[j - 1];
			}
			if (p.charAt(i) == p.charAt(j))
				table[i] = ++j;
		}
		return table;
	}

	static void KMP(String s, String p) {
		int[] table = getTable(p);
		int slen = s.length();
		int plen = p.length();
		int j = 0;
		for (int i = 0; i < slen; i++) {
			while (j > 0 && s.charAt(i) != p.charAt(j)) {
				j = table[j - 1];
			}
			if (s.charAt(i) == p.charAt(j)) {
				if (j == plen - 1) {
					ans = 1;
					break;
				} else
					j++;
			}
		}
	}
}