package 전체유형문제풀이;

/*

1. 문자열 길이 기준 만큼 탐색하기
2. 문자열 단위(자르는 길이)만큼 반복하여 자르기
2-1. 압축된 문자 갯수 표시해주면서 압축 (Cnt)
3. 가장 작은 값으로 리턴


*/

import java.util.*;

public class PG60057_문자열압축 {

	public static void main(String[] args) {

		String s = "abcabcabcabcdededededede";

		int res = solution(s);

		System.out.println(res);
	}

	private static int solution(String s) {
		int answer = Integer.MAX_VALUE;

		int len = s.length();

		//*** 문자열 s의 범위는 1부터!!!!
		if (len == 1) return 1;

		//1. 문자열 길이만큼 탐색
		for (int i = 1; i < len; i++) {

			String now = "";
			String temp = "";
			String press = "";

			int Cnt = 1;

			//2. 문자열 단위만큼 자르기
			for (int j = 0; j <= (len / i); j++) {
				int start = i * j;
				int end = i * (j + 1);

				//길이 초과시 초기화
				if (end > len) end = len;

				//새로 갱신된 문자열 단위만큼 자르기
				now = press;
				press = s.substring(start, end);

				//비교하고자 하는 문자열이 현재 보이는 문자열과 같다면 갯수 증가
				if (now.equals(press)) Cnt++;

				else {
					//2-1.압축된 문자 갯수 표시해주면서 압축
					if (Cnt > 1) {
						temp += String.valueOf(Cnt);
					}
					temp += now;
					Cnt = 1;
				}
			}

			//나머지 문자열 처리
			if (Cnt > 1) temp += String.valueOf(Cnt);
			temp += press;

			//최소길이 찾기
			answer = Math.min(temp.length(), answer);

		}

		return answer;
	}

}
