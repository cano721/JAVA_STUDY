package Programmers;

import java.util.HashMap;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();

	}
}

class Solution {
	HashMap<Integer, Integer> inCar = new HashMap<>();
	TreeMap<Integer, Integer> result = new TreeMap<>();

	/**
	 * 주차장의 요금표와 차량의 출입 기록이 주어질 때, 각 차량마다 부과되어야 하는 요금을
	 * 차량 번호의 오름차순으로 반환하는 문제
	 * @param fees 요금표
	 * @param records 차량의 출입 기록
	 * @return 각 차량 별 요금
	 */
	public int[] solution(int[] fees, String[] records) {
		for (String record : records) {
			String[] split = record.split(" ");
			int minute = timeToMinute(split[0]);
			int carNo = Integer.parseInt(split[1]);
			// 입차를 넣는다.
			// 출차는 주차 시간을 더해주고 입차에서 제거한다.
			if (split[2].equals("IN")) {
				inCar.put(carNo, minute);
			} else {
				result.put(carNo, result.getOrDefault(carNo, 0) + minute - inCar.get(carNo));
				inCar.remove(carNo);
			}
		}
		// 안나간 차량은 23:59을 출차 시간으로 한다.
		int out = timeToMinute("23:59");
		for (int carNo : inCar.keySet()) {
			result.put(carNo, result.getOrDefault(carNo, 0) + out - inCar.get(carNo));
		}
		int[] answer = new int[result.size()];
		int idx = 0;
		for (int key : result.keySet()) {
			answer[idx++] = getTotalFee(result.get(key), fees);
		}
		return answer;
	}

	public int timeToMinute(String str) {
		String[] split = str.split(":");
		return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
	}

	public int getTotalFee(int time, int[] fee) {
		if (time <= fee[0])
			return fee[1];
		time -= fee[0];
		return fee[1] + ((int) (Math.ceil(time / (double) fee[2])) * fee[3]);
	}
}