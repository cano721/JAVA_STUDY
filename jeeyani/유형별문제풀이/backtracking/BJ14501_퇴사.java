package 유형별문제풀이.backtracking;

import java.util.Scanner;

public class BJ14501_퇴사 {

	static int n;
	static int[] times;
	static int[] pays;
	static int ans = Integer.MIN_VALUE;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		n= sc.nextInt();
		times = new int[n];
		pays = new int[n];
		
		for (int i = 0; i < n; i++) {
			times[i] = sc.nextInt();
			pays[i] = sc.nextInt();
		}
		
		getMAXBenefit(0,0);
		System.out.println(ans);

	}

	private static void getMAXBenefit(int idx, int pay) {
		
		/*
		 * 총 상담할 수 있는 날짜를 넘길때와 동일할때 나눠서 생각해야함
		 * 날짜를 넘기면 그 경우는 상담을 할 수 없기 때문
		 * 
		 * if(n <= idx) {
			ans = Math.max(ans, pay);
			return;
		}*/
		
		if(n == idx) {
			ans = Math.max(ans, pay);
			return;
		}
		if(n < idx) {
			return;
		}
		
		getMAXBenefit(idx+times[idx],pay+pays[idx]);
		
		/*
		 * 상담안한 날은 돈계산없이 날짜계산만 진행!!
		 */
		getMAXBenefit(idx+1,pay);
		
	}

}
