package twoPoinSlider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class n11728_배열합치기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 
        StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] arr1 = new int[n];
		int[] arr2 = new int[m];
		
		st = new StringTokenizer(br.readLine()); 
		for (int i = 0; i < n; i++) {
			arr1[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine()); 
		for (int i = 0; i < m; i++) {
			arr2[i] = Integer.parseInt(st.nextToken());
		}
		
		int i = 0, j = 0;
		while(true) {
			if(i == n || j == m) break;
			if(arr1[i] == arr2[j]) {
				sb.append(arr1[i++] +" "+ arr2[j++] + " ");
			}else if(arr1[i] < arr2[j]) {
				sb.append(arr1[i++] + " ");
			}else {
				sb.append(arr2[j++] + " ");
			}
		}
		if(i == n) {
			for (int k = j; k < m; k++) {
				sb.append(arr2[k]+ " ");
			}
		}
		if(j == m) {
			for (int k = i; k < n; k++) {
				sb.append(arr1[k]+ " ");
			}
		}
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
	}

}
