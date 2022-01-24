package 유형별문제풀이.BinaraySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 
 * 
 * 
 * 
 * 
 @author Jeeyani
 */

public class BJ2031_이쿠키는달지않아 {


	static int t,n,d,k;	
	static int[] list;
	static int[] cnt;
	static int[][] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		t = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		list = new int[n];
		cnt = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			list[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(list);
		
		getEffectCnt();
		
		
		StringBuilder sb = new StringBuilder();
		sb.append(dp[k][n]);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}

	private static void getEffectCnt() {
		
		for (int i = 0; i < n; i++) {
			int left = i;
			int right = n-1;
			int time = list[i];
			
			while(left <= right) {
				
				int mid = (left+right)/2;
				
				if(list[mid] >= time+d) {
					right = mid - 1;
				}
				else {
					left = mid + 1;
				}
				
			}
			
			cnt[i] = left - i;	
		}
		
		dp = new int[k+1][n+1];
		
		for (int i = 0; i <n; i++) {
			for (int j = 1; j < k+1; j++) {
				dp[cnt[i]+i][j] = Math.max(dp[cnt[i]+i][j], dp[i][j-1]+cnt[i]);
				dp[i+1][j] = Math.max(dp[i+1][j],dp[i][j]);
			}
		}
		
	}


}
