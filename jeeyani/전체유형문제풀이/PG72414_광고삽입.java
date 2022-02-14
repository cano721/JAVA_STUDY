package 전체유형문제풀이;

import java.util.*;

/*
 * 
 * [구간합/누적합/슬라이딩윈도우]
 * 
 * 1. 최대 플레이 시간은 100시간 = 360000초이다
 * ad[360000]
 * 
 * ad[i] = n : i시간에 시청하고 있는 시청자의 수 n명
 * 
 * 2. 이중 포문시  O(N*M)으로 시간초과
 * 
 * 3. adv_time은 고정적인 시간임으로 누적합과 슬라이딩 윈도우 방식을 사용해서 풀이
 * 
 * https://loosie.tistory.com/169
 * 
 * */

public class PG72414_광고삽입 {

	public static void main(String[] args) {

		String play_time = "02:03:55";
		String adv_time = "00:14:15";
		String[] logs = { "01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29",
				"01:37:44-02:02:30" };

		String result = solution(play_time, adv_time, logs);

		System.out.println(result);

	}

	static int[] ad = new int[360000];

	private static String solution(String play_time, String adv_time, String[] logs) {
		String answer = "";

		//시청자수 초기화
		for (String logTime : logs) {

			String[] tempLog = logTime.split("-");
			int start = toSec(tempLog[0]);
			int end = toSec(tempLog[1]);

			for (int i = start; i < end; i++) {
				ad[i]++;
			}
		}

		int palyTime = toSec(play_time);
		int advTime = toSec(adv_time);

		int idx = 0;
		//int sum = 0;
		//int MaxSum = 0;
		
		long sum = 0;
		long MaxSum = 0;

		//초기화
		for (int i = 0; i < advTime; i++) {
			sum += ad[i];
		}

		MaxSum = sum;

		for (int i = advTime; i < palyTime; i++) {
			//이전 값은 빼주고 새로운 값 더해주기
			sum += ad[i] - ad[i - advTime];

			if (MaxSum < sum) {
				idx = i - advTime + 1;
				MaxSum = sum;
			}
		}

		answer = toStr(idx);

		return answer;
	}

	//문자 -> 초시간으로 변경하기
	public static int toSec(String time) {

		String[] tt = time.split(":");
		int hh = Integer.parseInt(tt[0]) * 3600;
		int mm = Integer.parseInt(tt[1]) * 60;
		int ss = Integer.parseInt(tt[2]);

		return hh + mm + ss;
	}

	//초 단위 -> 시:분:초 문자로 변경
	public static String toStr(int idxTime) {
		String res = "";

		int hh = idxTime / 3600;
		idxTime %= 3600;
		if (hh < 10)
			res += "0";
		res += hh + ":";

		int mm = idxTime / 60;
		idxTime %= 60;
		if (mm < 10)
			res += "0";
		res += mm + ":";

		int ss = idxTime;
		if (ss < 10)
			res += "0";
		res += ss;

		return res;
	}
}