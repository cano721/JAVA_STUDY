package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N개의 수로 이루어진 수열이 있다.
 * 이 수열에서 두 수를 선택했을 때(같은 수일 수도 있다),
 * 그 차이가 M이상이면서 가장 작은 경우를 구하려고 한다.
 * 
 * @author Rave
 *
 */
public class Main {

	/**
	 * 같은 수가 가능하므로 두 개의 포인터를 0으로 두고 시작한다.
	 * 만약 두 포인터의 값의 차이가 M 이상인 경우 가장 작은 경우인지 검사한다.
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