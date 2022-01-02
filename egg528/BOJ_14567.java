package BOJ;

import java.util.*;
import java.io.*;
import java.math.BigDecimal;


public class Main{
	
	public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int[] indegree = new int[N+1];
        int[] when = new int[N+1];
        
        ArrayList<ArrayList<Integer>> link = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i <= N; i++) {
        	link.add(new ArrayList<Integer>());
        }
        
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	int A = Integer.parseInt(st.nextToken());
        	int B = Integer.parseInt(st.nextToken());
        	
        	indegree[B]++;
        	link.get(A).add(B);
        }
        Queue<Node> q = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
        	if(indegree[i] == 0) q.add(new Node(i, 1));
        }
        
        
        while(!q.isEmpty()) {
        	Node now = q.poll();
        	
        	when[now.Node] = now.semester;
        	
        	for(int i : link.get(now.Node)) {
        		indegree[i]--;
        		if(indegree[i] == 0) q.add(new Node(i, now.semester+1));
        	}
        }
        
        for(int i = 1; i <= N; i++) {
        	bw.write(when[i]+" ");
        }
        bw.flush();
        
        
	}
	static class Node{
		int Node;
		int semester;
		
		public Node(int N, int s) {
			this.Node = N;
			this.semester = s;
		}
	}
}