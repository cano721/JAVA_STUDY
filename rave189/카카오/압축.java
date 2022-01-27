package Programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		Solution s = new Solution();
		String msg = // "KAKAO";
				"TOBEORNOTTOBEORTOBEORNOT";
		int[] result = s.solution(msg);
		for (int v : result)
			System.out.println(v);
	}

}

class Solution {
	Map<String, Integer> hash = new HashMap<>();
	ArrayList<Integer> answer = new ArrayList<>();
	int count = 1;

	/**
	 * 다음과 같은 알고리즘을 통해 메세지를 압축한다.
	 * 1. 길이가 1인 모든 단어를 포함하도록 사전을 초기화한다.
	 * 2. 사전에서 현재 입력과 일치하는 가장 긴 문자열 w를 찾는다.
	 * 3. w에 해당하는 사전의 색인 번호를 출력하고, 입력에서 w를 제거한다.
	 * 4. 입력에서 처리되지 않은 다음 글자가 남아있다면(c), w+c에 해당하는 단어를 사전에 등록한다.
	 * 5. 단계 2로 돌아간다.
	 * @param msg 압축할 문자열
	 * @return 압축 결과
	 */
	public int[] solution(String msg) {
		init();
		for (int i = 0; i < msg.length();) {
			// 가장 긴 문자열 찾기
			String longestStr = "";
			while (i < msg.length() && hash.containsKey(longestStr + msg.charAt(i)))
				longestStr += msg.charAt(i++);
			// 제거가 없네? 근데 통과네?
			answer.add(hash.get(longestStr));
			// 다음 글자가 있다면 가장 긴 문자열에 합쳐서 색인을 사전에 등록한다.
			if (i < msg.length()) {
				longestStr += msg.charAt(i);
				hash.put(longestStr, count++);
			}
		}
		return answer.stream().mapToInt(Integer::intValue).toArray();
	}

	public void init() {
		// 1. 초기화
		for (int i = 0; i < 26; i++)
			hash.put((char) ('A' + i) + "", count++);
	}
}