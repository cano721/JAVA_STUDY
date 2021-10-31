package backtracking;

import java.util.Scanner;

public class BJ1182_부분수열의합 {
	
	/*
	 * 
	 * 1. 지금의 위치를 선택 
	 * 2. 지금의 위치를 선택하지 않기
	 * 
	 * getS(idx+1, sum+arr[idx]) : 현재 위치의 원소의 합 더하기
	 * getS(idx+1, sum) : 현재 위치 빼고 더하기
	 * 
	 * 
	 * 3. 배열의 끝까지 다 확인했다면, S와 동일한지 확인
	 * 4. S = 0 인 경우도 존재함으로, 수를 하나 빼주고 출력하기
	 * 
	 */

	static int n;
	static int s;
	static int[] arr;
	static int ans=0;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		s = sc.nextInt();
		
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}

		getS(0,0);
		System.out.println((s==0)? --ans:ans);
		
	}

	private static void getS(int idx, int sum) {
		
		if(idx == n) {
			if(s==sum) ans++;
			return;
		}
		
		getS(idx+1,sum+arr[idx]);
		getS(idx+1,sum);
		
	}

}
