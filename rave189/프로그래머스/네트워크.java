package Programmers;

import java.util.HashSet;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	int[] unionFind;

	public int solution(int n, int[][] computers) {
		unionFind = new int[n];
		for (int i = 1; i < n; i++)
			unionFind[i] = i;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j || computers[i][j] == 0)
					continue;
				int parentA = find(i);
				int parentB = find(j);
				unionFind[parentB] = parentA;
			}
		}
		HashSet<Integer> hash = new HashSet<>();
		for (int i = 0; i < n; i++)
			hash.add(find(i));
		return hash.size();
	}

	public int find(int n) {
		if (n == unionFind[n])
			return n;
		return unionFind[n] = find(unionFind[n]);
	}
}