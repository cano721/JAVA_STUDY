package BOJ;

import java.util.*;
import java.io.*;
import java.math.BigDecimal;


public class Main{
	
	public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
     
        int N = Integer.parseInt(br.readLine());
        
        int[] indegree = new int[N+1];
        int[] time = new int[N+1];
        HashSet<Integer>[] table = new HashSet[N+1];
        for(int i = 0; i <= N; i++) {
        	table[i] = new HashSet<>();
        }
        
        
        
        ArrayList<ArrayList<Integer>> link = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i <= N; i++) {
        	link.add(new ArrayList<Integer>());
        }
        
        
        
        
        for(int i = 1; i <= N; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	time[i] = Integer.parseInt(st.nextToken());
        	
        	while(true) {
        		int before = Integer.parseInt(st.nextToken());
        		
        		if(before == -1) break;
        		if(table[i].contains(before)) continue;
        		
        		indegree[i]++;
        		link.get(before).add(i);
        		
        		table[i].add(before);
        		for(int k : table[before]) {
        			table[i].add(k);
        		}
        		
        		
        	}
        }
       
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
        	if(indegree[i] == 0) q.add(i);
        }
        
        int[] sum = new int[N+1];
        while(!q.isEmpty()) {
        	int temp = q.poll();
        	
        	for(int i : link.get(temp)) {
        		indegree[i]--;
        		sum[i] = Math.max(sum[i], sum[temp]+time[temp]);
        		if(indegree[i] == 0) q.add(i);
        	}
        }
        
        for(int i = 1; i <= N; i++) {
        	bw.write((sum[i]+time[i])+"\n");
        }
        
        bw.flush();
        
	}
}