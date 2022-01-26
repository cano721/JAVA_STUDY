package programmers;

public class d220126_다트게임 {

	public static void main(String[] args) {
		String dartResult = "1D2S#10S";
		
		int answer = 0, idx = 0;
		int[] score = new int[3];
		for (int i = 0; i < dartResult.length(); i++) {
			switch(dartResult.charAt(i)) {
			case 'S': 
				break;
			case 'D': 
				score[idx-1] = (int) Math.pow(score[idx-1], 2);
				break;
			case 'T': 
				score[idx-1] = (int) Math.pow(score[idx-1], 3);
				break;
			case '*': 
				score[idx-1] *= 2;
				if(idx != 1) score[idx-2] *= 2;
				break;
			case '#' :
				score[idx-1] *= -1;
				break;
			default :
				if(dartResult.charAt(i+1) == '0') {
					score[idx] = 10;
					i++;
				}
				else score[idx] = dartResult.charAt(i) - '0';
				idx++;
			}
		}
		
		for (int i = 0; i < 3; i++) {
			answer += score[i];
		}
		System.out.println(answer);

	}

}
