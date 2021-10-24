import java.io.BufferedReader;
import java.io.InputStreamReader;

public class D20211024_1_BJ_10870_피보나치수5 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		System.out.println(fibonacci(n));
	}

	static int fibonacci(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		return fibonacci(n- 1) + fibonacci(n - 2);
	}
}
