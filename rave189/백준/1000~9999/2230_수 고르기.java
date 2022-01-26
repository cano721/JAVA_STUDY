package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N���� ���� �̷���� ������ �ִ�.
 * �� �������� �� ���� �������� ��(���� ���� ���� �ִ�),
 * �� ���̰� M�̻��̸鼭 ���� ���� ��츦 ���Ϸ��� �Ѵ�.
 * 
 * @author Rave
 *
 */
public class Main {

	/**
	 * ���� ���� �����ϹǷ� �� ���� �����͸� 0���� �ΰ� �����Ѵ�.
	 * ���� �� �������� ���� ���̰� M �̻��� ��� ���� ���� ������� �˻��Ѵ�.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		long m = Long.parseLong(st.nextToken());
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(br.readLine());
		Arrays.sort(arr);
		int start = 0, end = 0;
		long answer = Long.MAX_VALUE;
		while (start <= end && end < n) {
			long sum = arr[end] - arr[start];
			if (sum >= m) {
				answer = Math.min(answer, sum);
				start++;
			} else
				end++;
		}
		System.out.println(answer);
	}
}