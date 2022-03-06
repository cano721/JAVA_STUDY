package elwlahd555.programmers;

import java.util.ArrayList;

public class 프로그래머스17680_1차_캐시 {
	public static void main(String[] args) {
		int cacheSize=3;
		String cities[]={"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
		System.out.println(solution(cacheSize, cities));
	}
    public static int solution(int cacheSize, String[] cities) {
        int answer = 0;
        ArrayList<String> cache = new ArrayList<>();
        
        if(cacheSize == 0) // 캐시크기가 0
            return cities.length * 5;
        
        for(int i=0; i<cities.length; i++) {
            cities[i] = cities[i].toUpperCase(); // 대소문자 구분X
            if(cache.contains(cities[i])) { // cache hit
                cache.remove(cities[i]);
                cache.add(cities[i]);
                answer += 1;
            }
            else { // cache miss
                if(cache.size()==cacheSize) {
                    cache.remove(0);
                    cache.add(cities[i]);
                }
                else
                    cache.add(cities[i]);
                answer += 5;
            }
        }
        return answer;
    }
}
