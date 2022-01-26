class Solution {
	public String solution(String m, String[] musicinfos) {
		String answer = "(None)";
		m = changeSharp(m);
		int max = 0;

		for (String music : musicinfos) {
			music = changeSharp(music);
			String[] info = musicc.split(",");
			StringBuilder sb = new StringBuilder();

			int time = (Integer.parseInt(info[1].split(":")[0]) * 60 + Integer.parseInt(info[1].split(":")[1]))
					- (Integer.parseInt(info[0].split(":")[0]) * 60 + Integer.parseInt(info[0].split(":")[1]));

			for (int i = 0; i < time; i++) {
				sb.append(info[3].charAt(i % info[3].length()));
			}

			if (sb.toString().contains(m)) {
				if (time > max) {
					answer = info[2];
					max = time;
				}
			}

		}
		return answer;
	}

	private String changeSharp(String m) {
		m = m.replaceAll("C#", "c");
		m = m.replaceAll("D#", "d");
		m = m.replaceAll("F#", "f");
		m = m.replaceAll("G#", "g");
		m = m.replaceAll("A#", "a");
		return m;
	}
}
