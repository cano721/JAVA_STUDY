import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n1476_날짜계산 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		int E, S, M;
		//테스트 개수
		E = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int answer = solution(E, S, M);
		System.out.println(answer);
	}

	private static int solution(int e, int s, int m) {
		int answer = 1;
		int a = 1, b = 1, c = 1;
		while(true) {
			if(a==e && b==s && c==m) {
				return answer;
			}
			answer++;
			a++; b++; c++;
			if(a > 15) a = 1;
			if(b > 28) b = 1;
			if(c > 19) c = 1;
		}
	}

}
