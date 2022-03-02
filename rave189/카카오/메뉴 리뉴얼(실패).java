package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	HashSet<Character>[] hash;
	char[] arr;
	PriorityQueue<Course>[] pq;

	public String[] solution(String[] orders, int[] course) {
		init(orders);
		bruteforce(0, "");
		ArrayList<String> answer = new ArrayList<>();
		for (int i = 0; i < pq.length; i++) {
			if (!pq[i].isEmpty()) {
				int maxCnt = pq[i].peek().count;
				while (!pq[i].isEmpty() && pq[i].peek().count == maxCnt)
					answer.add(pq[i].poll().food);
			}
		}
		answer.sort((v1, v2) -> v1.compareTo(v2));
		return answer.toArray(new String[answer.size()]);
	}

	public void init(String[] orders) {
		hash = new HashSet[orders.length];
		HashSet<Character> elements = new HashSet<>();
		for (int i = 0; i < hash.length; i++) {
			hash[i] = new HashSet<>();
		}
		pq = new PriorityQueue[11];
		for (int i = 0; i < pq.length; i++) {
			pq[i] = new PriorityQueue<>((v1, v2) -> v2.count - v1.count);
		}
		for (int i = 0; i < orders.length; i++) {
			for (char ch : orders[i].toCharArray()) {
				hash[i].add(ch);
				elements.add(ch);
			}
		}
		arr = new char[elements.size()];
		int index = 0;
		for (char ch : elements) {
			arr[index++] = ch;
		}
		Arrays.sort(arr);
	}

	public void bruteforce(int cur, String result) {
		if (result.length() >= 2)
			check(result);
		for (int i = cur; i < arr.length; i++) {
			bruteforce(i + 1, result + arr[i]);
		}
	}

	public void check(String s) {
		int count = 0;
		loop: for (int i = 0; i < hash.length; i++) {
			for (char ch : s.toCharArray()) {
				if (!hash[i].contains(ch))
					continue loop;
			}
			count++;
		}
		if (count >= 2) {
			pq[s.length()].add(new Course(s, count));
		}
	}
}

class Course {
	String food;
	int count;

	public Course(String food, int count) {
		this.food = food;
		this.count = count;
	}
}