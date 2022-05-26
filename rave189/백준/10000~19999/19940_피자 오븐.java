package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * ���ڸ� ���쿡 ������� �Ѵ�.
 * ���쿡 ���� ���ڸ� ��Ȯ�� N�� ���� ���۽�Ű���� �Ѵ�.
 * ������ -1, 1, -10, 10, 60�� ��ư�� �ִ�.
 * N�� �־��� ��, N�� ����� ���� ��ư�� �ּ� Ƚ���� ����� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	static int[] buttons = { -1, 1, -10, 10, 60 };

	/**
	 * �ð��� 0.25�ʷ� �ſ� ª����
	 * �ٵ� �ʺ�켱 + �׸��𿩼� while���ȿ��� ������ ������� ��� �����߾���. (�ð��ʰ�, �޸� �ʰ�)
	 * �׷��� ������ 60�� �Ѿ�� 60���� �����°� ������ ���� ����̱� ������
	 * 60���� ���� ������ �κи� ä���ָ� ����� ���� �� ������.
	 * min�迭�� 60������ �߾��ٰ� Ʋ��.
	 * 60�� ������ ���� ����� ª�� ���� �־ 61�� �ٲ�
	 * �� 2~3�ð����� ����....
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		int[][] min = new int[61][buttons.length];
		Queue<Integer> q = new LinkedList<>();
		q.add(0);
		boolean[] visited = new boolean[min.length];
		visited[0] = true;
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 0; i < buttons.length; i++) {
				int next = cur + buttons[i];
				try {
					if (visited[next])
						continue;
					visited[next] = true;
					q.add(next);
					System.arraycopy(min[cur], 0, min[next], 0, buttons.length);
					min[next][buttons.length - 1 - i]++;
				} catch (ArrayIndexOutOfBoundsException e) {
				}
			}
		}
		while (testCase-- > 0) {
			int n = Integer.parseInt(br.readLine());
			int pass = n / 60;
			int remain = n % 60;
			answer.append(String.format("%d %d %d %d %d\n", min[remain][0] + pass, min[remain][1], min[remain][2],
					min[remain][3], min[remain][4]));
		}
		System.out.println(answer);
	}
}