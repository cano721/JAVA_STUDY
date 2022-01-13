package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int x = Integer.parseInt(br.readLine());
		int wood = 64;
		int count = 0;
		while (x > 0) {
			if (x >= wood) {
				x -= wood;
				count++;
			} else
				wood >>= 1;
		}
		System.out.println(count);
	}
}