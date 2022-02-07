package programmers;

import java.util.HashMap;
import java.util.HashSet;

public class d220207_보석쇼핑 {

	public static void main(String[] args) {
		String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
		int[] answer = solution(gems);	//[3, 7]
		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
	}

	private static int[] solution(String[] gems) {
		HashSet<String> set = new HashSet<String>();
	    HashMap<String, Integer> map = new HashMap<>();
	    int[] answer = new int[2];
	    
		for(String gem : gems) {
			set.add(gem);
		}
        int start = 0, end = 0, min = Integer.MAX_VALUE;
        
        while(end < gems.length) {
            if (map.size() < set.size()) {
                map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);
                end++;
            }
            while (map.size() == set.size()) {
                if (end - start < min) {
                    min = end - start;
                    answer[0] = start + 1;
                    answer[1] = end;
                }
                map.put(gems[start], map.get(gems[start]) - 1);
                if (map.get(gems[start]) == 0)
                    map.remove(gems[start]);
                start++;
            }
        }
		return answer;
	}

}
