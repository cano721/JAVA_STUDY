package 전체유형문제풀이;

import java.util.*;

/* 투포인터?
 * 
 *  
 *  n/2인 중앙값을 기준으로 a와 b 둘다 중앙값 왼편 혹은 오른편에 같이 있다면 전체라운드를 대결할 필요없다.
 *  
 *  a,b분포 위치에 따라 시작과 끝을 조절하여 최종적으로
 *  가운데 수를 기준으로 쪽에 분포할 때까지 반복하기
 *  
 *  마지막으로 중앙값 기준으로 양쪽으로 값이 있다면 모든 라운드에 다 참가하게 됨으로 log2를 이용하여 최종 라운드 계산해주기
 *  
 *  https://small-stap.tistory.com/m/33
 *  */

public class PG12985_예상대진표 {

	public static void main(String[] args) {

		int n = 8;
		int a = 4;
		int b = 7;

		int result = solution(n, a, b);

		System.out.println(result);

	}

	private static int solution(int n, int a, int b) {
		int answer = 0;

		if (a > b) {
			int temp = a;
			a = b;
			b = temp;
		}
		/*
		//	8, 1, 2 => 실행결괏값 2, 기댓값 1
		
		if((n/2 >= a && n/2 >= b) || (n/2 < a && n/2 < b)){
		    answer = (int)baselog2(n)-1;
		}
		
		else{
		   answer = (int)baselog2(n);
		}
		*/

		int start = 1;
		int end = n;

		while (true) {

			int mid = start + (end - start) / 2;

			if (mid >= a && mid >= b) {
				end = mid;
			} else if (mid < a && mid < b) {
				start = mid + 1;
			}

			else {
				answer = (int) baselog2(end - start + 1);
				break;
			}
		}

		return answer;
	}

	public static double baselog2(int n) {
		return Math.log10(n) / Math.log10(2);
	}
}