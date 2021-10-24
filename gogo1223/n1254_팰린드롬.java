import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n1254_팰린드롬 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		String S = br.readLine();
		int answer = solution(S);
		System.out.println(answer);
		
	}

	private static int solution(String s) {
		int answer = s.length();
		String str, str2;
		StringBuffer sb;
		
		for (int i = 0; i < s.length(); i++) {
			//i부터 자른 문자열이 팰린드롬인지 확인
			str = s.substring(i);
			sb = new StringBuffer(str);		//check!)문자 뒤집을때 버퍼사용
			str2 = sb.reverse().toString();
			
			if(str.equals(str2)) {
				answer += i;
				break;
			}
		}
		return answer;
	}

}
