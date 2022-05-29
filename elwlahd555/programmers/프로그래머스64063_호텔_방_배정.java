package elwlahd555.programmers;

import java.util.HashMap;
import java.util.Map;

public class 프로그래머스64063_호텔_방_배정 {
	public static void main(String[] args) {
		int k = 10;
		long room_number[] = { 1, 3, 4, 1, 3, 1 };
		System.out.println(solution(k, room_number));
	}

	private static Map<Long, Long> map = new HashMap<>();

	public static long[] solution(long k, long[] room_number) {
		int n = room_number.length;
		long[] answer = new long[n];

		for (int i = 0; i < n; i++) {
			answer[i] = findEmptyRoom(room_number[i]);
		}

		return answer;
	}

	private static long findEmptyRoom(long room) {
		if (!map.containsKey(room)) {
			map.put(room, room + 1);
			return room;
		}

		long nextRoom = map.get(room);
		long emptyRoom = findEmptyRoom(nextRoom);
		map.put(room, emptyRoom);
		return emptyRoom;
	}
}