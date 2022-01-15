package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		int len = str.length();
		int result = 0;

		for(int i = 0; i < len; i++) {
			String subStr = str.substring(i, len);
			result = Math.max(result, getMaxBubunLength(subStr));
		}
		
		System.out.print(result);
	}
	

	static int getMaxBubunLength(String str) {
		int j = 0; 
		int n = str.length(), max = 0; 
		int pi[] = new int[n];
		
		for(int i = 1; i < n; i++) { 
			while(j > 0 && str.charAt(i) != str.charAt(j)) {
				j = pi[j - 1];
			}
			
			if(str.charAt(i) == str.charAt(j)) {
				pi[i] = ++j;
				max = Math.max(max, pi[i]);
			}
		}
		
		return max;
	}
}