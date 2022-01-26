class PG_song {
    public String solution(String m, String[] musicinfos) {
        String answer = "";
        		
		m = convert(m);
        answer = "(None)";
        int memory = 0;
    	for(int i=0; i<musicinfos.length; i++) { 
			String []str = musicinfos[i].split(",");
			String []start = str[0].split(":");
			String []end = str[1].split(":");
			
			String melody = convert(str[3]);
			
			int min = (Integer.parseInt(end[0])-Integer.parseInt(start[0]))*60 + (Integer.parseInt(end[1])-Integer.parseInt(start[1])); 
			
			int length = melody.length();
			StringBuilder sb = new StringBuilder();
            
			for(int j=0; j<min; j++) {
				sb.append(melody.charAt(j%length)); 
			}
			
			if(sb.toString().contains(m) && memory < min) {
				answer = str[2];	
				memory = min;
			}
		}
        return answer;
    }
    static String convert(String m) {
		m = m.replaceAll("C#", "c");
		m = m.replaceAll("D#", "d");
		m = m.replaceAll("F#", "f");
		m = m.replaceAll("G#", "g");
		m = m.replaceAll("A#", "a");
		
		return m;
	}
}