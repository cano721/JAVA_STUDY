package BOJ;

import java.util.*;
import java.io.*;


public class Main{	
    static long N;
    static int K, Q;
	
	public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
      
        N = Long.parseLong(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        

        for(int i = 0; i < Q; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	long a = Long.parseLong(st.nextToken());
        	long b = Long.parseLong(st.nextToken());
        	
        	if(K == 1) {
        		long dist = a < b ? b-a : a-b;
        		bw.write(dist+"\n");
        	}else {
        		bw.write(LCA(a, b)+"\n");
        	}
        	
        }
        
        bw.flush();
        
    }  
	static long LCA(long Node1, long Node2) {
		long depth1 = getDepth(Node1);
		long depth2 = getDepth(Node2);
		long sum = Math.abs(depth2 - depth1);
		
				
		if(depth1 < depth2) {
			long diff = depth2 - depth1;
			for(int i = 0; i < diff; i++) {
				Node2 = getParent(Node2);
			}
			
		}else if(depth2 < depth1) {
			long diff = depth1 - depth2;
			for(int i = 0; i < diff; i++) {
				Node1 = getParent(Node1);
			}
		}
		
		
		while(Node1 != Node2) {
			sum+=2;
			Node1 = getParent(Node1);
			Node2 = getParent(Node2);
		}
		return sum;
	}
	
	static long getDepth(long Node) {
		long sum = 0;
		long i = 0;
		while(true) {
			sum += Math.pow(K, i);
			if(Node <= sum) break;
			i++;
		}
		
		return i;
	}
	static long getParent(long Node) {
		return  (Node+(K-2))/K;
	}
}