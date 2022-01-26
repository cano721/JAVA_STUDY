package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int num = 665;
		do {
			if (isValid(++num))
				n--;
		} while (n > 0);
		System.out.println(num);
	}

	public static boolean isValid(int num) {
		int count = 0;
		for (char ch : Integer.toString(num).toCharArray()) {
			if (ch == '6')
				count++;
			else
				count = 0;
			if (count == 3)
				return true;
		}
		return false;
	}
}