package 전체유형문제풀이.프로그래머스;

import java.util.*;

/*

[완전탐색 - dfs]

1. dfs를 통해 구할 수 있는 모든 경우의 수를 구한다. = 순열
2. 해당 줄 순서가 조건에 맞는지 확인한다.


*/

public class PG1835_단체사진찍기 {

	static HashMap<Character, Integer> friendMap;
	static int list[];
	static int answer;
	static boolean visited[];
	static String[] strData;

	public static void main(String[] args) {

		int n = 2;
		String[] data = { "N~F=0", "R~T>2" };

		int result = solution(n, data);

		System.out.println(result);

	}

	private static int solution(int n, String[] data) {
		strData = data;
		friendMap = new HashMap<>();

		friendMap.put('A', 0);
		friendMap.put('C', 1);
		friendMap.put('F', 2);
		friendMap.put('J', 3);
		friendMap.put('M', 4);
		friendMap.put('N', 5);
		friendMap.put('R', 6);
		friendMap.put('T', 7);

		answer = 0;
		list = new int[8];
		visited = new boolean[8];

		getDFS(0);

		return answer;
	}

	private static void getDFS(int num) {
		//탈출조건
		//모든 경우의 수를 확인한후, 조건에 맞는지 확인하기
		if (num == 8) {
			if (isOK()) {
				answer++;
				return;
			}
		}
		//8명 모두 확인
		for (int i = 0; i < 8; i++) {
			if (visited[i])
				continue;

			visited[i] = true;
			list[num] = i;

			getDFS(num + 1);
			visited[i] = false;
		}

	}

	private static boolean isOK() {
		
		for (String s : strData) {

			int f1 = list[friendMap.get(s.charAt(0))];
			int f2 = list[friendMap.get(s.charAt(2))];

			char sign = s.charAt(3);
			int dist = (s.charAt(4) - '0') + 1;

			int diff = Math.abs(f1 - f2); //두 친구간의 거리

			//각 두 친구사이의 거리간격에 따른 조건확인
			if (sign == '=') {

				if (diff != dist)
					return false;

			} else if (sign == '>') {
				if (diff <= dist)
					return false;
			} else {
				if (diff >= dist)
					return false;
			}
		}

		return true;

	}

}