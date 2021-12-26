package BOJ;

import java.util.*;
import java.io.*;


public class Main{
	static ArrayList<ArrayList<Integer>> link = new ArrayList<ArrayList<Integer>>();
	static int N;
	
	static int[] depth;
	static int[] parent;
	static boolean[] visited;
	
	static int max_depth = 0;
	static int[][] arr;
	
	
	
    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        
        for(int i = 0; i <= N; i++) {
        	link.add(new ArrayList<Integer>());
        }
        
        for(int i = 0; i < N-1; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());

        	link.get(a).add(b);
        	link.get(b).add(a);
        }
        
        depth = new int[N+1];
        parent = new int[N+1];
        
        visited = new boolean[N+1];
        visited[1] = true;
        init_depth(1, 1, 0);
        
        
        max_depth = (int) (Math.log((double)max_depth)/Math.log((double)2))+1;
        arr = new int[max_depth][N+1];
        arr[0] = parent;
        
        
        for(int i = 1; i < max_depth; i++) {
        	for(int j = 1; j <= N; j++) {
        		arr[i][j] = arr[i-1][arr[i-1][j]];
        	}
        }
        
        int M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	
        	bw.write(findCommonParent(a, b)+"\n");
        }
        
        bw.flush();
    }
    static int findCommonParent(int a, int b) {
    	if(depth[a] < depth[b]) {
    		int temp = a;
    		a = b;
    		b = temp;
    	}
    	
    	for(int i = arr.length-1; 0 <= i; i--) {
    		if(Math.pow(2, i) <= depth[a] - depth[b]) {
    			a = arr[i][a];
    		}
    	}
    	
    	if(a == b) return a;
    	
    	for(int i = arr.length-1; 0 <= i; i--) {
    		if(arr[i][a] != arr[i][b]) {
    			a = arr[i][a];
    			b = arr[i][b];
    		}
    	}
    	return arr[0][a];
    }
    static class Node{
    	int dep;
    	int node;
    	int parent;
    	
    	public Node(int d, int n, int p) {
    		this.dep = d;
    		this.node = n;
    		this.parent = p;
    	}
    }
    static int getTreeHeight(int n) {
    	return(int)Math.ceil(Math.log(n)/Math.log(2)) +1;
    }
    static void init_depth(int dep, int node, int paren) {
    	max_depth = Math.max(max_depth, dep);
    	
    	depth[node] = dep;
    	parent[node] = paren;
    	
    	for(int to : link.get(node)) {
    		if(!visited[to]) {
    			visited[to] = true;
    			init_depth(dep+1, to, node);
    		}
    	}
    }
}