package 전체유형문제풀이;

import java.util.*;
import java.util.Map.Entry;

/*

1. 코스 갯수에 따른 메뉴 구성구하기(조합)
2. 각 구한 메뉴구성이 몇번이나 주문되었는지 확인
3. 가장 주문이 많이 된 메뉴구성을 저장
4. 오름차순으로 정렬

https://fbtmdwhd33.tistory.com/249
*/

public class PG72411_메뉴리뉴얼 {

	public static void main(String[] args) {

		String[] orders = { "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH" };
		int[] course = { 2, 3, 4 };

		ArrayList<String> result = solution(orders, course);

		System.out.println(result);

	}

	static HashMap<String, Integer> map;

	private static ArrayList<String> solution(String[] orders, int[] course) {
		ArrayList<String> answer = new ArrayList<>();

		//오름차순 정렬
		for (int i = 0; i < orders.length; i++) {

			char[] charOrder = orders[i].toCharArray();
			Arrays.sort(charOrder);
			orders[i] = String.valueOf(charOrder);

		}

		//메뉴 구성갯수에 따른 메뉴구성 구하기
		for (int i = 0; i < course.length; i++) {
			map = new HashMap<>();
			int max = Integer.MIN_VALUE;

			for (int j = 0; j < orders.length; j++) {
				StringBuilder sb = new StringBuilder();

				if (course[i] <= orders[j].length()) {
					combi(orders[j], sb, 0, 0, course[i]);
				}
			}

			//가장 많이 주문된 횟수 구하기
			for (Entry<String, Integer> entry : map.entrySet()) {
				max = Math.max(max, entry.getValue());
			}

			//최소 2번 이상 주문된 메뉴구성, 해당 횟수와 일치하는 조합만 담기
			for (Entry<String, Integer> entry : map.entrySet()) {
				if (max >= 2 && entry.getValue() == max) {
					answer.add(entry.getKey());
				}
			}
		}

		Collections.sort(answer);

		return answer;
	}

	//조합을 이용해서 메뉴 구성 구하기
	public static void combi(String str, StringBuilder sb, int idx, int cnt, int n) {

		if (cnt == n) {
			//구한 조합을 map 저장, 횟수도 함께 저장
			map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1);
			return;
		}

		for (int i = idx; i < str.length(); i++) {
			sb.append(str.charAt(i));
			combi(str, sb, i + 1, cnt + 1, n);
			sb.delete(cnt, cnt + 1);
		}
	}

}