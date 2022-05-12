package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input = br.readLine().toCharArray();
		int[] left = new int[input.length + 1];
		int[] right = new int[input.length + 1];
		for (int i = 0; i < input.length; i++) {
			if (input[i] == '(')
				left[i + 1]++;
			else
				right[i + 1]++;
		}
		for (int i = 1; i <= input.length; i++) {
			left[i] += left[i - 1];
			right[i] += right[i - 1];
		}
		int answer = 0;
		int sum = 0;
		for (int i = 1; i <= input.length && sum >= 0; i++) {
			char ch = input[i - 1];
			sum += ch == '(' ? 1 : -1;
			int prevLeftBraket = left[i - 1];
			int nextLeftBraket = left[input.length] - left[i];
			int prevRightBraket = right[i - 1];
			int nextRightBraket = right[input.length] - right[i];
			nextLeftBraket += prevLeftBraket - prevRightBraket;
			if (ch == '(') {
				if (nextLeftBraket == nextRightBraket + 1)
					answer++;
			} else {
				if (nextLeftBraket + 1 == nextRightBraket)
					answer++;
			}
		}
		System.out.println(answer);
	}
}