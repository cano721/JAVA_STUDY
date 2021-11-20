package twoPoinSlider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n2470_두용액 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 

		int n = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		
		st = new StringTokenizer(br.readLine()); 
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		int start = 0, end = n-1;
		int diff = Integer.MAX_VALUE;
		int answer1 = 0 , answer2 = 0;
		while(start < end) {
			if(Math.abs(arr[start] + arr[end]) < diff) {
				diff = Math.abs(arr[start] + arr[end]);
				answer1 = arr[start];
				answer2 = arr[end];
			}
			if(arr[start] + arr[end] > 0) {
				end--;
			}else {
				start++;
			}
		}
		System.out.println(answer1+" "+answer2);
		
		br.close();
	}

}
