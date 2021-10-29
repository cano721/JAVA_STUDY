import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n2231_분해합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		
		int N = Integer.parseInt(st.nextToken());
		
		System.out.println(solution(N));
	}

	private static int solution(int n) {
		int answer = 0;
		String str;
		int temp;
		for (int i = n; i > 0; i--) {
			str = i+"";
			temp = i;
			for (int j = 0; j < str.length(); j++) {
				temp += Integer.parseInt(str.substring(j, j+1));
			}
			if(temp == n) {
				answer = i;
			}
		}
		return answer;
	}

}
