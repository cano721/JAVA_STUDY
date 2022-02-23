package 전체유형문제풀이;

import java.util.*;

/*

[정렬]

** Character.isLetter : 문자인지아닌지 판별

1. 해당 문자열의 각 문자들을 분할하여 확인하면서 문자를 검증
2. 각 검증된 문자들을 각 list에 담기
3. B리스트에 A리스트가 존재하면 B리스트에서 삭제하여 교집합 결과만 따로 담아주기
4. 합집합은 A+B리스트

*/

public class PG17677_1차뉴스클러스터링 {

	public static void main(String[] args) {

		String str1 = "aa1+aa2";
		String str2 = "AAAA12";

		int result = solution(str1, str2);

		System.out.println(result);

	}

	static final int fix = 65536;

	private static int solution(String str1, String str2) {
		List<String> listA = new ArrayList<>();
		List<String> listB = new ArrayList<>();

		//1. 해당 문자열의 각 문자들을 분할하여 확인하면서 문자를 검증
		//2. 각 검증된 문자들을 각 list에 담기
		listA = divStr(str1);
		listB = divStr(str2);

		Collections.sort(listA);
		Collections.sort(listB);

		//3. B리스트에 A리스트가 존재하면 B리스트에서 삭제하여 교집합 결과만 따로 담아주기
		List<String> inter = new ArrayList<>();
		List<String> union = new ArrayList<>();

		for (String strA : listA) {

			if (listB.contains(strA)) {
				listB.remove(strA);
				inter.add(strA);
			}
			union.add(strA);
		}

		//4. 합집합은 A+B리스트
		for (String strB : listB) {
			union.add(strB);
		}

		//A,B 모두 공집합인 경우

		double res = 0;

		if (union.size() == 0)
			return fix;
		else {

			res = (double) inter.size() / (double) union.size();

		}

		return (int) (res * fix);
	}

	private static List<String> divStr(String str) {
		List<String> list = new ArrayList<>();

		str = str.toUpperCase(); //대소문자 구분안함

		for (int i = 0; i < str.length() - 1; i++) {

			char s = str.charAt(i);
			char e = str.charAt(i + 1);

			if (Character.isLetter(s) && Character.isLetter(e)) {
				String temp = s + "" + e;
				list.add(temp);
			}

		}
		return list;
	}

}