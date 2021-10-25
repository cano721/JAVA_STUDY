import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n2846_오르막길 {

	public static void main(String[] args) throws IOException {
		// 입력 값 셋팅 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		int N, answer = 0;
		N = Integer.parseInt(br.readLine());	//입력개수
		
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		
		for (int i = 0; i < arr.length; i++) {	//오르막길 입력
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int temp = 0;
		
		for (int j = 0; j < arr.length-1; j++) {
			if(arr[j] < arr[j+1]) {
				temp += arr[j+1] - arr[j];
			} else {
				temp = 0;
			}
			if(temp > answer) {
				answer = temp;
			}
		}
		
		System.out.println(answer);
		
	}

}
