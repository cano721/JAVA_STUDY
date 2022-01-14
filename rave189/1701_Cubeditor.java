package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * ���ڿ� s�� �־�����.
 * �� s���� �� �� �̻� ������ �κ� ���ڿ� �� ���� �� ���ڿ��� ���̸� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	static int max = 0;

	/**
	 * kmp �˰����� Ȱ���Ѵ�.
	 * kmp �˰��򿡼� ���λ�� ���̻簡 ���� ���̸� ���ϱ� ������
	 * �� �� �̻� ������ �κ� ���ڿ��� ���� �� �ִ�.
	 * ���� ���� table�� �� �� �ִ밪�� �� ���� �� ���ڿ��� ���̰� �ȴ�.
	 * �̶�, kmp���� table�� ���� ������ s�� ���λ�� ���̻簡 �������� ���ϱ� ������
	 * �߰����� �����ϴ� �κ� ���ڿ��� ���� �� ��츦 Ž������ ���Ѵ�.
	 * ���� substring�� ����Ͽ� ��� �κ� ���ڿ��� ���� table�� ���ϰ� �� ���� �߿��� �ִ��� ã�´�.
	 * 
	 * kmp�ƴϰ� ���ڿ������ε� Ǯ �� �ִ� �� ������.
	 * ���� �� ���� �غ��ߴ��?
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		for (int i = 0; i < s.length(); i++)
			findMax(s.substring(i));
		System.out.println(max);
	}

	public static void findMax(String s) {
		int[] table = new int[s.length()];
		for (int i = 1, j = 0; i < s.length(); i++) {
			while (j > 0 && s.charAt(i) != s.charAt(j))
				j = table[j - 1];
			if (s.charAt(i) == s.charAt(j)) {
				table[i] = ++j;
				max = Math.max(max, table[i]);
			}
		}
	}
}