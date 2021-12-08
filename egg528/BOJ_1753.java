package BOJ;

import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		String[] VE = br.readLine().split(" ");
		int V = Integer.parseInt(VE[0]);
		int E = Integer.parseInt(VE[1]);
		int S = Integer.parseInt(br.readLine());
		
		ArrayList<ArrayList<Edge>> arr = new ArrayList<ArrayList<Edge>>();
		for(int i = 0; i <= V; i++) {
			arr.add(new ArrayList<Edge>());
		}
		
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			
			arr.get(a).add(new Edge(b, w));
		}
		
		int[] len = new int[V+1];
		boolean[] visited = new boolean[V+1];
		
		Arrays.fill(len, 100000000);
		len[S] = 0;
		
		
		for(int i = 0; i < V; i++) {
			int idx = getSmallestIdx(len, visited);
			
			if(idx == -1) break;
			
			visited[idx] = true;
			
			for(Edge edge : arr.get(idx)) {
				if(len[idx]+edge.weight < len[edge.to]) {
					len[edge.to] = len[idx]+edge.weight;
				}
			}
		}
		
		for(int i = 1; i <= V; i++) {
			if(len[i] == 100000000) System.out.println("INF");
			else System.out.println(len[i]);
		}
		
		
		
	}
	static int getSmallestIdx(int[] len, boolean[] visited) {
		int min = 100000000;
		int idx = -1;
		
		for(int i = 1; i < len.length; i++) {
			if(!visited[i] && len[i] < min) {
				min = len[i];
				idx = i;
			}
		}
		
		return idx;
	}
	
	
	static class Edge{
		int to;
		int weight;
		
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
}	