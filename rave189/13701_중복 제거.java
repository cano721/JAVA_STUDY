package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.StringTokenizer;

/**
 * 33554431������ ���ڷ� �̷���� �� N���� �־�����.
 * �� �� �ߺ��� ���� �����ϰ� ���� ���� ��� �ϴ� ����
 * @author Rave
 *
 */
public class Main {

	/**
	 * BitSet�� ����Ѵ�.
	 * index�� bit�� ����ϱ� ������ �ſ� ���� �޸𸮸� ����Ͽ� �����͸� ������ �� �ִ�.
	 * ���ڰ� ������ ���ڿ� �ش��ϴ� index�� true�� �ٲ��ش�.
	 * �̸� hash�� ����ϵ��� ����Ͽ� ������ Ǯ �� �ִ�.
	 * 
	 * hash�ε� Ǯ���� ��(�ð��� �޸𸮸� �ξ� �� ��ƸԴ´�.)
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