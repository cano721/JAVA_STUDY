package BOJ;

import java.util.*;
import java.io.*;


public class Main{	
	
	public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
      
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
       
        ArrayList<ArrayList<Integer>> link = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i <= N; i++) {
        	link.add(new ArrayList<Integer>());
        }
        
        int[] indegree = new int[N+1];
        
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int K = Integer.parseInt(st.nextToken());
        	
        	int[] arr = new int[K];
        	for(int j = 0; j < arr.length; j++) {
        		arr[j] = Integer.parseInt(st.nextToken());
        	}
        	
        	for(int a = 0; a < K; a++) {
        		for(int b = a+1; b < K; b++) {
        			int before = arr[a];
        			int after = arr[b];
        			
        			link.get(before).add(after);
        			indegree[after]++;
        		}
        	}
        }
        
        Queue<Integer> q = new LinkedList<>();
        int cnt = 0;
        
        for(int i = 1; i <= N; i++) {
        	if(indegree[i] == 0) q.add(i);
        }
        
        while(!q.isEmpty()) {
        	int now = q.poll();
        	
        	bw.write(now+"\n");
        	cnt++;
        	
        	for(int to : link.get(now)) {
        		indegree[to]--;
        		if(indegree[to] == 0) {
        			q.add(to);
        		}
        	}
        }
        
        
        if(cnt != N) {
        	System.out.println(0);
        	return;
        }
        
        bw.flush();
        bw.close();
        br.close();
	}
}
