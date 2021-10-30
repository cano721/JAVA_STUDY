package backtracking;

import java.util.Scanner;

public class BJ14501_퇴사 {

	static int n;
	static int[] times;
	static int[] pays;
	static boolean[] visisted;
	static int ans = Integer.MIN_VALUE;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		n= sc.nextInt();
		times = new int[n];
		pays = new int[n];
		visisted = new boolean[n];
		for (int i = 0; i < n; i++) {
			times[i] = sc.nextInt();
			pays[i] = sc.nextInt();
		}
		//sc.next();
		
		getMAXBenefit(0,0);
		System.out.println(ans);

	}

	private static void getMAXBenefit(int idx, int pay) {
		
		if(n <= idx+times[idx]) {
			ans = Math.max(ans, pay);
			return;
		}
		
		for (int i = idx; i < n; i++) {
			
			if(!visisted[idx]) {
				visisted[idx] = true;
				getMAXBenefit(idx+times[idx],pay+pays[idx]);
				visisted[idx] = false;
			}
			
			
		}
		
	}

}
