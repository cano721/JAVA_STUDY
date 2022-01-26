package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N���� ���� �̷���� ������ �ִ�.
 * �� ������ �κм����� �� ���� M�� �Ǵ� ����� ���� ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	/**
	 * �� �����ͷ� ������ ���� m���� Ȯ���غ���.
	 * start <= end ������  while ���� �ȿ� �־��־��µ�, �̰Ͷ����� �ڲ� Ʋ�� ���� ������
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		int start = 0, end = 0, sum = 0, answer = 0;
		while (end < n) {
			if (sum + arr[end] > m)
				sum -= arr[start++];
			else {
				sum += arr[end++];
				if (sum == m)
					answer++;
			}
		}
		System.out.println(answer);
	}
}