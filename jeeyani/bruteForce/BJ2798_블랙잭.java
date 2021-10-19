package bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2798_블랙잭 {

	public static void main(String[] args) throws IOException {
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		
		int n = Integer.valueOf(st.nextToken());
		int m = Integer.valueOf(st.nextToken());
		
		int[] arr = new int[n];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.valueOf(st.nextToken());
		}
		
		int result = 0;
		
		//삼중 for문을 사용하여 모든 경우를 탐색하자
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				for (int k = j+1; k < n; k++) {
					
					if(arr[i]+arr[j]+arr[k] > m) {
						continue;
					}
					else {
						result = Math.max(result, arr[i]+arr[j]+arr[k]);
					}
					
				}
			}
		}
		System.out.println(result);
	}

}
