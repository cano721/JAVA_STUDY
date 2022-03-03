package Programmers;

import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	HashMap<String, Integer> hash = new HashMap<>();

	/**
	 * 캐시 크기와 들어오는 데이터들이 주어질 때, 총 실행시간을 구하는 문제
	 * 캐시 교체 알고리즘은 LRU를 사용한다.
	 * cache hit은 1의 실행시간을, cache miss는 5의 실행 시간을 가진다.
	 * 데이터는 영문자로 구성되어 있으며, 대소문자를 구분하지 않는다.
	 * @param cacheSize 캐시 크기
	 * @param cities 데이터들
	 * @return 총 실행시간
	 */
	public int solution(int cacheSize, String[] cities) {
		int answer = 0;
		for (int i = 0; i < cities.length; i++) {
			String city = cities[i].toLowerCase();
			if (hash.containsKey(city)) {
				answer += 1;
			} else {
				answer += 5;
			}
			hash.put(city, i);
			if (hash.size() > cacheSize)
				removeLRU();
		}
		return answer;
	}

	public void removeLRU() {
		String removeKey = "";
		int min = Integer.MAX_VALUE;
		for (String key : hash.keySet()) {
			if (hash.get(key) < min) {
				min = hash.get(key);
				removeKey = key;
			}
		}
		hash.remove(removeKey);
	}
}

class Country {
	String name;
	int idx;

	public Country(String name, int idx) {
		this.name = name;
		this.idx = idx;
	}
}