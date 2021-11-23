package DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ9184_신나는함수실행 {

	//a,b,c 각 값에 대한 값을 저장
	static int[][][] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		/*
		 * 조건은 -50 ≤ a, b, c ≤ 50 이지만
		 * 
		 * w로직안에서 20 초과는 따로 계산하지 않기 때문에 최대 크기를 21로 설정
		 * 
		 */
		
		dp = new int[21][21][21];
		
		while(true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a==-1 && b==-1 && c==-1) break;
			
			int ans = w(a,b,c);
			
			System.out.println("w("+a+", "+b+", "+c+") = "+ans);
		}
	}

	private static int w(int a, int b, int c) {
		//0~20 범위 제한
		if(a>=0 && a<=20 && b>=0 && b<=20 && c>=0 && c<=20 && dp[a][b][c] != 0) return dp[a][b][c];
		
		if(a <= 0 || b <= 0 || c <= 0) {
			return 1;
		}
		
		if(a > 20 || b > 20 || c > 20) {
			return dp[20][20][20] = w(20,20,20);
		}
		
		if(a<b & b<c) {
			return dp[a][b][c] = w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c);
		}
		
		else {
			return dp[a][b][c] =  w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);
		}
	}

}
