package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	
	/**
	 * 총 플레이 시간, 광고 시간 그리고 사용자들의 시청 시간이 주어진다.
	 * 광고를 최대한 많은 사람들이 보는게 좋으므로, 사용자들의 시청 시간이 가장 많이 겹치는 부분에 광고를 삽입 하려고 한다.
	 * 광고를 넣어야 하는 시작 위치를 구하는 문제
	 * 넣을 수 있는 위치가 여러 군데 있다면 시작 시간이 가장 빠른 것을 출력한다.
	 * 시작 시간 <= x < 끝나는 시간이다.
	 * 끝나는 시간은 다 보지 않은 것이므로 포함하지 않는 것 같다.
	 * 사용자의 수를 더하다보면 int를 초과하는 것 같다.
	 * @param play_time 총 플레이 시간
	 * @param adv_time 광고 시간
	 * @param logs 사용자들의 시청 시간
	 * @return 광고를 넣기 가장 좋은 시작 시간
	 */
	public String solution(String play_time, String adv_time, String[] logs) {
		int total = getTimeToSecondTime(play_time);
		int advTime = getTimeToSecondTime(adv_time);
		int[] arr = new int[total + 1];
		for (int i = 0; i < logs.length; i++) {
			String[] split = logs[i].split("-");
			int start = getTimeToSecondTime(split[0]);
			int end = getTimeToSecondTime(split[1]);
			for (int j = start; j < end; j++)
				arr[j]++;
		}
		int left = 0, right = advTime, answer = 0;
		long max = 0;
		for (int i = left; i < right; i++)
			max += arr[i];
		long sum = max;
		while (right < arr.length) {
			sum -= arr[left++];
			sum += arr[right++];
			if (sum > max) {
				max = sum;
				answer = left;
			}
		}
		return secondTimeToStringTime(answer);
	}

	public int getTimeToSecondTime(String str) {
		String[] split = str.split(":");
		int time = Integer.parseInt(split[0]) * 60;
		time = (time + Integer.parseInt(split[1])) * 60;
		time += Integer.parseInt(split[2]);
		return time;
	}

	public String secondTimeToStringTime(int time) {
		int second = time % 60;
		time /= 60;
		int minute = time % 60;
		time /= 60;
		int hour = time;
		return String.format("%02d:%02d:%02d", hour, minute, second);
	}
}