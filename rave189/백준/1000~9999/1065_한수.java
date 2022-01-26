package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int answer = 0;
		for (int i = 1; i <= n; i++)
			if (isValid(Integer.toString(i)))
				answer++;
		System.out.println(answer);
	}

	public static boolean isValid(String num) {
		for (int i = 1; i < num.length(); i++)
			if (num.charAt(i) - num.charAt(i - 1) != num.charAt(1) - num.charAt(0))
				return false;
		return true;
	}
}