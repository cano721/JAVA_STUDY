package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class n2011_암호코드 {
    private static Map<String, Integer> memory = new HashMap<>();
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 

		String n = st.nextToken();
		int answer = fn_code(n);
		System.out.println(answer);
	}

	private static int fn_code(String n) {
		if(n.length() == 1) {
			if(n.equals("0")) return 0;
			else return 1;
		}
		if(n.length() == 2) {
			switch(n.charAt(0)) {
			case '0' : return 0;
			case '1' : 
				if(n.charAt(1) == '0') return 1;
				else return 2;
			case '2' : 
				if(n.charAt(1) > '6') return 1;
				else if(n.charAt(1) == '0') return 1;
				else return 2;
			default : 
				if(n.charAt(1) == '0') return 0;
				else return 1;
			}
		}
		if(memory.containsKey(n)) return memory.get(n);
		
		int answer = 0;
		
        if(Integer.parseInt(n.substring(0, 1)) > 0) {
			answer += fn_code(n.substring(1));
			if(Integer.parseInt(n.substring(0, 2)) <= 26) {
				answer += fn_code(n.substring(2));
			}
		}
		
		answer %= 1000000;
		memory.put(n, answer);
		
		return answer;
		
	}

}
