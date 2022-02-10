package elwlahd555.programmers;

public class 프로그래머스12900_2XN_타일링 {
	public static void main(String[] args) {
		int n = 4;
		System.out.println(solution(n));
	}

	public static int solution(int n) {
		int a = 1;
		int b = 1;
		for (int i = 0; i < n - 1; i++) {
			int c = (a + b) % 1000000007;
			a = b;
			b = c;
		}
		return b;
	}
}
