package 전체유형문제풀이;

import java.util.*;

/*
[정렬]

Comparator 구현하기

1. 정규식 표현을 이용하여 0번 인덱스에 head 담기
2. compareTo 사용하여 문자 비교하기
2-1. 대소문자는 구별하지 않음으로, 소문자로 일괄 변경하기
     문자1 > 문자2 , 양수 반환
     문자1 < 문자2, 음수 반환
     문자1 = 문자2, 0 반환
     
3. 결과가 0인 경우, 숫자비교하기


https://subin-0320.tistory.com/103

*/
public class PG42888_오픈채팅방 {

	public static void main(String[] args) {

		String[] record = { "Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo",
				"Change uid4567 Ryan" };

		String[] res = solution(record);

		for (String str : res) {
			System.out.print(str + " ");
		}

	}

	private static String[] solution(String[] record) {

		String[] answer = {};
		Map<String, String> map = new HashMap<>();

		//1.answer 초기화
		int len = 0;
		for (int i = 0; i < record.length; i++) {
			String[] temp = record[i].split(" ");
			if (!"Change".equals(temp[0]))
				len++;
		}

		answer = new String[len];

		for (int i = 0; i < record.length; i++) {

			String[] temp = record[i].split(" ");

			//유저의 아이디 저장해 두기
			if (!"Leave".equals(temp[0])) {
				if (!map.containsKey(temp[1])) {
					map.put(temp[1], temp[2]);
				} else {
					map.put(temp[1], temp[2]);
				}
			}
		}

		for (int i = 0; i < record.length; i++) {

			String[] temp = record[i].split(" ");

			if ("Enter".equals(temp[0])) {
				String tempID = (String) map.get(temp[1]);
				answer[i] = tempID + "님이 들어왔습니다.";
			} else if ("Leave".equals(temp[0])) {
				String tempID = (String) map.get(temp[1]);
				answer[i] = tempID + "님이 나갔습니다.";
			}
		}
		return answer;
	}
}
