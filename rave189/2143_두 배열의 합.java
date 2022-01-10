package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * �� �迭 A�� B�� �־�����.
 * A�� �κ� �迭�� �հ� B�� �κ� �迭�� ���� ���� ���� T�� �Ǵ� ���� ������ ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	static ArrayList<Integer> b = new ArrayList<>();

	/**
	 * a�迭�� �������� ���Ѵ�.
	 * b�� ��� �κ� �迭�� ���Ѵ�.
	 * b�� �κ� �迭�� hash�� ���� ��
	 * T - a�� �κ� �迭 = b�� �κ� �迭�� ���� hash���� ������ ������ �����ش�.
	 * 
	 * �̺� Ž�����δ� �ð� �ʰ��� ��
	 * ���� ���� �κ� ������ �� ������ ���� �ؾߵ���
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		int[] a = new int[n + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++)
			a[i] = a[i - 1] + Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0, prevSize = 0, count = 0; i < m; i++) {
			int v = Integer.parseInt(st.nextToken());
			int size = b.size();
			for (int j = prevSize; j < size; j++)
				b.add(b.get(j) + v);
			b.add(v);
			prevSize += count++;
		}
		Map<Integer, Integer> hash = new HashMap<>();
		b.forEach(v -> hash.put(v, hash.getOrDefault(v, 0) + 1));
		long answer = 0;
		for (int i = 1; i <= n; i++)
			for (int j = 0; j < i; j++)
				answer += hash.getOrDefault(t - (a[i] - a[j]), 0);

		System.out.println(answer);
	}
}