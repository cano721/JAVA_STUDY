package Programmers;

import java.util.HashSet;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		int N = 5;
		int number = 12;
		System.out.println(solution.solution(N, number));
	}
}

class Solution {
	public int solution(int N, int number) {
		Set<Integer>[] hash = new Set[9];
		for (int i = 0; i < hash.length; i++)
			hash[i] = new HashSet<>();
		int[] base = new int[9];
		for (int i = 1, j = N; i < hash.length; i++, j = j * 10 + N) {
			base[i] = j;
			hash[i].add(j);
		}
		for (int i = 1; i < hash.length; i++) {
			for (int j = 1; j <= i; j++) {
				for (int a : hash[i]) {
					for (int b : hash[j]) {
						if (i + j < hash.length) {
							hash[i + j].add(a + b);
							hash[i + j].add(a - b);
							hash[i + j].add(b - a);
							hash[i + j].add(a * b);
							if (b > 0)
								hash[i + j].add(a / b);
							if (a > 0)
								hash[i + j].add(b / a);
						}
					}
				}
			}
		}
		for (int i = 1; i < hash.length; i++)
			if (hash[i].contains(number))
				return i;
		return -1;
	}
}