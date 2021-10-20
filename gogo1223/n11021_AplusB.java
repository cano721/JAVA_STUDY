import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n11021_AplusB {

	public static void main(String[] args) throws IOException {
		// 입력 값 셋팅 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		int T;
		int A, B;
		//테스트 개수
		T = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[T][2];
		
		
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine()); 
			arr[i][0] = Integer.parseInt(st.nextToken()); 
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < arr.length; i++) {
			A = arr[i][0];
			B = arr[i][1];
			System.out.println("Case #" + (i+1) + ": " + (A+B));
		}
	}

}
