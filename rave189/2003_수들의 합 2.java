package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N개의 수로 이루어진 수열이 있다.
 * 이 수열의 부분수열중 그 합이 M이 되는 경우의 수를 구하는 문제
 * @author Rave
 *
 */
public class Main {

	/**
	 * 투 포인터로 범위의 값이 m인지 확인해본다.
	 * start <= end 조건을  while 조건 안에 넣어주었는데, 이것때문에 자꾸 틀린 값이 나왔음
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