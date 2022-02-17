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
	 * �������� ���ǥ�� ������ ���� ����� �־��� ��, �� �������� �ΰ��Ǿ�� �ϴ� �����
	 * ���� ��ȣ�� ������������ ��ȯ�ϴ� ����
	 * @param fees ���ǥ
	 * @param records ������ ���� ���
	 * @return �� ���� �� ���
	 */
	public int[] solution(int[] fees, String[] records) {
		for (String record : records) {
			String[] split = record.split(" ");
			int minute = timeToMinute(split[0]);
			int carNo = Integer.parseInt(split[1]);
			// ������ �ִ´�.
			// ������ ���� �ð��� �����ְ� �������� �����Ѵ�.
			if (split[2].equals("IN")) {
				inCar.put(carNo, minute);
			} else {
				result.put(carNo, result.getOrDefault(carNo, 0) + minute - inCar.get(carNo));
				inCar.remove(carNo);
			}
		}
		// �ȳ��� ������ 23:59�� ���� �ð����� �Ѵ�.
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