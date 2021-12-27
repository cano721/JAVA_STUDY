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
        int[] inDegree = new int[N+1];
        for(int i = 0; i <= N; i++) {
        	link.add(new ArrayList<Integer>());
        }
        
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	
        	inDegree[b]++;
        	link.get(a).add(b);
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
        	if(inDegree[i] == 0) q.add(i);
        }
        while(!q.isEmpty()){
        	int now = q.poll();
        	
        	bw.write(now+" ");
        	
        	for(int to : link.get(now)) {
        		inDegree[to]--;
        		if(inDegree[to] == 0) {
        			q.add(to);
        		}
        	}
        }
        bw.flush();
	}
}