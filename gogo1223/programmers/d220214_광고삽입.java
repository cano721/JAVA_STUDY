package programmers;

import java.util.Arrays;

public class d220214_광고삽입 {

	public static void main(String[] args) {
		String play_time = "99:59:59";
		String adv_time = "25:00:00"; 
		String[] logs = {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"};
		System.out.println(solution(play_time, adv_time, logs));	//"01:30:59"
	}

	private static String solution(String play_time, String adv_time, String[] logs) {
		int playTime 	= getSecTime(play_time);
		int advTime 	= getSecTime(adv_time);
		int[] ad = new int[playTime + 1];
		
		for(String log : logs) {
			int start = getSecTime(log.split("-")[0]);
			int end = getSecTime(log.split("-")[1]);
			for (int i = start; i < end; i++) {
				ad[i]++;
			}
		}
		
		int startTime = 0;
        int endTime = advTime;
        long sum = 0;
        for (int i = startTime; i < endTime; i++) {
            sum += ad[i];
        }
        long max = sum;
        int answer = 0;
        while (endTime <= playTime) {
            sum -= ad[startTime];
            sum += ad[endTime];
            if(sum > max) {
                max = sum;
                answer = startTime + 1;
            }
            startTime++;
            endTime++;
        }
		String answerTime = lpad((answer / 3600), 2) + ":" + lpad((answer % 3600)/60, 2) + ":" + lpad((answer % 3600)%60, 2);
		
		return answerTime;
	}
	/* HH:MM:SS 형식의 시간을 초로 나타내기*/
	private static int getSecTime(String time) {
		String[] arr = time.split(":");
		return Integer.parseInt(arr[0])*60*60 + Integer.parseInt(arr[1])*60 + Integer.parseInt(arr[2]);
	}
	
	/*pad의 길이가 되도록 number 앞에 0 붙이는 함수*/
	private static String lpad(int number, int pad) {
		String answer = "";
		String strNum = number+"";
		for (int i = 0; i < pad - strNum.length(); i++) {
			answer += "0";
		}
		return answer + strNum;
	}
}
