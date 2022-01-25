package 유형별문제풀이.bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ1476_날짜계산 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		String output ="1 1 1";
		int[] arr = {1,1,1};
		int answer = 1;
		
		//날짜
		while(!input.equals(output)) {
			
			arr[0]++;
			arr[1]++;
			arr[2]++;
			answer++;
			
			if(arr[0] == 16) {
				arr[0] = 1;
			}
			if(arr[1] == 29) {
				arr[1] = 1;
			}
			if(arr[2] == 20) {
				arr[2] = 1;
			}
			
			output = String.valueOf(arr[0])+" "+String.valueOf(arr[1])+" "+String.valueOf(arr[2]);
		}

		System.out.print(answer);
		
	}

}
