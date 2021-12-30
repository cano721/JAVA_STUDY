package BOJ;

import java.util.*;
import java.io.*;


public class Main{	
	
	public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Integer>> link = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> link2 = new ArrayList<ArrayList<Integer>>();
        
        int[] time = new int[N+1];
        int[] indegree = new int[N+1];
        
        for(int i = 0; i <= N; i++) {
        	link.add(new ArrayList<Integer>());
        	link2.add(new ArrayList<Integer>());
        }
        
        for(int i = 1; i <= N; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	int cost = Integer.parseInt(st.nextToken());
        	time[i] = cost;
        	
        	int M = Integer.parseInt(st.nextToken());
        	indegree[i] = M;
        	
        	for(int j = 0; j < M; j++) {
        		int first = Integer.parseInt(st.nextToken());
        		link.get(first).add(i);
        		link2.get(i).add(first);
        	}
        }
        
        
        Queue<Integer> q = new LinkedList<>();
        
        for(int i = 1; i <= N; i++) {
        	if(indegree[i] == 0) {
        		q.add(i);
        	}
        }

        while(!q.isEmpty()) {
        	int now = q.poll();
        	
        	
        	
        	for(int i : link.get(now)) {
        		indegree[i]--;
        		if(indegree[i] == 0) {
        			q.add(i);
        			int temp = 0;
        			for(int from : link2.get(i)) {
        				if(temp < time[from]) temp = time[from];
        			}
        			time[i] += temp;
        		}
        	}
        }
        
        int ans = 0;
        for(int i = 1; i <= N; i++) {
        	if(ans < time[i]) ans = time[i];
        }
        
        System.out.println(ans);
        
	}
}