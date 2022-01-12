package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * ���ڿ� S�� P�� �־�����.
 * P�� S�� �κ� ���ڿ����� ���ϴ� ����
 * ������ 1 �ƴϸ� 0�� ����Ѵ�. 
 * @author Rave
 *
 */
public class Main {

	/**
	 * kmp�˰����� ����Ͽ� ���� �ð��ȿ� ���Ѵ�.
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String p = br.readLine();
		int[] table = makeTable(s);
		for (int i = 0, j = 0; i < s.length(); i++) {
			// ������ ���Ҵ� ���ڷ� ���ư���.
			while (j > 0 && s.charAt(i) != p.charAt(j))
				j = table[j - 1];
			if (s.charAt(i) == p.charAt(j))
				// �����鼭 j�� p�� ���̰� ���ٸ� �κ� ���ڿ��̴�.
				// �ƴϸ� j�� 1����
				if (j == p.length() - 1) {
					System.out.println(1);
					return;
				} else
					j++;
		}
		System.out.println(0);
	}

	/**
	 * ���λ�� ���̻簡 ���� �ִ� ���̸� �����Ѵ�.
	 * @param s
	 * @return
	 */
	public static int[] makeTable(String s) {
		int[] table = new int[s.length()];
		for (int i = 1, j = 0; i < s.length(); i++) {
			// ������ ���Ҵ� ���ڷ� ���ư���.
			while (j > 0 && s.charAt(i) != s.charAt(j))
				j = table[j - 1];
			// ������ ++j�� �ؼ� �ִ� ���̸� �����Ѵ�.
			if (s.charAt(i) == s.charAt(j))
				table[i] = ++j;
		}
		return table;
	}
}