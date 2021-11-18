package twoPoinSlider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n1806_부분합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		
		int n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		
		st = new StringTokenizer(br.readLine()); 
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int start = 0, end = 0, sum = 0;
		int minLen = Integer.MAX_VALUE;
		
		while(true) {
			if(sum >= s) {
				minLen = Math.min(end - start, minLen);
				sum -= arr[start];
				start++;
			}
			else if(end == n) break;
			else if(sum < s) {
				sum += arr[end];
				end++;
			}
		}
		
		if(minLen == Integer.MAX_VALUE) System.out.println("0");
		else System.out.println(minLen);
	}

}
