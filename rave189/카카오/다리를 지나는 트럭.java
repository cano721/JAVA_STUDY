package Programmers;

import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();

	}
}

class Solution {
	public int solution(int bridge_length, int weight, int[] truck_weights) {
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < bridge_length; i++)
			q.add(0);
		int weightSum = 0;
		int answer = 0;
		for (int i = 0; i < truck_weights.length; answer++) {
			if (q.size() == bridge_length)
				weightSum -= q.poll();
			if (weightSum + truck_weights[i] <= weight) {
				weightSum += truck_weights[i];
				q.add(truck_weights[i++]);
			} else
				q.add(0);
		}
		for (; !q.isEmpty(); answer++)
			q.poll();
		return answer;
	}
}