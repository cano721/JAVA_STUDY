package BOJ;

import java.util.*;
import java.io.*;


public class Main{	
	
	static int[] arr;
	static int answer = Integer.MAX_VALUE;
	
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        

        int T = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int duration = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[N+1];
        int[] cnt_before = new int[N+1];
        st = new StringTokenizer(br.readLine());
        
        for(int i = 1; i <= N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr);
        int ans = 0;
        
        for(int i = 1; i <= N; i++) {
        	
        	cnt_before[i] = i-upperBound(arr, duration, i)+1;
        }
        

        int[][] DP = new int[K+1][N+1];
        
        for(int i = 1; i <= K; i++) {
        	for(int j = 1; j <= N; j++) {
        		int t = cnt_before[j];
        		DP[i][j] = Math.max(DP[i][j-1], t + DP[i-1][j-t]);
        	}
        }
        
        System.out.println(DP[K][N]);
    }
    static int upperBound(int[] arr, int duration, int idx) {
    	int left = 0;
    	int right = idx;
    	int target = arr[idx]-duration;
    	
    	while(left+1 < right) {
    		int mid = (left+right)/2;
    		
    		if(arr[mid] <= target) {
    			left = mid;
    		}else {
    			right = mid;
    		}
    	}
    	return right;
    }
}