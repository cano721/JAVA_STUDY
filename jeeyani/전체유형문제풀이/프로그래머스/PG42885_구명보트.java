package 전체유형문제풀이.프로그래머스;

import java.util.*;

/* 
 * 
 *  1. 제한을 넘는다면 다음 사람으로 넘어가기
 *  2. 제한을 넘지 않는다면, 첫번째 사람과 마지막 사람은 배에 태울 수 있기 때문에, 포인터 옮겨 다른 수 찾기
 *  3. 마지막에 계산되지 않고 남는 수가 있다면 if(s == e) 구명보트 추가하기
 *  
 *  */

public class PG42885_구명보트 {

	public static void main(String[] args) {


		int[] people = {70,50,80,50}; 
		
		int result = solution(people, 100);

		System.out.println(result); 

	}

	private static int solution(int[] people, int limit) {
		int answer = 0;
		
		Integer[] p = new Integer[people.length];
		for (int j = 0; j < p.length; j++) {
			p[j] = people[j];
		}
		
		Arrays.sort(p,Collections.reverseOrder());
		int s =0;
		int e =people.length-1;
		
		while(s < e) {
			int sum = p[s] + p[e];
			
			if(sum > limit) s++;
			else {
				s++;
				e--;
			}
			
			answer++;
		}
		if(s == e) answer++;
		
		return answer;
	}
	

}