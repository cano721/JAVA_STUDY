package twopoint;

import java.io.*;
import java.util.*;

public class BJ2470_두용액 {

	static int n;
	static int[] arr;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		//**정렬
		Arrays.sort(arr);
		
		//양쪽부터 포인터를 옮겨가며 비교
		int start = 0;
		int end = n-1;
		int min = Integer.MAX_VALUE;
		int[] ans = new int[2];
		
		while(start < end) {
			int sum = arr[start]+arr[end];
			
			if(min > Math.abs(sum)) {
				min = Math.abs(sum);
				
				ans[0] = arr[start];
				ans[1] = arr[end];
				
				//0이면 제일 작은 수 임으로 종료
				if(sum==0) break;
			}
			
			if(sum < 0) start++;
			else end--;
		}
		
		System.out.print(ans[0]+" "+ans[1]);
		
	}

}
