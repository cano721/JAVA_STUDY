import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n6603_로또 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		int T;
		//테스트 개수
		T = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[T];
		String temp;
		
		for (int i = 0; i < T; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int answer = solution(arr);
		System.out.println(answer);

	}

	private static int solution(int[] arr) {
		// TODO Auto-generated method stub
		return 0;
	}

}
