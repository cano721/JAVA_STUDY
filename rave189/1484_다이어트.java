package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 다이어트 중인 성원이의 저울이 부서졌다.
 * 그래서 성원이의 친구가 새로운 저울을 선물해 주었다.
 * 그러자 성원이의 몸무게가 G 킬로그램 더 쪘다.
 * G 킬로그램 = 성원이의 현재 몸무게^2 - 성원이가 기억하는 몸무게^2 이다.
 * 성원이의 현재 몸무게로 가능한 것을 모두 출력하는 문제
 * 성원이의 몸무게는 자연수만 가능하다.
 * 또한, 가능한 무게가 없다면 -1을 출력한다.
 * @author Rave
 *
 */
public class Main {

	/**
	 * 성원이의 몸무게는 자연수이므로 left를 1, right를 2로 두고 두 포인터로 탐색한다.
	 * 
	 * 처음에 왜 두 포인터인지 감을 1도 못잡음.
	 * 질문 게시판에서 (a+b)(a-b)로 한다는 글 보고 푼 방식. 사실 a*a - b*b로 해도 상관 없었을 듯.
	 * 갑자기 이렇게 하면 되지 않을까해서 했는데 풀린 문제
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		int g = Integer.parseInt(br.readLine());
		int left = 1, right = 2;
		while (left < right) {
			int result = (right + left) * (right - left);
			if (result >= g) {
				if (result == g)
					answer.append(right).append('\n');
				left++;
			} else
				right++;
		}
		System.out.println(answer.length() == 0 ? -1 : answer);
	}
}