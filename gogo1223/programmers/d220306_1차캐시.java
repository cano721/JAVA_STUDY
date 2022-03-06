package programmers;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
public class d220306_1차캐시 {

	public static void main(String[] args) {
		int cacheSize = 3;
		String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
		int answer = solution(cacheSize, cities);
		System.out.println(answer);	//50

	}

	private static int solution(int cacheSize, String[] cities) {
		int answer = 0;
		Map<String, Integer> lru = new HashMap<>();
		
		for (int i = 0; i < cities.length; i++) {
			cities[i] = cities[i].toLowerCase();
			if(cacheSize == 0) {
				answer += 5;
				continue;
			}
			if(lru.size() < cacheSize) {
				if(lru.getOrDefault(cities[i], 1) != 1) {	//이미 존재하는 도시
					lru.put(cities[i], answer++);
					continue;
				}
				lru.put(cities[i], answer);
				answer += 5;
			} else {
				if(lru.getOrDefault(cities[i], 1) == 1) {	//새로운 도시가 들어왔을 때
					int min = -1;
					String minCity = "";
					for(Entry<String, Integer> entry : lru.entrySet()) {
						if(min == -1) {
							min = entry.getValue();
							minCity = entry.getKey();
						}else if(min > entry.getValue()) {
								min = Math.min(min, entry.getValue());
								minCity = entry.getKey();
						}
					}
					lru.remove(minCity);
					lru.put(cities[i], answer);
					answer += 5;
				}else {										//이미 존재하는 도시
					lru.put(cities[i], answer++);
				}
			}
		}
		return answer;
	}

}
