package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();

	}
}

class Solution {
	public int solution(int[] citations) {
		int answer = 0;
		int[] arr = new int[10001];
		for (int v : citations)
			arr[v]++;
		for (int i = arr.length - 1; i > 0; i--)
			arr[i - 1] += arr[i];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] >= i)
				answer = i;
		}
		return answer;
	}
}