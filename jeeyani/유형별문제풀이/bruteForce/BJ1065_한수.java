package 유형별문제풀이.bruteForce;

import java.io.*;

public class BJ1065_한수 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int result = 0;

		for (int i = 1; i <= n; i++) {
			
			//1~99까지는 한수가 무조건 1개씩
			if (i >= 1 && 99 >= i) {
				result++;
			} else {
				int f = i / 100;
				int s = (i - (f * 100)) / 10;
				int t = (i - (f * 100) - (s * 10));
				int mis = s - f;

				if ((s + mis) == t)
					result++;
			}
		}

		System.out.println(result);

	}

}
