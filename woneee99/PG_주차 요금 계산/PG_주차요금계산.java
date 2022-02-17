import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class PG_주차요금계산 {
	public int[] solution(int[] fees, String[] records) {
		HashMap<String, Integer> map = new HashMap<>(); // 주차중
		HashMap<String, Integer> check = new HashMap<>(); // 시간

		ArrayList<String> arr = new ArrayList<>(); // 차들
		for (int i = 0; i < records.length; i++) {
			String[] car = records[i].split(" ");
			int time = cal(car[0]);
			String carNum = car[1];

			if (!arr.contains(carNum)) {
				arr.add(carNum);
				check.put(carNum, 0);
			}
			if (car[2].equals("OUT")) {
				int in = map.get(carNum);
				check.put(carNum, check.get(carNum) + time - in);
				map.remove(carNum);
			} else {
				map.put(carNum, time);
			}
		}
		int[] result = new int[arr.size()];
		Collections.sort(arr);
		int last = cal("23:59");

		for (int i = 0; i < arr.size(); i++) {
			String car = arr.get(i);
			result[i] = fees[1]; // 기본 요금 시간

			int time = 0;
			if (check.containsKey(car))
				time = check.get(car);

			if (map.containsKey(car)) {
				time += (last - map.get(car));
			}
			time -= fees[0];
			if (time > 0)
				result[i] += (int) (Math.ceil(time / (1.0 * fees[2])) * fees[3]);
		}
		return result;
	}

	int cal(String time) {
		String[] t = time.split(":");
		int min = Integer.parseInt(t[0]);
		int sec = Integer.parseInt(t[1]);
		return min * 60 + sec;
	}
}