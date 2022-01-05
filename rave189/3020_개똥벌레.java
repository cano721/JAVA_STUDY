package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * ������ ���������� �̷���� ������ �ִ�.
 * �� ������ ���˹����� ����Ϸ��� �Ѵ�.
 * ���˹����� �ڽ��� ������ ������ ���� �� ���������� ����, ��ֹ��� ���̸� �ı��Ѵ�.
 * ���˹����� ������ ��������� �ı��� ��ֹ��� �ּڰ��� �׷��� ������ �� ���� �ִ��� ���ϴ� ����
 * ù ��° ��ֹ��� �׻� �����̰�, ������ �������� ������ ������ ���´�.
 * @author Rave
 *
 */
public class Main {

	/**
	 * ������ �������� ���̸� �迭�� �����Ѵ�.
	 * ���� ���� ���� ���� �� ������ ���� ������ �������� ������ ���Ѵ�.
	 * ������ ���̸�ŭ �ݺ����� ���鼭 ����[H] - ����[i-1]���� i��° �������� ���� ������ ������ ���ϰ�,
	 * ������[H] - ������[H-i]�� i��° ���������� ū �������� ������ ���Ѵ�.
	 * �ּڰ������� ���Ѵ�.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int[] stalagmite = new int[h + 1];
		int[] stalactite = new int[h + 1];
		for (int i = 0; i < n; i += 2) {
			stalagmite[Integer.parseInt(br.readLine())]++;
			stalactite[Integer.parseInt(br.readLine())]++;
		}
		for (int i = 1; i <= h; i++) {
			stalagmite[i] += stalagmite[i - 1];
			stalactite[i] += stalactite[i - 1];
		}
		int min = Integer.MAX_VALUE, count = 0;
		for (int i = 1; i <= h; i++) {
			int sum = stalagmite[h] - stalagmite[i - 1] + stalactite[h] - stalactite[h - i];
			if (sum < min) {
				min = sum;
				count = 1;
			} else if (sum == min)
				count++;
		}
		System.out.println(String.format("%d %d", min, count));
	}
}