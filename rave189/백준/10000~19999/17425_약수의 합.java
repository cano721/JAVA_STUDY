package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * �ڿ��� A�� ����� ���� f(A)��� �Ѵ�.
 * �ڿ��� A���� �۰ų� ���� ��� f(A)�� ���� ���� g(A)��� �Ѵ�.
 * �ڿ��� X�� �־�����.
 * g(X)�� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	static final int SIZE = 1000000;
	static long[] sumOfDivisor = new long[SIZE + 1];

	/**
	 * �Ҽ� �����ؼ� ����� �� ���ϴ� �� ��� �̰����� �غôµ� �� ����...
	 * �����佺�׳׽��� ü�� �ϴ°� ���� ���� ����̾���.
	 * ���� if ���� while���� �ϴ� �ڵ尡 �ð��� ��ƸԴ� ��
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		for (int i = 1; i <= SIZE; i++)
			for (int j = i; j <= SIZE; j += i)
				sumOfDivisor[j] += i;
		for (int i = 1; i <= SIZE; i++)
			sumOfDivisor[i] += sumOfDivisor[i - 1];
		int testCase = Integer.parseInt(br.readLine());
		while (testCase-- > 0)
			answer.append(sumOfDivisor[Integer.parseInt(br.readLine())]).append('\n');
		System.out.print(answer);
	}
}