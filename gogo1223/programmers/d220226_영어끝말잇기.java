package programmers;

import java.util.ArrayList;

public class d220226_영어끝말잇기 {

	public static void main(String[] args) {
		int n = 3; 
		String[] words = {"tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank"};
		int[] answer = solution(n, words);
		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
	}
	static int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < words.length; i++) {
        	if(list.contains(words[i]) || 
        			(i > 0 && words[i-1].charAt(words[i-1].length()-1) != words[i].charAt(0))) {
        		answer[0] = i % n +1;
        		answer[1] = i / n + 1;
        		break;
        	}
        	list.add(words[i]);
		}

        return answer;
    }
}
