package programmers;

import java.util.Arrays;

public class d220309_기능개발 {

	public static void main(String[] args) {
		int[] progresses = {93, 30, 55}; 
		int[] speeds = {1, 30, 5};
		int[] answer = solution(progresses, speeds);	//[2, 1]
		for (int i = 0; i < answer.length; i++) {
			System.out.println(answer[i]);
		}
	}

	private static int[] solution(int[] progresses, int[] speeds) {
		int[] answer = new int[100];
		int cnt = 0;
		
		for(int i=0; i<progresses.length; i++) {
            while(progresses[i] + (cnt*speeds[i]) < 100) {
            	cnt++;
            }
            answer[cnt]++;
        }
		return Arrays.stream(answer).filter(i -> i != 0).toArray();
	}

}
