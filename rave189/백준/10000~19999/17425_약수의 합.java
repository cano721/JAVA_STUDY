package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 자연수 A의 약수의 합을 f(A)라고 한다.
 * 자연수 A보다 작거나 같은 모든 f(A)를 더한 값을 g(A)라고 한다.
 * 자연수 X가 주어진다.
 * g(X)를 구하는 문제
 * @author Rave
 *
 */
public class Main {

	static final int SIZE = 1000000;
	static long[] sumOfDivisor = new long[SIZE + 1];

	/**
	 * 소수 판정해서 약수의 합 구하는 법 등등 이것저것 해봤는데 다 실패...
	 * 에라토스테네스의 체로 하는게 가장 빠른 방법이었음.
	 * 괜히 if 돌고 while돌고 하는 코드가 시간만 잡아먹는 꼴
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