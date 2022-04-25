package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * �������� �л��鿡�� �ܾ ����ġ���� �Ѵ�.
 * �ð��� ���� �������� K���� �ܾ ����ġ���� �Ѵ�.
 * �̷��� �л����� K���� �ܾ� ���Ϸ� ������ �ܾ ���� �� �ִ�.
 * � �ܾ �����ľ� �л����� ���� ���� �ܾ ���� �� �ִ��� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	static int answer = 0, alphabetSize = 26;
	static int[] words;

	/**
	 * ��Ʈ����ŷ ����
	 * �з� �Ⱥ����� ��Ʈ����ŷ���� ������ �������� �˾��� ��...
	 * ��Ʈ����ŷ�ΰ� �ƴϱ� �ݹ� Ǯ��
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		words = new int[n];
		for (int i = 0; i < n; i++) {
			for (char ch : br.readLine().toCharArray()) {
				words[i] |= 1 << (ch - 'a');
			}
		}
		bruteforce(0, k, 0);
		System.out.println(answer);
	}

	public static void bruteforce(int cur, int depth, int selected) {
		if (depth == 0) {
			answer = Math.max(answer, calc(selected));
			return;
		}
		for (int i = cur; i < alphabetSize; i++)
			bruteforce(i + 1, depth - 1, selected | 1 << i);
	}

	public static int calc(int selected) {
		int count = 0;
		for (int word : words)
			if ((word & selected) == word)
				count++;
		return count;
	}
}
