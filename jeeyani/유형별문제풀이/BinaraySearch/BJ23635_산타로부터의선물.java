package 유형별문제풀이.BinaraySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 
 * 
 * 
 * 
 * 
 @author Jeeyani
 */

public class BJ23635_산타로부터의선물 {


	static int k,n;	
	static int[] pleasure;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		pleasure = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i < n; i++) {
			pleasure[i] = Integer.parseInt(st.nextToken());
		}
		
		getSantaGift();
		
		
		StringBuilder sb = new StringBuilder();
		//sb.append();
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	private static void getSantaGift() {
		int left = 1;
		int right = n-1;
		
		while(left <= right) {
			int mid = (left+right)/2;
			
			
			
			
		}
		
		//....
		
	}


}
