package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 3���� N���� �ٷ� �̷���� ���� �ִ�.
 * �� ĭ���� 0~9������ ���ڰ� �����ִ�.
 * �������� ���̸� �Ϸ��µ� ���� �ٷ� ������ ������ ������ ���� ���������� �ִ�.
 * 1. �ٷ� �Ʒ��� ���� ��������.
 * 2. �ٷ� �Ʒ��� ���� ������ ���� ��������.
 * ���� �� ������ �ٷ� �Ʒ� ���� �Ʒ� ���� ������ ������ ���� ������ �� �ְ�,
 * �� �������� �ٷ� �Ʒ� ���� �Ʒ� ���� ������ ���� ���� ������ �� �ְ�, 
 * ����� ������̵� ������ �� �ִ�.
 * �� ��, ���� �� �ִ� �ִ� ������ �ּ� ������ ���ϴ� ����
 * @author Rave
 *
 */
public class Main {

	/**
	 * �ִ븸 �����ϴ� dp�� �ּҸ� �����ϴ� dp�� ����� �������� ���� �ִ� �ּ��̵��� dp�� �����Ѵ�.
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n + 1][3];
		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < arr[0].length; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		int[][] maxDp = new int[n + 1][3];
		int[][] minDp = new int[n + 1][3];
		for (int i = 1; i <= n; i++) {
			maxDp[i][0] = Math.max(maxDp[i - 1][0], maxDp[i - 1][1]) + arr[i][0];
			maxDp[i][1] = Math.max(maxDp[i - 1][0], Math.max(maxDp[i - 1][1], maxDp[i - 1][2])) + arr[i][1];
			maxDp[i][2] = Math.max(maxDp[i - 1][1], maxDp[i - 1][2]) + arr[i][2];
			minDp[i][0] = Math.min(minDp[i - 1][0], minDp[i - 1][1]) + arr[i][0];
			minDp[i][1] = Math.min(minDp[i - 1][0], Math.min(minDp[i - 1][1], minDp[i - 1][2])) + arr[i][1];
			minDp[i][2] = Math.min(minDp[i - 1][1], minDp[i - 1][2]) + arr[i][2];
		}
		int max = Math.max(maxDp[n][0], Math.max(maxDp[n][1], maxDp[n][2]));
		int min = Math.min(minDp[n][0], Math.min(minDp[n][1], minDp[n][2]));
		System.out.println(String.format("%d %d", max, min));
	}
}