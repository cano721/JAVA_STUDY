package 유형별문제풀이.twopoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1806_부분합 {

	static int n,s;
	static int[] arr;
	static int len = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int start = 0;
		int end = 0;
		int sum = 0;
		int lenCnt = 0;
		for (int i = start; i < n; i++) {
			
			while(sum < s && end < n) {
				sum += arr[end];
				end++;
				lenCnt++;
			}
			
			
			if(sum >= s) {
				len = Math.min(len, lenCnt);
			}
			
			sum -=arr[start];
			start++;
			lenCnt--;
			
		}
		//합을 구할 수 없으면 0출력!!!! =>빼먹지마 ㅠ
		if(len == Integer.MAX_VALUE) {
			System.out.println(0);
		}else {
			System.out.println(len);
		}
		
	}

}
