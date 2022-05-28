package Programmers;

import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
		long k = 10;
		long[] room_number = { 1, 3, 4, 1, 3, 1 };
		for (long v : solution.solution(k, room_number)) {
			System.out.println(v);
		}
	}
}

class Solution {
	HashMap<Long, Long> hash = new HashMap<>();

	public long[] solution(long k, long[] room_number) {
		long[] answer = new long[room_number.length];
		for (int i = 0; i < room_number.length; i++) {
			long number = getRoomNumber(room_number[i]);
			hash.put(number, number + 1);
			answer[i] = number;
		}
		return answer;
	}

	public long getRoomNumber(long wanted) {
		if (!hash.containsKey(wanted))
			return wanted;
		long nextWanted = getRoomNumber(hash.get(wanted));
		hash.put(wanted, nextWanted);
		return nextWanted;
	}
}