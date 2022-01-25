import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		   
	       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	       
	       StringTokenizer st = new StringTokenizer(br.readLine());
	       int n = Integer.parseInt(st.nextToken());   	//얼음양동이
	       int m = Integer.parseInt(st.nextToken()); 	//좌표, 거리
	       
	       int[] Weight = new int[1000000];
	       int Max_len = 0;
	       
	       for ( int i = 0 ; i < n ; i++){	           
	           st = new StringTokenizer(br.readLine());
	           int val = Integer.parseInt(st.nextToken());
	           int idx = Integer.parseInt(st.nextToken());
	           
	           Weight[idx] = val;                               
	           Max_len  = Math.max(Max_len,idx);    
	          }
	       
	       int start=0, end=0, sum=0, ans= 0;

	       
	       while(end<= Max_len) {
	     	  sum += Weight[end];
	     	  ans = Math.max(sum, ans);
	     	  end++;
	     	  
	     	  if(end>m*2) {
	     		  sum -= Weight[start];
	     		  start+=1;	  
	     	  } 
	       }
	       System.out.println(ans);	       
	}
}