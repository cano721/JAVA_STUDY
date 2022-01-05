package BOJ;

import java.util.*;
import java.io.*;

public class Main{
	
	public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        
        
        // 종유석과 석순 나누기
        int[] upside = new int[N/2];
        int[] downside = new int[N/2];
        
        for(int i = 0; i < N; i++) {
        	if(i%2 == 0) downside[i/2] = Integer.parseInt(br.readLine());
        	else upside[i/2] = Integer.parseInt(br.readLine());
        }
        
        Arrays.sort(upside);
        Arrays.sort(downside);
        
        
        int[] arr = new int[H+1];
        int min = Integer.MAX_VALUE;
        int cnt = 0;
        
        for(int i = 1; i < arr.length; i++) {
        	// i를 기준으로 구간 검색
        	// 둘 다 lowerBound로 구현
        	
        	// downside
        	arr[i] = downside.length -lowerBound(downside, i);
        	arr[i] += upside.length - lowerBound(upside, H-i+1);
        	
        	if(arr[i] < min) {
        		min = arr[i];
        		cnt = 1;
        	}else if(arr[i] == min) {
        		cnt++;
        	}
        	
        }
        
        System.out.println(min+" "+cnt);
        
	}
	static int lowerBound(int[] box, int target) {
		int left = -1;
		int right = box.length;
		
		while(left+1 < right) {
			int mid = (left + right) / 2;
			
			if(box[mid] < target) {
				left = mid;
			}else {
				right = mid;
			}
		}
		return right;
	}
}