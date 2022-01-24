package 유형별문제풀이.twopoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2003_수들의합2 {

	static int n,m;
	static int[] arr;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = 0;
		int sum = 0;
		int doCnt = 0;
		
		//for (int i = start; i < n; i++) {
			
		while(true) {
			
			if(sum >= m) {
				sum -=arr[start];
				start++;
			}
			//모든 배열을 다 확인했으면 그만!!
			else if(end >= n) break;
			 
			else{	
				sum += arr[end];
				end++;
			}

			if(sum == m) {
				doCnt++;
			}
			
		}

		System.out.println(doCnt);
		
		
	}

}
