package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		for (int i = 1; i <= n; i++) {
			int v = decompose(i);
			if (v == n) {
				System.out.println(i);
				return;
			}
		}
		System.out.println(0);
	}

	public static int decompose(int n) {
		int sum = n;
		for (char ch : Integer.toString(n).toCharArray())
			sum += ch - '0';
		return sum;
	}
}