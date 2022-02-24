package elwlahd555.programmers;

import java.util.HashMap;

public class 프로그래머스17677_뉴스_클러스터링 {
	public static void main(String[] args) {
		String str1 = "aa1+aa2";
		String str2 = "AAAA12";
		System.out.println(solution(str1, str2));
	}

	public static int solution(String str1, String str2) {
		int answer = 0;
		double min = 0;
		double max = 0;
		HashMap<String, int[]> map = new HashMap<String, int[]>();
		StringBuilder sb1 = new StringBuilder(str1.toUpperCase());
		for (int i = 0; i < sb1.length() - 1; i++) {
			if (sb1.charAt(i) >= 'A' && sb1.charAt(i) <= 'Z' && sb1.charAt(i + 1) >= 'A' && sb1.charAt(i + 1) <= 'Z') {
				if (map.containsKey(sb1.substring(i, i + 2))) {
					map.get(sb1.substring(i, i + 2))[0]++;
				} else {
					int temp[] = { 1, 0 };
					map.put(sb1.substring(i, i + 2), temp);
				}
			}
		}
		StringBuilder sb2 = new StringBuilder(str2.toUpperCase());
		for (int i = 0; i < sb2.length() - 1; i++) {
			if (sb2.charAt(i) >= 'A' && sb2.charAt(i) <= 'Z' && sb2.charAt(i + 1) >= 'A' && sb2.charAt(i + 1) <= 'Z') {
				if (map.containsKey(sb2.substring(i, i + 2))) {
					map.get(sb2.substring(i, i + 2))[1]++;
				} else {
					int temp[] = { 1, 0 };
					map.put(sb2.substring(i, i + 2), temp);
				}
			}
		}
		for (String s : map.keySet()) {
			min += Math.min(map.get(s)[0], map.get(s)[1]);
			max += Math.max(map.get(s)[0], map.get(s)[1]);
		}
		if (max == 0) {
			min = 1;
			max = 1;
		}
		double result = min / max;
		answer = (int) (65536 * result);
		return answer;
	}
}
