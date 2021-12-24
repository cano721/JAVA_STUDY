package BOJ;

import java.util.*;
import java.io.*;


public class Main{	
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        String[] NM = br.readLine().split(" ");
        int child = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);
        
        int[] arr = new int[M];
        int[] sum = new int[M+1];
        int[] min = new int[M+1];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        	sum[i+1] = arr[i]+sum[i];
        	min[i+1] = Math.min(min[i], arr[i]);
        }
        

        int ans = Integer.MAX_VALUE;
        
        // 상품을 어디까지 사용할 것인가
        // 모든 가능성을 고려해야 한다.
        for(int i = child-1; i < M; i++) {
        	int joy_sum = sum[i+1];
        	
        	// 식의 첫 부분 sum이 고정되었으니, min(k)의 최대값 찾기
        	int left = min[i+1];
        	int right = joy_sum;
        	
        	while(left <=\ right) {
        		int mid = (left+right)/2;
        		
        		int cnt = 1;
        		int temp_sum = 0;

        		
        		for(int j = 0; j <= i; j++) {
        			if(temp_sum+arr[j] <= mid) temp_sum+=arr[j];
        			else {
        				cnt++;
        				temp_sum = 0;
        			}
        		}
        		
        		if(child < cnt) {
        			left = mid+1;
        		}else {
        			right = mid;
        		}
        	}
        	
        	
        	ans = Math.min(ans, joy_sum - (left*child));
        	if(ans == 0) {
        		System.out.println(0);
        		return;
        	}
        	
        }
        
        System.out.println(ans);
    }
}