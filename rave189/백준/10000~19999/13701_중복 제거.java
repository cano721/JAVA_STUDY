package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.StringTokenizer;

/**
 * 33554431까지의 숫자로 이루어진 수 N개가 주어진다.
 * 이 중 중복된 수를 제거하고 남은 수를 출력 하는 문제
 * @author Rave
 *
 */
public class Main {

	/**
	 * BitSet을 사용한다.
	 * index로 bit를 사용하기 때문에 매우 적은 메모리를 사용하여 데이터를 저장할 수 있다.
	 * 숫자가 들어오면 숫자에 해당하는 index를 true로 바꿔준다.
	 * 이를 hash를 사용하듯이 사용하여 문제를 풀 수 있다.
	 * 
	 * hash로도 풀리긴 함(시간과 메모리를 훨씬 더 잡아먹는다.)
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		BitSet bitSet = new BitSet(33554432);
		while (st.hasMoreTokens()) {
			int v = Integer.parseInt(st.nextToken());
			if (!bitSet.get(v)) {
				bitSet.set(v);
				answer.append(v).append(' ');
			}
		}
		System.out.println(answer);
	}
}