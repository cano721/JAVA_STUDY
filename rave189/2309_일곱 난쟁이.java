package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static int[] arr = new int[9];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			sum += arr[i];
		}
		Arrays.sort(arr);
		for (int i = 0; i < arr.length; i++)
			for (int j = i + 1; j < arr.length; j++)
				if (sum - arr[i] - arr[j] == 100) {
					print(i, j);
					return;
				}
	}

	public static void print(int x, int y) {
		for (int i = 0; i < arr.length; i++) {
			if (i == x || i == y)
				continue;
			System.out.println(arr[i]);
		}
	}
}