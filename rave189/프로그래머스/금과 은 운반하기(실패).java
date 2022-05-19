package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
		long left = 0, right = 1000000000000L;
		while (left <= right) {
			long mid = (left + right) / 2;
			long aMax = 0, bMax = 0, aMin = 0, bMin = 0;
			for (int i = 0; i < g.length; i++) {

			}
			if (aMax >= a && bMax >= b)
				right = mid - 1;
			else
				left = mid + 1;
		}
		return left - 1;
	}
}