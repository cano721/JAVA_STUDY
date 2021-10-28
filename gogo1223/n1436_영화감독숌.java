import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n1436_영화감독숌 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	
		int n = Integer.parseInt(br.readLine());
		
		System.out.println(solution(n));
	}

	private static int solution(int n) {
		int chk = 0;
		int answer = 666;
		
		while(true) {
			if(String.valueOf(answer).contains("666")) {
				chk++;
				if(chk == n) return answer;
			}
			answer++;
		}
	}

}
