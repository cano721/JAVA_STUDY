package BOJ;

import java.util.*;
import java.io.*;


public class Main{

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        String[] NM = br.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);
        
        int[] arr = new int[N];
        for(int i = 0; i < N; i++) {
        	arr[i] = Integer.parseInt(br.readLine());
        }
        
        Arrays.sort(arr);
        
        int left = 0;
        int right = arr[N-1] - arr[0];
        int ans = 0;
        int dist = 0;
        
        while(left <= right) {
        	int mid = (left+right)/2;
        	int start = arr[0];
        	int cnt = 1;
        	
        	for(int i = 1; i < N; i++) {
        		dist = arr[i] - start;
        		if(mid <= dist) {
        			cnt++;
        			start = arr[i];
        		}
        	}
        	
        	if(M <= cnt) {
        		ans = mid;
        		left = mid+1;
        	}else {
        		right =  mid-1;
        	}
        }
        
        
        System.out.println(ans);
    }
}