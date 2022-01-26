package programmers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class d220126_방금그곡 {

	 
	private static String solution(String m, String[] musicinfos) {
		String answer = "(None)";
		int maxPlayTime = -1;
		m = changeMelody(m);
		
		for (String musicinfo : musicinfos) {
			String[] info = musicinfo.split(",");
			String title = info[2];
			String melody = changeMelody(info[3]);
			
			String[] startTime = info[0].split(":");
			String[] endTime = info[1].split(":");
			int length = Integer.parseInt(endTime[0])*60 + Integer.parseInt(endTime[1])
				- Integer.parseInt(startTime[0])*60 - Integer.parseInt(startTime[1]);
			
			if(length <= melody.length()) {
				melody = melody.substring(0, length);
			}else {
				StringBuilder newMelody = new StringBuilder();
                
                // 나눈 몫 만큼 악보 처음부터 반복
                for (int i = 0; i < length / melody.length(); i++)
                    newMelody.append(melody);
                
              	// 나머지만큼 악보에서 잘라서 붙임
                newMelody.append(melody.substring(0, length % melody.length()));
                melody = newMelody.toString();
			}
			
			// 조건이 일치하는 음악이 여러개면
            // 재생된 시간이 제일 긴 음악 제목을 반환해야 하기 때문에
            // 조건에 멜로디 포함뿐만 아니라, 재생된 시간까지 비교
            if (melody.contains(m) && length > maxPlayTime) {
                answer = title;
                maxPlayTime = length;
            }
		}
		return answer;
	}
	static String changeMelody(String oldMelody) {
        oldMelody = oldMelody.replaceAll("C#", "H");
        oldMelody = oldMelody.replaceAll("D#", "I");
        oldMelody = oldMelody.replaceAll("F#", "J");
        oldMelody = oldMelody.replaceAll("G#", "K");
        String newMelody = oldMelody.replaceAll("A#", "L");
        
        return newMelody;
    }
	public static void main(String[] args) {
		String m = "ABCDEFG"; 
		String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
		String answer = solution(m, musicinfos);
		System.out.println(answer);
	}
}
