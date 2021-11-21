package twoPoinSlider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class n2230_수고르기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine()); 
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		int start = 0, end = 0, diff = Integer.MAX_VALUE;
		
		while(true) {
			if(end >= n || start > end) break;
			if(arr[end] - arr[start] >= m) {
				diff = Math.min(arr[end] - arr[start], diff);
				start++;
			}else {
				end++;
			}
		}
		System.out.println(diff);

	}

}
