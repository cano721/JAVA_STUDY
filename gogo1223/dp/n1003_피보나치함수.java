package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n1003_피보나치함수 {
	static int[][] fibo = new int[41][2];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 

		int n = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine()); 
			arr[i] = Integer.parseInt(st.nextToken());
		}
		fibo[0][0] = 1;
		fibo[0][1] = 0;
		fibo[1][0] = 0;
		fibo[1][1] = 1;
		for (int i = 2; i < 41; i++) {
			fibo[i][0] = fibo[i-2][0] + fibo[i-1][0];
			fibo[i][1] = fibo[i-2][1] + fibo[i-1][1];
		}
		for (int i = 0; i < n; i++) {
			System.out.println(fibo[arr[i]][0] +" "+ fibo[arr[i]][1]);
		}

	}
}
