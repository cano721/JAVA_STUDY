package elwlahd555.programmers;

public class 프로그래머스12980_점프와_순간_이동 {
	public static void main(String[] args) {
		int n = 5;
		System.out.println(solution(n));
	}

	public static int solution(int n) {
		int ans = 0;
		while (n != 0) {
			if (n % 2 == 0) {
				n /= 2;
			} else {
				n--;
				ans++;
			}
		}
		return ans;
	}
}
