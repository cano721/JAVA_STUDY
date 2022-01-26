package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * ���������� ������ ��� �絿�̰� �ִ�.
 * �ʹ� ���� ����� �� ���� ���� K��ŭ ������ ���� �絿�̸� ���������� �Ѵ�.
 * �� ��, ������ �� �ִ� �ִ����� ������ ������ ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	/**
	 * �̺�Ž���� ���� ��� + ����� �ڸ��� 2K+1  �����ȿ� �絿�̰� �ִٸ� �� ���� ������ ���� �ִ����� ���Ѵ�.
	 * �� ��, x�� ��ġ�� ������ �� �� ���ؾ� �Ѵ�.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		Pail[] pails = new Pail[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			pails[i] = new Pail(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(pails);
		int answer = 0, left = 0, right = 0, sum = 0;
		while(left <= right && right < n) {
			if(pails[right].x - pails[left].x <= 2*k + 1) {
				sum += pails[right++].amount;
				answer = Math.max(answer, sum);
			} else
				sum -= pails[left++].amount;
		}
		System.out.println(answer);
	}
}

class Pail implements Comparable<Pail> {
	int amount, x;

	public Pail(int amount, int x) {
		this.amount = amount;
		this.x = x;
	}

	@Override
	public int compareTo(Pail o) {
		return x - o.x;
	}

}