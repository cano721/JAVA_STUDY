package 전체유형문제풀이;

import java.util.*;

/*
 * 
 * h-idex 설명: https://aigong.tistory.com/67
 * 
 * h개의 논문이 h번 인용되고 나머지가 h번 미만 인용되면 h-idex = h
 * 
 * 즉, 내림차순 기준으로 i번째에 해당하는 지수가 i가 되면 h-index = i 인 것이다.
 * 
 * */

public class PG42747_Hindex {

	public static void main(String[] args) {

		int[] citations = { 3, 0, 6, 1, 5 };

		int result = solution(citations);

		System.out.println(result);

	}

	private static int solution(int[] citations) {
		int answer = 0;

		Integer[] temp = new Integer[citations.length];
		for (int i = 0; i < citations.length; i++) {
			temp[i] = citations[i];
		}

		Arrays.sort(temp, Collections.reverseOrder());

		for (int i = 0; i < citations.length; i++) {
			if ((i + 1) <= temp[i])
				answer = i + 1;
		}

		return answer;
	}

}