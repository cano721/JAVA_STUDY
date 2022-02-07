import java.util.HashMap;
import java.util.HashSet;

class PG_보석쇼핑 {
    public int[] solution(String[] gems) {
		int start=0, end=0, left=0, right=0;
		int dist = Integer.MAX_VALUE;
		int []answer = new int[2];
		HashSet<String> set = new HashSet<>();
		
		for(String s : gems) set.add(s);
		
		HashMap<String, Integer> map = new HashMap<>();
		
		while(true) {
			if(map.size() == set.size()) {
				map.put(gems[left], map.get(gems[left])-1);
                if (map.get(gems[left]) == 0) {
                    map.remove(gems[left]);
                }
				left++;
			}
			else if(right == gems.length) break;
			else {
				map.put(gems[right], map.getOrDefault(gems[right], 0)+1);
				right++;
			}
			
			if(map.size() == set.size()) {
				if(right- left < dist) {
					dist = right-left;
					start = left +1;
					end = right;
				}
			}
		}
        answer[0] = start;
		answer[1] = end;
        return answer;
    }
}