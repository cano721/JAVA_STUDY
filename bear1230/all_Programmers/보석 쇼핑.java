import java.util.*;

class Solution {
	public int[] solution(String[] gems) {
		int[] answer = new int[2];

		Set<String> hs = new HashSet<>();
		for (String gem : gems) {
			hs.add(gem);
		}

		Map<String, Integer> map = new HashMap<>();
		Queue<String> que = new LinkedList<>();

		int idx = 0;
		int start = 0;
		int size = Integer.MAX_VALUE;
		for (int i = 0; i < gems.length; i++) {
			if (map.containsKey(gems[i]))
				map.put(gems[i], map.get(gems[i]) + 1);
			else
				map.put(gems[i], 1);

			que.add(gems[i]);

			while (true) {
				String gem = que.peek();
				if (map.get(gem) > 1) {
					map.put(gem, map.get(gem) - 1);
					que.poll();
					idx++;
				} else {
					break;
				}
			}

			if (map.size() == hs.size() && size > que.size()) {
				size = que.size();
				start = idx;
			}

		}

		answer[0] = start + 1;
		answer[1] = start + size;

		return answer;
	}
}
