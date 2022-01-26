package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arr1 = new int[n], arr2 = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++)
			arr1[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++)
			arr2[i] = Integer.parseInt(st.nextToken());
		int idx1 = 0, idx2 = 0;
		while (idx1 < n && idx2 < m) {
			if (arr1[idx1] < arr2[idx2])
				answer.append(arr1[idx1++]).append(' ');
			else
				answer.append(arr2[idx2++]).append(' ');
		}
		while (idx1 < n)
			answer.append(arr1[idx1++]).append(' ');
		while (idx2 < m)
			answer.append(arr2[idx2++]).append(' ');
		System.out.println(answer);
	}
}