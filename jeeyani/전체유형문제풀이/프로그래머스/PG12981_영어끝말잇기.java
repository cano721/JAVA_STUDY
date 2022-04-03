package 전체유형문제풀이.프로그래머스;

import java.util.*;

/*
 * 
 * 1. 단어길이 만큼 계속 앞뒤의 단어를 비교하여 끝말잇기 규칙을 잘 지키고 있는지 확ㅇㄴ
 * 2. 잘 지켜고 있다면 이전 단어와 같은 단어가 언급되었는지 확인하기

 * 
 * 1) 번호: 해당 단어 위치 % n + 1
 * 2) 차례 : 해당 단어 위치 /n +1
 * 
 * */

public class PG12981_영어끝말잇기 {

	public static void main(String[] args) {

		int n = 3;
		String[] words = { "tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank" };
		int[] result = solution(n, words);

		System.out.println(result[0] + " " + result[1]);

	}

	private static int[] solution(int n, String[] words) {
		int[] answer = new int[2];

		for (int i = 1; i < words.length; i++) {
			
			//바로 앞뒤의 단어의 끝말잇기 실패할 경우
			if (!words[i - 1].substring(words[i - 1].length() - 1, words[i - 1].length()).equals(words[i].substring(0, 1))) {
				answer[0] = i % n + 1;
				answer[1] = i / n + 1;
				break;
				
			}
			
			//이전에 등장했던 단어일 경우
			else {
				for (int j = 0; j < i; j++) {
					if (words[i].equals(words[j])) {
						answer[0] = i % n + 1;
						answer[1] = i / n + 1;
						break;
					}
				}
			}
		}

		return answer;
	}

}