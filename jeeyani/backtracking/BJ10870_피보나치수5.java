package backtracking;

import java.util.Scanner;

public class BJ10870_피보나치수5 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int result = fibo(n);
		System.out.println(result);

	}

	private static int fibo(int n) {
		
		if(n==0) return 0;
		if(n ==1) return 1;
		
		return fibo(n-1)+fibo(n-2);
	}

}
