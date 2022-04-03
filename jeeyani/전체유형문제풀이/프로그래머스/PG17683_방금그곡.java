package 전체유형문제풀이.프로그래머스;


/*
 * 구현
 * 브루트포스
 * 
 * */
public class PG17683_방금그곡 {

	public static void main(String[] args) {
		
		String m ="ABCDEFG";
		String[] musicinfos ={"12:00,12:14,HELLO,CDEFGAB","13:00,13:05,WORLD,ABCDEF"};
		
		String res = solution(m,musicinfos);

		System.out.print(res);

	}

	private static String solution(String m, String[] musicinfos) {
		String answer = "(None)";
		int playTimeMax = -1;
		
        
		//1. # 붙은 음은 다른 음으로 대체
		m = changeM(m);
		
		
		for (int i = 0; i < musicinfos.length; i++) {
			String[] tempMusic = musicinfos[i].split(",");
			
			String startTime = tempMusic[0];
			String endTime = tempMusic[1];
			String musicNm = tempMusic[2];
			String melody = changeM(tempMusic[3]);
			
			//2.재생시간 구하기(분 기준)
			int playTime = getPlayTime(startTime,endTime);
			
			//3.재생시간과 음악 시간에 따른 구분
			
			//재생시간이 음악시간보다 긴 경우
			int melodyLen = melody.length();
			String playMusic ="";
			
			if(playTime > melodyLen) {
				
				//재생시간 만큼 음악틀기
				for (int j = 0; j < playTime/melodyLen; j++) {
					playMusic += melody;
				}
				
				playMusic += melody.substring(0, playTime%melodyLen);
				
			}
			//재생시간이 음악시간보다 짧거나 같은 경우, 재생시간만큼만 재생
			else {
				playMusic = melody.substring(0, playTime);
			}
			
			/*
			4.
			조건이 일치하는 음악이 여러 개일 때에는 라디오에서 재생된 시간이 제일 긴 음악 제목을 반환
			재생된 시간도 같을 경우 먼저 입력된 음악 제목을 반환한다.
			조건이 일치하는 음악이 없을 때에는 “(None)”을 반환
			*/
			
			if(playMusic.contains(m) && playTimeMax < playTime) {
				answer = musicNm;
				playTimeMax = playTime;
			}
		}
	
       // return (playTimeMax != -1)? answer : "(None)";
		return answer;
	}

	private static String changeM(String m) {

		m = m.replaceAll("C#", "c");
		m = m.replaceAll("D#", "d");
		m = m.replaceAll("F#", "f");
		m = m.replaceAll("A#", "a");
		m = m.replaceAll("G#", "g");
		
		return m;
		
	}

	private static int getPlayTime(String startTime, String endTime) {
		
		String[] s = startTime.split(":");
		String[] e = endTime.split(":");
		
		int start = Integer.parseInt(s[0])*60 + Integer.parseInt(s[1]);
		int end = Integer.parseInt(e[0])*60 + Integer.parseInt(e[1]);
		
		return end-start;
	}

	

}
