package 전체유형문제풀이;

/* 구현 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class PG67256_키패드누르기 {

	public static void main(String[] args) {
		
		int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
		String hand ="right";
		
		String result = solution(numbers,hand);
		System.out.println(result);

	}

	private static String solution(int[] numbers, String hand) {
		String answer = "";
        StringBuilder sb = new StringBuilder();
        
        int leftbtn = 10; //*
        int rightbtn = 12; //#
        
        
        for (int i : numbers) {
			//왼쪽
        	if(i == 1 ||i == 4||i == 7) {
        		sb.append("L");
        		leftbtn = i;
        	} 
        	//오른쪽
        	else if(i == 3||i == 6||i == 9) {
        		sb.append("R");
        		rightbtn = i;
        	}
        	else {
        		int distanceleft = getDistance(leftbtn , i);
        		int distanceright = getDistance(rightbtn , i);
        		
        		if(distanceleft > distanceright) {
        			sb.append("R");
        			rightbtn = i;
        		}else if(distanceleft < distanceright) {
        			sb.append("L");
        			leftbtn = i;
        		}else {
        			if(hand.equals("right")) {
        				sb.append("R");
        				rightbtn = i;
        			}else {
        				sb.append("L");
        				leftbtn = i;
        			}
        		}

        	}
        	
		}
        answer = sb.toString();
        
        return answer;
	}

	private static int getDistance(int btn, int i) {//현재위치와 이동할위치(다음이동)간의 거리차이
		
		if(btn == 0) {
			btn = 11;
		}
		
		if(i == 0) {
			i = 11;
		}
		
		int locationX = (btn-1) / 3; // x좌표구하기
		int locationY = (btn-1) % 3; // y좌표구하기
		int nextX = (i-1) / 3; // 다음x 좌표구하기
		int nextY = (i-1) % 3; // 다음y 좌표구하기
		
		
		int result = Math.abs(locationX-nextX)+Math.abs(locationY-nextY);
		
		return result;
	
	}

}
