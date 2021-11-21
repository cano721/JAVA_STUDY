package twopoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ2230_수고르기 {
	
	static int n,m;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(arr);
		
		int start = 0;
		int end = 0;
		int minus = 0;
		int min = Integer.MAX_VALUE;
		
		while(end < n) {
			
			minus = Math.abs(arr[start]-arr[end]);
			
			//1. m이상 일때 최소 값 비교
			if(minus > m) {
				min = Math.min(minus, min);
				start++;
			}
			//m일때가 가장 작은 경우 임으로 바로 종료
			else if(minus == m) {
				min = minus;
				break;
			}
			//3. m이하 일때 end값 한칸씩 옮기기
			else {
				end++;
			}
			
		}
		System.out.println(min);
		
	}

}
