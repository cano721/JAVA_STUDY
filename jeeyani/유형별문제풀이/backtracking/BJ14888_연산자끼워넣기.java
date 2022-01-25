package 유형별문제풀이.backtracking;

import java.util.Scanner;

public class BJ14888_연산자끼워넣기 {
	
	/*
	 * 
	 * 연산자 배열을 만들기
	 * - 연산자 배열을 방문해서 각 연산자를 순서대로 계산
	 * 
	 */

	static int n;
	static int[] arr;
	static int[] oper = new int[4];
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		n= sc.nextInt();
		arr = new int[n];
		
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		
		for (int i = 0; i < 4; i++) {
			oper[i] = sc.nextInt();
		}

		getMAXMIN(arr[0],1); //첫번째 숫자부터 시작
		System.out.println(max+"\n"+min);

	}

	private static void getMAXMIN(int num, int idx) {
		
		/*
		 * 모든 계산이 완료되면 종료
		 */
		if(idx == n) {
			
			max = Math.max(max, num);
			min = Math.min(min, num);
			return;
			
		}
		
		
		for (int i = 0; i < 4; i++) {
			//연산이 존재할 경우
			if(oper[i]>0) {
				
				oper[i]--; //사용한 만큼 갯수 줄이기
				
				switch(i) {
				
				case 0: getMAXMIN(num+arr[idx], idx+1); break;
				case 1: getMAXMIN(num-arr[idx], idx+1); break;
				case 2: getMAXMIN(num*arr[idx], idx+1); break;
				case 3: getMAXMIN(num/arr[idx], idx+1); break;
				
				}
				oper[i]++; //다시 원래갯수로 돌려주기
			}			
			
		}
		
	}

}
