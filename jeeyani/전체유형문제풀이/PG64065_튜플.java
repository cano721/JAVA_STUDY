package 전체유형문제풀이;

/*

[문자열, 정렬]

1. 문자열로 들어오는 값을 배열의 형태로 넣어주는 작업 필요
2. 구한 배열을 이용하여 튜플 구하기
2-1. 해당 입력값은 중복되는 원소가 없는 튜플**!!
2-2. 배열을 길이가 짧은 순서대로 정렬
2-3. 해당 값이 존재하는 순서대로 정렬하여 튜플을 완성시키자


*/

import java.util.*;

public class PG64065_튜플 {

	public static void main(String[] args) {

		String s = "{{4,2,3},{3},{2,3,4,1},{2,3}}";

		int[] res = solution(s);

		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i] + " ");

		}

	}

	private static int[] solution(String s) {
		int[] ans = {};
		HashSet<Integer> set = new HashSet<>();

		String S_remove = s.replaceAll("[{}]", " ");
		String[] sTemp = S_remove.split(" ,");

		ans = new int[sTemp.length];
		Arrays.sort(sTemp, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		});

		int idx = 0;
		for (int i = 0; i < sTemp.length; i++) {

			for (String ss : sTemp[i].split(",")) {
				int a = Integer.parseInt(ss.trim());

				if (!set.contains(a)) {
					set.add(a);
					ans[idx++] = a;
				} else
					continue;
			}

		}
		return ans;
	}
}
