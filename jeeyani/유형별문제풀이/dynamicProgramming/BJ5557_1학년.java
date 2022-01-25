package 유형별문제풀이.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ5557_1학년 {

	/*
	 * 참고)https://hyunah030.tistory.com/5
	 *  
	 * dp = new int[21] : 0이상 20이하의 중간 수만  허용하기 때문
	 * 
	 * 11
	 * 8 3 2 4 8 7 2 4 0 8 8
	 * 
	 * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
	 * 0 0 0 0 0 0 0 0 1 0 0  0  0   0  0  0  0  0  0  0  0
	 * 
	 * 
	 * 8 (+3/-3) 일때 배열값 갱신
	 * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
	 * 0 0 0 0 0 1 0 0 0 0 0  1  0   0  0  0  0  0  0  0  0
	 * 
	 * 
	 * 1. 5(+2/-2)의 값과 11(+2/-2)의 값 갱신
	 * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
	 * 0 0 0 1 0 0 0 1 0 1 0  0  0   1  0  0  0  0  0  0  0
	 * 
	 */
	
	
	
	static int n;
	static int[] arr;
	static long[] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < n-2; i++) {
			//dp초기화
			if(i==0) {
				dp = new long[21];
				dp[arr[0]] = 1;
			}
			
			dp = getSum(i+1);
		}
		
		System.out.println(dp[arr[n-1]]);
		
	}

	private static long[] getSum(int x) {
		
		long[] result = new long[21];
		for (int j = 0; j < 21; j++) {
			if(dp[j]==0) continue;
			
			else {
				if(j-arr[x] >= 0 && j-arr[x] <= 20) {
					result[j-arr[x]] += dp[j];
				}
				
				if(j+arr[x] >= 0 && j+arr[x] <= 20) {
					result[j+arr[x]] += dp[j];
				}
			}
		}
		
		return result;
	}

}
