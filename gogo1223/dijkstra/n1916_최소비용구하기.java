package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class n1916_최소비용구하기 {
	static class Edge implements Comparable<Edge>{
    	int id, cost;

		public Edge(int id, int cost) {
			this.id = id;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}
	static int N, M;
	static ArrayList<Edge>[] dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dist = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
			dist[i] = new ArrayList<Edge>();
		}
        for (int i = 1; i <= M; i++) {
        	st = new StringTokenizer(br.readLine());
        	
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			dist[s].add(new Edge(e, cost));
		}
	}

}
