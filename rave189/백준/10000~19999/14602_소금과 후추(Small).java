package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * �迭 A�� �־�����, W�� �־�����.
 * ���η� i+w, ���η� j+w ������ ���� �Ϸķ� �����Ͽ� �� �� �߾Ӱ��� �����Ϸ��� �Ѵ�.
 * �߾Ӱ����� ������ ���ο� �迭 B�� ���ϴ� ����
 * ��� ���� 0�̻� k������ ������ �־�����.
 * @author Rave
 *
 */
public class Main {

	static int k, w, mid;
	static int[][] map;

	/**
	 * i+w, j+w ������ ���� type�� �־�ΰ�, ���� ���� ������ ������ �ϳ��� �ٿ����� �߾Ӱ����� �Ǻ��Ѵ�. 
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		mid = w * w / 2 + 1;
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i <= n - w; i++) {
			for (int j = 0; j <= m - w; j++)
				answer.append(getMedian(i, j) + " ");
			answer.append('\n');
		}
		System.out.println(answer);
	}

	public static int getMedian(int x, int y) {
		int[] type = new int[k + 1];
		for (int i = x; i < x + w; i++)
			for (int j = y; j < y + w; j++)
				type[map[i][j]]++;
		int left = 0, right = k, leftCnt = 0, rightCnt = 0;
		while (left <= right) {
			if ((leftCnt += type[left++]) >= mid)
				return left - 1;
			else if ((rightCnt += type[right--]) >= mid)
				return right + 1;
		}
		return -1;
	}
}