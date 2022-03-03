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
	 * ĳ�� ũ��� ������ �����͵��� �־��� ��, �� ����ð��� ���ϴ� ����
	 * ĳ�� ��ü �˰����� LRU�� ����Ѵ�.
	 * cache hit�� 1�� ����ð���, cache miss�� 5�� ���� �ð��� ������.
	 * �����ʹ� �����ڷ� �����Ǿ� ������, ��ҹ��ڸ� �������� �ʴ´�.
	 * @param cacheSize ĳ�� ũ��
	 * @param cities �����͵�
	 * @return �� ����ð�
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