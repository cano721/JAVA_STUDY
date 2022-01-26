package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * ����� ���̰� N���� ���� �־�����.
 * �� ���� �� ���� ū ���������� ã�� ����
 * ���������� ���̰� ���̰� �����ϴ� �� ���� �̷������ �Ѵ�.
 * 
 * @author Rave
 *
 */
public class Main {

	/**
	 * ó�� ���� �������� Ž���� �ϸ� ���̰� ���� ū �� ���� ã�´�.
	 * ���� ���� ���ų� �������ٸ� ���������� �ƴϹǷ� prev�� max�� next�� ������Ʈ ���ش�.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int prev = Integer.parseInt(st.nextToken());
		int max = prev;
		int answer = 0;
		while (--n > 0) {
			int next = Integer.parseInt(st.nextToken());
			if (max < next)
				answer = Math.max(answer, next - prev);
			else
				prev = next;
			max = next;
		}
		System.out.println(answer);
	}
}