package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Solution s = new Solution();
		String m =
//				 "ABCDEFG";
//				"CC#BCC#BCC#BCC#B";
//		 "ABC";
				"DF";
		String[] musicinfos =
//				 { "12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF" };
//				{ "03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B" };
//		 { "12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF" };
				{ "6:20,6:50,TEST,DDF" };
		System.out.println(s.solution(m, musicinfos));
	}
}

class Solution {
	HashMap<String, Character> hash = new HashMap<>();
	String[] sharp = { "A#", "C#", "D#", "F#", "G#" };
	char[] replace = { 'X', 'Y', 'Z', 'T', 'k' };

	public String solution(String m, String[] musicinfos) {
		init();
		PriorityQueue<Music> pq = new PriorityQueue<>();
		for (int i = 0; i < musicinfos.length; i++) {
			String[] info = musicinfos[i].split(",");
			int totalPlayTime = getTotalPlayTime(info[0], info[1]);
			String soundTrack = getTotalPlaySoundTrack(totalPlayTime, getSoundTrack(info[3]));
			String hearTrack = getSoundTrack(m);
			int hearIdx = 0;
			for (char track : soundTrack.toCharArray()) {
				if (track == hearTrack.charAt(hearIdx)) {
					hearIdx++;
				} else if (track == hearTrack.charAt(0))
					hearIdx = 1;
				else
					hearIdx = 0;
				if (hearIdx == hearTrack.length()) {
					pq.add(new Music(i, totalPlayTime, info[2]));
					break;
				}
			}
		}
		return pq.isEmpty() ? "(None)" : pq.poll().name;
	}

	public void init() {
		for (int i = 0; i < sharp.length; i++)
			hash.put(sharp[i], replace[i]);
	}

	public int getTotalPlayTime(String start, String end) {
		return getTimeToInt(end) - getTimeToInt(start);
	}

	private int getTimeToInt(String time) {
		String[] split = time.split(":");
		return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
	}

	public String getSoundTrack(String sound) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < sound.length(); i++) {
			if (i + 1 < sound.length() && sound.charAt(i + 1) == '#') {
				result.append(hash.get(sound.substring(i, i + 2)));
				i++;
			} else
				result.append(sound.charAt(i));
		}
		return result.toString();
	}

	public String getTotalPlaySoundTrack(int time, String sound) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < time; i++)
			result.append(sound.charAt(i % sound.length()));
		return result.toString();
	}
}

class Music implements Comparable<Music> {
	int index, totalPlayTime;
	String name;

	public Music(int index, int totalPlayTime, String name) {
		this.index = index;
		this.totalPlayTime = totalPlayTime;
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public int getTotalPlayTime() {
		return totalPlayTime;
	}

	@Override
	public int compareTo(Music o) {
		if (totalPlayTime > o.totalPlayTime)
			return -1;
		else if (totalPlayTime == o.totalPlayTime)
			return index - o.index;
		return 1;
	}
}