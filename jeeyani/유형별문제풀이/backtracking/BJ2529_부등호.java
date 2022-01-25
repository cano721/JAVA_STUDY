package 유형별문제풀이.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BJ2529_부등호 {

	static int n;
	static char[] sign;
	static boolean[] numberCHK = new boolean[10];
	static ArrayList<String> list = new ArrayList<String>();
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		sign = new char[n];
		
		for (int i = 0; i < n; i++) {
			sign[i] = sc.next().charAt(0);
		}
		
		getMaxMin(0,"");
		
		Collections.sort(list);
		System.out.println(list.get(list.size()-1));
		System.out.println(list.get(0));

	}

	private static void getMaxMin(int idx, String num) {
		
		if(idx == n+1) {
			
			list.add(num);
			return;
			
		}
		
		for (int i = 0; i < 10; i++) {
			
			if(numberCHK[i]) continue;
			
			//if(!numberCHK[i]) {
			/*
			 * idx == 0 : 첫 시작은 제외
			 * !numberCHK[i] : 사용한 숫자가 아닐경우
			 * compare(num.charAt(idx-1)-'0',i,sign[idx-1]):마지막으로 사용한 숫자, 현재비교할 값, 연산자
			 * 
			 */
			if(idx == 0 || !numberCHK[i] && compare(num.charAt(idx-1)-'0',i,sign[idx-1])) {
				
				numberCHK[i] = true;
				getMaxMin(idx+1, num+i);
				numberCHK[i] = false;
			}
			
		}
		
	}

	private static boolean compare(int first, int sec, char sign) {
		
		if(sign == '>') {
			return first > sec;
			
		}
		else return first < sec;
		
	}

}
