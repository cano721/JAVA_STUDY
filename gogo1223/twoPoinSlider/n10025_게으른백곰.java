package twoPoinSlider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n10025_게으른백곰 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] arr = new int[1000001];
		
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine()); 
			int g = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			arr[x] = g;
		}
		
		int sum = 0;
		int answer = 0;
		for(int i = 0; i < arr.length; i++) {
			if(i >= 2*k + 1) sum -= arr[i-(2*k+1)];
			sum += arr[i];
			answer = Math.max(answer, sum);
		}
		System.out.println(answer);

	}

}
