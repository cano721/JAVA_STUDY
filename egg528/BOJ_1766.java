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
        
        int[] indegree = new int[N+1];
        
        ArrayList<ArrayList<Integer>> link = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i <=  N; i++) {
        	link.add(new ArrayList<>());
        }
        
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	int A = Integer.parseInt(st.nextToken());
        	int B = Integer.parseInt(st.nextToken());
        	
        	indegree[B]++;
        	link.get(A).add(B);
        }
        
        
        PriorityQueue<Integer> q = new PriorityQueue<>(new Comparator<Integer>(){
        	@Override
        	public int compare(Integer o1, Integer o2) {
        		return Integer.compare(o1,  o2);
        	}			
        }) ;
        
        for(int i = 1; i <= N; i++) {
        	if(indegree[i] == 0) q.add(i);
        }
        
        
        while(!q.isEmpty()) {
        	int now = q.poll();
        	
        	bw.write(now+" ");
        	
        	for(int i : link.get(now)) {
        		indegree[i]--;
        		if(indegree[i] == 0) q.add(i);
        	}
        }
        
        bw.flush();
        bw.close();
        br.close();
        
	}
	static class Problem{
		int no;
		int priority;
		
		public Problem(int n, int p) {
			this.no = n;
			this.priority = p;
		}
	}
}