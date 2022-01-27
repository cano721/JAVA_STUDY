import java.util.*;

class Solution {
	public int[] solution(String msg) {
		int[] answer = {};
		HashMap<String, Integer> map = new HashMap<>();
		ArrayList<Integer> list = new ArrayList<>();

		for (int i = 'A'; i <= 'Z'; i++) {
			map.put(String.valueOf((char) i), i - 65 + 1);
		}
		int idx = 27;

		for (int i = 0; i < msg.length(); i++) {
			String tmp = String.valueOf(msg.charAt(i));
			int num = map.get(tmp);
			
			for (int j = i + 1; j < msg.length(); j++) {
				tmp += msg.charAt(j);

				if (map.containsKey(tmp)) {
					num = map.get(tmp);
					i += j - i;
				} else {
					map.put(tmp, idx++);
					break;
				}
			}
			list.add(num);
		}

		answer = new int[list.size()];

		for (int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i);
		}

		return answer;
	}
}
