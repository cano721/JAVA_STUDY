package BOJ;

import java.util.*;
import java.io.*;


public class Main{
	static int T;
	static int N;
	
	static int[] parent;
	static int[] depth;
	static boolean[] isVisited;
	static ArrayList<ArrayList<Integer>> graph;
	
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        
        
        while(T-- > 0) {
        	int N = Integer.parseInt(br.readLine());
        	
        	graph = new ArrayList<ArrayList<Integer>>();
        	for(int i = 0; i < N; i++) {
        		graph.add(new ArrayList<Integer>());
        	}
        	
        	
        	parent = new int[N];
        	depth = new int[N];
        	isVisited = new boolean[N];
        	
        	Arrays.fill(parent, -1);
        	
        	for(int i = 0; i < N-1; i++) {
        		st = new StringTokenizer(br.readLine());
        		int p = Integer.parseInt(st.nextToken())-1;
        		int c = Integer.parseInt(st.nextToken())-1;
        		
        		graph.get(p).add(c);
        		parent[c] = p;
        	}
        	
        	
        	int root = -1;
        	for(int i = 0; i < N; i++) {
        		if(parent[i] == -1) root = i;
        	}
        	
        	isVisited[root] = true;
        	DFS(0, root);

        	
        	String[] ab = br.readLine().split(" ");
        	
        	bw.write(LCA(Integer.parseInt(ab[0])-1, Integer.parseInt(ab[1])-1)+1+"\n");
        }
        
        bw.flush();
        bw.close();
        br.close();
    }
    static int LCA(int a, int b) {
    	
    	while(depth[a] != depth[b]) {
    		if(depth[a] < depth[b]) b = parent[b];
    		else a = parent[a];
    	}
    	
    	while(a != b) {
    		a = parent[a];
    		b = parent[b];
    	}
    	
    	return a;
    }
    
    static void DFS(int dep, int Node) {
    	depth[Node] = dep;
    	
    	
    	for(int i : graph.get(Node)) {
    		if(!isVisited[i]) {
    			DFS(dep+1, i);
    			isVisited[i] = true;
    		}
    	}
    }
}