package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * ���̾�Ʈ ���� �������� ������ �μ�����.
 * �׷��� �������� ģ���� ���ο� ������ ������ �־���.
 * �׷��� �������� �����԰� G ų�α׷� �� �ȴ�.
 * G ų�α׷� = �������� ���� ������^2 - �����̰� ����ϴ� ������^2 �̴�.
 * �������� ���� �����Է� ������ ���� ��� ����ϴ� ����
 * �������� �����Դ� �ڿ����� �����ϴ�.
 * ����, ������ ���԰� ���ٸ� -1�� ����Ѵ�.
 * @author Rave
 *
 */
public class Main {

	/**
	 * �������� �����Դ� �ڿ����̹Ƿ� left�� 1, right�� 2�� �ΰ� �� �����ͷ� Ž���Ѵ�.
	 * 
	 * ó���� �� �� ���������� ���� 1�� ������.
	 * ���� �Խ��ǿ��� (a+b)(a-b)�� �Ѵٴ� �� ���� Ǭ ���. ��� a*a - b*b�� �ص� ��� ������ ��.
	 * ���ڱ� �̷��� �ϸ� ���� �������ؼ� �ߴµ� Ǯ�� ����
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int g = Integer.parseInt(br.readLine());
		int left = 1, right = 2;
		while (left < right) {
			int result = (right + left) * (right - left);
			if (result >= g) {
				if (result == g)
					answer.append(right).append('\n');
				left++;
			} else
				right++;
		}
		System.out.println(answer.length() == 0 ? -1 : answer);
	}
}