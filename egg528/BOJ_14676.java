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
        int K = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> link = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i <= N; i++) {
        	link.add(new ArrayList<Integer>());
        }
        int[] indegree = new int[N+1];
        int[] building = new int[N+1];
        
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int before = Integer.parseInt(st.nextToken());
        	int after = Integer.parseInt(st.nextToken());
        	
        	indegree[after]++;
        	link.get(before).add(after);
        }
        
        boolean isLie = false;
        

        
        for(int i = 0; i < K; i++) {
        	st = new StringTokenizer(br.readLine());
        	int order = Integer.parseInt(st.nextToken());
        	int idx = Integer.parseInt(st.nextToken());
        	
        	// °Ç¼³
        	if(order == 1) {
        		if(indegree[idx] != 0) {
        			System.out.println("Lier!");
        			return;
        		}
        		
        		building[idx]++;
        		if(building[idx] == 1) {
        			for(int to : link.get(idx)) {
            			indegree[to]--;
            		}
        		}
        		
        		
        	// ÆÄ±«
        	}else {
        		if(building[idx] == 0) {
        			System.out.println("Lier!");
        			return;
        		}
        		
        		building[idx]--;
        		if(building[idx] == 0) {
        			for(int to : link.get(idx)) {
        				indegree[to]++;
        			}
        		}
        	}
        }
        
        
        
        System.out.println("King-God-Emperor");
	}
}