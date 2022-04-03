package 전체유형문제풀이.프로그래머스;

import java.util.*;

/*

그리드...?

*/

public class PG12980_점프와순간이동 {

	public static void main(String[] args) {

		int n = 6;

		int result = solution(n);

		System.out.println(result);

	}

	private static int solution(int n) {
		int ans = 0;

		while (n > 0) {

			//짝수이면 순간이동
			if (n % 2 == 0) {
				n /= 2;
			}
			//홀수이면 한칸 점프 (점프때 사용량 체크)
			else {
				n -= 1;
				ans++;
			}
		}

		return ans;
	}

}