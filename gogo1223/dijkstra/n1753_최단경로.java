package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 참고자료 : https://subbak2.tistory.com/55
 * */
public class n1753_최단경로 {
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
    
    static int V,E,K;
    static int u,v,w;
    static int [] dist;
    static ArrayList[] adjList;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        
        // 거리배열 - 무한대 초기화
        dist = new int[V+1];
        for (int i = 1; i <= V; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
        // 인접리스트 초기화
        adjList = new ArrayList[V+1];
        for (int i = 1; i <= V; i++) {
        	adjList[i] = new ArrayList<Edge>();
		}
        // 인접리스트 입력
        for (int i = 1; i <= E; i++) {
        	st = new StringTokenizer(br.readLine());
        	u = Integer.parseInt(st.nextToken());
        	v = Integer.parseInt(st.nextToken());
        	w = Integer.parseInt(st.nextToken());
        	
        	adjList[u].add(new Edge(v,w));
		}
        //출발지 K부터 시작
        dijkstra(K);
        
        for (int i = 1; i <= V; i++) {
			if(dist[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
			}
			else {
				System.out.println(dist[i]);
			}
		}

	}
	
	static void dijkstra(int start) {
    	// 1. 출발지 비용은 0으로 하고 출발지를 PQ에 넣고 시작
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>(); 
        dist[start] = 0;
        pq.add(new Edge(start,0));
        
        while(!pq.isEmpty()) {
        	Edge now = pq.poll();
        	
        	// * 특정 목적지에 도착하는 문제였다면, 도착지 도착후 break
        	
        	// 2. 더 큰 가중치로 도착한 경우 패스
        	if (now.cost > dist[now.id]) continue;
        	
        	// 3. 현재 위치에 연결된 간선 탐색
        	int len = adjList[now.id].size();
        	for (int i = 0; i<len; i++) {
        		Edge next = (Edge) adjList[now.id].get(i);
        		
        		// 4. cost가 더 작을때만 갱신하고 PQ에 넣기
        		if (dist[next.id] > now.cost + next.cost ) {
        			dist[next.id] = now.cost + next.cost;
        			pq.add(new Edge(next.id, dist[next.id]));
        		}
        	}
        	
        }
    }

}
