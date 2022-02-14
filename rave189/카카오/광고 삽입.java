package Programmers;

public class Main {

	public static void main(String[] args) {
		Solution solution = new Solution();
	}
}

class Solution {
	
	/**
	 * �� �÷��� �ð�, ���� �ð� �׸��� ����ڵ��� ��û �ð��� �־�����.
	 * ���� �ִ��� ���� ������� ���°� �����Ƿ�, ����ڵ��� ��û �ð��� ���� ���� ��ġ�� �κп� ���� ���� �Ϸ��� �Ѵ�.
	 * ���� �־�� �ϴ� ���� ��ġ�� ���ϴ� ����
	 * ���� �� �ִ� ��ġ�� ���� ���� �ִٸ� ���� �ð��� ���� ���� ���� ����Ѵ�.
	 * ���� �ð� <= x < ������ �ð��̴�.
	 * ������ �ð��� �� ���� ���� ���̹Ƿ� �������� �ʴ� �� ����.
	 * ������� ���� ���ϴٺ��� int�� �ʰ��ϴ� �� ����.
	 * @param play_time �� �÷��� �ð�
	 * @param adv_time ���� �ð�
	 * @param logs ����ڵ��� ��û �ð�
	 * @return ���� �ֱ� ���� ���� ���� �ð�
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