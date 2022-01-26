package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * �� ��尡 �ڽ��� �ִ� K�� ���� �� �ִ� Ʈ���� K�� Ʈ����� �Ѵ�.
 * K�� Ʈ���� �� ��尡 �־����� ��, �� ����� �Ÿ��� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	/**
	 * ����� ������ 10^15�̱� ������ Ʈ���� ������� �ϸ� �ȵȴ�.
	 * �� ����� �θ� ���ϴ� ����� (n-1)/k�� �ø��ϸ� ���� �� �ִ�.
	 * �ణ �����ϰ�(?) ������ �θ� ��带 ��� ������ �� �ű⼭ ã�� ������� ǰ
	 * 
	 * �� ��带 �ϳ��� �ø��鼭 ã���� �� ���� �ð��� ���� �� �ִ�.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		long n = Long.parseLong(st.nextToken());
		double k = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		while (q-- > 0) {
			st = new StringTokenizer(br.readLine());
			long first = Long.parseLong(st.nextToken());
			long second = Long.parseLong(st.nextToken());
			if (k == 1) {
				answer.append(Math.abs(second - first)).append('\n');
				continue;
			}
			Map<Long, Long> hash = new HashMap<>();
			for (long tmp = first, idx = 1; tmp >= 1; tmp = (long) Math.ceil((tmp - 1) / k), idx++)
				hash.put(tmp, idx);
			for (long tmp = second, idx = 1; tmp >= 1; tmp = (long) Math.ceil((tmp - 1) / k), idx++) {
				if (hash.containsKey(tmp)) {
					answer.append(hash.get(tmp) + idx - 2).append('\n');
					break;
				}
			}
		}
		System.out.print(answer);
	}
}