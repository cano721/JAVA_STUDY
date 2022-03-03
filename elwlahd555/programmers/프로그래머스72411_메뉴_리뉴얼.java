package elwlahd555.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

public class 프로그래머스72411_메뉴_리뉴얼 {
	public static void main(String[] args) {
		String orders[]= {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
		int course[]= {2,3,4};
		System.out.println(solution(orders, course));
	}

	// 12. combi 메소드에서 map에 접근하기 위해 전역변수로 선언.
	static HashMap<String, Integer> map;

	// 13. 조합을 구하는 메소드 (한 사람의 메뉴구성, 조합을 구할 StringBuilder, 조합을 위한 idx, 코스요리 개수에 따른
	// 종료조건을 위한 cnt와 n)
	public static void combi(String str, StringBuilder sb, int idx, int cnt, int n) {
		// 14. 각 코스요리의 개수만큼 조합이 되면,
		if (cnt == n) {
			// 15. map에 해당 조합을 삽입 및 카운팅.
			map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
			return;
		}

		// 16. idx부터 시작함으로써 조합을 구할 수 있다.
		for (int i = idx; i < str.length(); i++) {
			// 17. sb에 붙여나간다.
			sb.append(str.charAt(i));
			// 18. 재귀호출.
			combi(str, sb, i + 1, cnt + 1, n);
			// 19. 붙였던거 다시 떼기.
			sb.delete(cnt, cnt + 1);
		}
	}

	public static ArrayList<String> solution(String[] orders, int[] course) {
		ArrayList<String> answer = new ArrayList<>();

		// 1. 각 문자열을 오름차순 정렬.
		for (int i = 0; i < orders.length; i++) {
			// 2. 각 문자열을 문자형 배열로 변환.
			char[] charArr = orders[i].toCharArray();
			// 3. 해당 문자형 배열을 정렬.
			Arrays.sort(charArr);
			// 4. 정렬된 문자형 배열을 문자열로 변환해 저장.
			orders[i] = String.valueOf(charArr);
		}

		// 5. course의 길이만큼 반복하여 필요한 조합을 구함.
		for (int i = 0; i < course.length; i++) {
			// 6. HashMap으로 조합의 수를 카운팅.
			map = new HashMap<>();
			// 7. course의 경우에 따라 구한 조합들 중 가장 많이 주문된 횟수를 저장.
			int max = Integer.MIN_VALUE;
			// 8. 각 사람들의 조합을 구하기 위해 탐색.
			for (int j = 0; j < orders.length; j++) {
				// 9. 조합을 구하기 위해 문자열을 조작할 StringBuilder.
				StringBuilder sb = new StringBuilder();
				// 10. 코스의 개수 <= 각 문자열의 길이인 경우 조합을 구한다.
				if (course[i] <= orders[j].length())
					// 11. 조합을 구하기 위한 메소드 호출
					combi(orders[j], sb, 0, 0, course[i]);
			}

			// 20. 가장 많이 주문된 횟수를 max에 저장.
			for (Entry<String, Integer> entry : map.entrySet()) {
				max = Math.max(max, entry.getValue());

			}
			// 21. 최소 2번 이상 주문된 조합이며, 해당 횟수와 일치하는 조합은 ArrayList에 삽입.
			for (Entry<String, Integer> entry : map.entrySet()) {
				if (max >= 2 && entry.getValue() == max)
					answer.add(entry.getKey());
			}
		}
		// 22. 추가된 조합들을 오름차순 정렬.
		Collections.sort(answer);

		return answer;
	}
}
