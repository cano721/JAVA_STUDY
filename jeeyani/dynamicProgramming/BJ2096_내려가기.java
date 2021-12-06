package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * https://mygumi.tistory.com/143
 * 
 * dp[n][3] : n번째 줄에 1,2,3칸
 * 
 * dp[0][0] : 첫번째줄의 첫번째칸
 * dp[0][1] : 첫번째줄의 두번째칸
 * dp[0][2] : 첫번째줄의 세번째칸
 * 
 * <<최대값>>
 * 첫 번째 칸이 내려갈 수 있는 경우
 * dp[n][0] = max(dp[n-1][0],dp[n-1][1])
 * 
 * 두 번째 칸이 내려갈 수 있는 경우
 * dp[n][1] = max(dp[n-1][0],dp[n-1][1],dp[n-1][2])
 * 
 * 세 번째 칸이 내려갈 수 있는 경우
 * dp[n][2] = max(dp[n-1][1],dp[n-1][2])
 * 
 */

public class BJ2096_내려가기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		int[] maxDP = new int[3];
		int[] minDP = new int[3];
		int[] map = new int[3];
		
		
		int[] temp = new int[3];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				map[j] = Integer.parseInt(st.nextToken());
			}
			
			//초기값 설정
			// 첫번째 값 넣어주기
			if(i==0) {
				maxDP[0] = map[0];
				maxDP[1] = map[1];
				maxDP[2] = map[2];
				
				minDP[0] = map[0];
				minDP[1] = map[1];
				minDP[2] = map[2];
				
				continue;
			}
			
			//1. 최대값 구하기
			temp[0] = Math.max(maxDP[0], maxDP[1]) + map[0];
			temp[1] = Math.max(maxDP[0], Math.max(maxDP[1], maxDP[2])) + map[1];
			temp[2] = Math.max(maxDP[1], maxDP[2]) + map[2];
			
			maxDP[0] = temp[0];
			maxDP[1] = temp[1];
			maxDP[2] = temp[2];
			
			//2. 최소값 구하기
			temp[0] = Math.min(minDP[0], minDP[1]) + map[0];
			temp[1] = Math.min(minDP[0], Math.min(minDP[1], minDP[2])) + map[1];
			temp[2] = Math.min(minDP[1], minDP[2]) + map[2];
			
			minDP[0] = temp[0];
			minDP[1] = temp[1];
			minDP[2] = temp[2];
			
		}
		
		int max = 0;
		int min = Integer.MAX_VALUE;
		
		for(int k : maxDP) {
			max = Math.max(max, k);
		}
		
		for(int k : minDP) {
			min = Math.min(min, k);
		}
		
		System.out.println(max+" "+min);

	}

}
