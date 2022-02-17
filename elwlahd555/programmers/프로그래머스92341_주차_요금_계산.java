package elwlahd555.programmers;

import java.util.Arrays;
import java.util.HashMap;

public class 프로그래머스92341_주차_요금_계산 {
	public static void main(String[] args) {
		int fees[] = { 180, 5000, 10, 600 };
		String records[] = { "05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN",
				"18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT" };
		System.out.println(solution(fees, records));
	}

	public static int[] solution(int[] fees, String[] records) {
		HashMap<String, String> cars = new HashMap<String, String>();	//주차시간 누적
		HashMap<String, Integer> result = new HashMap<String, Integer>();	//주차요금
		for (int i = 0; i < records.length; i++) {
			String temp[] = records[i].split(" ");
			if (temp[2].equals("IN")) {	//IN 인 경우 cars에 넣어준다.
				cars.put(temp[1], temp[0]);
			} else {	//OUT인 경우 기존에 들어가 있는 IN 시간을 가져온다.
				String timeIn[] = cars.get(temp[1]).split(":");
				cars.remove(temp[1]);
				String timeOut[] = temp[0].split(":");
				int min = 0;	//분을 계산한다.
				min += (Integer.parseInt(timeOut[0]) - Integer.parseInt(timeIn[0])) * 60;
				min += Integer.parseInt(timeOut[1]) - Integer.parseInt(timeIn[1]);
				if (result.containsKey(temp[1])) {	//계산된 분을 result에 누적으로 담아준다.
					result.replace(temp[1], result.get(temp[1]) + min);
				} else {
					result.put(temp[1], min);
				}
			}
		}
		for (String s : cars.keySet()) {	//OUT 기록이 없는 차들을 꺼내준다.
			String[] timeIn = cars.get(s).split(":");
			int min = 0;
			min += (23 - Integer.parseInt(timeIn[0])) * 60;
			min += 59 - Integer.parseInt(timeIn[1]);
			if (result.containsKey(s)) {
				result.replace(s, result.get(s) + min);
			} else {
				result.put(s, min);
			}
		}
		Object[] sortKey = result.keySet().toArray();	//차 번호 순서대로 정렬
		Arrays.sort(sortKey);
		int[] answer = new int[sortKey.length];
		for (int i = 0; i < sortKey.length; i++) {	//누적 시간만큼 요금 계산
			int totalMin = result.get(sortKey[i]);
			int fee = 0;
			if (totalMin >= fees[0]) {	//기본금 계산
				totalMin -= fees[0];
				fee = fees[1];
			} else {
				totalMin = 0;
				fee = fees[1];
			}
			if (totalMin > 0) {	//기본금 계산 후 남은 시간 계산
				if (totalMin % fees[2] == 0) {
					fee += totalMin / fees[2] * fees[3];
				} else {
					fee += (totalMin / fees[2] + 1) * fees[3];
				}
			}
			answer[i] = fee;
		}
		return answer;
	}
}
