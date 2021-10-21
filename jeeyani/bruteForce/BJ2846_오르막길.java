package bruteForce;

import java.io.*;
import java.util.*;

public class BJ2846_오르막길 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());

		}

		int result = 0;
		int max = 0;
		for (int i = 1; i < n; i++) {

			if (arr[i] > arr[i - 1]) {
				max += arr[i] - arr[i - 1];
				
			}
			/*높이가 계속 동일한 경우도 고려했어야 했음..!!
			else if (arr[i] < arr[i - 1]) {
				max = 0;
			}
			*/
			else {
				max = 0;
			}
			
			result = Math.max(max, result);
		}

		System.out.println(result);
	}
}
