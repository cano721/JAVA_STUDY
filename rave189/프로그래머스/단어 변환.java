package Programmers;

import java.util.HashSet;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {

	HashSet<String> hash = new HashSet<>();
	int answer = Integer.MAX_VALUE;

	public int solution(String begin, String target, String[] words) {
		init(words);
		search(begin, target, 0);
		return answer == Integer.MAX_VALUE ? 0 : answer;
	}

	public void init(String[] words) {
		for (String word : words)
			hash.add(word);
	}

	public void search(String begin, String target, int count) {
		if (begin.equals(target)) {
			answer = Math.min(answer, count);
			return;
		}
		char[] word = begin.toCharArray();
		for (int i = 0; i < word.length; i++) {
			if (word[i] == target.charAt(i))
				continue;
			char tmp = word[i];
			for (char ch = 'a'; ch <= 'z'; ch++) {
				word[i] = ch;
				String str = new String(word);
				if (hash.contains(str)) {
					hash.remove(str);
					search(str, target, count + 1);
					hash.add(str);
				}
			}
			word[i] = tmp;
		}
	}
}