package elwlahd555.programmers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class 프로그래머스67258_보석_쇼핑 {
	public static void main(String[] args) {
		String gems[] = { "DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA" };
		System.out.println(solution(gems));
	}

	public static int[] solution(String[] gems) {
		LinkedList<String> que = new LinkedList<>();
		HashSet<String> gemsSet = new HashSet<>();
		HashMap<String, Integer> gemsMap = new HashMap<>();
		int start = 0;
		int end = Integer.MAX_VALUE;

		for (String s : gems) { // 보석종류
			gemsSet.add(s);
		}
		int startPoint = 0;
		for (int i = 0; i < gems.length; i++) {
			gemsMap.put(gems[i], gemsMap.getOrDefault(gems[i], 0) + 1);

			que.add(gems[i]);

			while (true) {
				String temp = que.peek(); // 구간 내 보석이 1이상 있으면 start++
				if (gemsMap.get(temp) > 1) {
					que.poll();
					start++;
					gemsMap.put(temp, gemsMap.get(temp) - 1);
				} else {
					break;
				}
			}
			if (gemsMap.size() == gemsSet.size() && end > que.size()) {
				end = que.size();
				startPoint = start;
			}
		}
		return new int[] { startPoint + 1, startPoint + end };
	}
}
