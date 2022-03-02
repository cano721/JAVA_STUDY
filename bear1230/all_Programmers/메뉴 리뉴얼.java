
import java.util.*;

class Solution {
	public static String[] solution(String[] orders, int[] course) {
		String[] answer = {};
		HashMap<String, Integer> map = new HashMap<>();
		for (String str : orders) {
			char[] order = str.toCharArray();
			Arrays.sort(order);
			combi(0, "", order, map);
		}

		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < course.length; i++) {
			int max = 0;
			Iterator<String> iter = map.keySet().iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				if (key.length() == course[i]) {
					max = Math.max(max, map.get(key));
				}
			}

			Iterator<String> keys = map.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				if (key.length() == course[i] && map.get(key) == max && max > 1) {
					list.add(key);
				}
			}
		}
		answer = new String[list.size()];
		int idx = 0;
		for (String string : list) {
			answer[idx++] = string;
		}
		Arrays.sort(answer);
		return answer;
	}

	private static void combi(int start, String str, char[] order, HashMap<String, Integer> map) {
		if (str.length() > 1)
			map.put(str, map.getOrDefault(str, 0) + 1);

		for (int i = start; i < order.length; i++) {
			combi(i + 1, str + order[i], order, map);
		}
	}
}
