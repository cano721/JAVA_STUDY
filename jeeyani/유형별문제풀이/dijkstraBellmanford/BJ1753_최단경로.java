package 유형별문제풀이.dijkstraBellmanford;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
	int v;
	int w;
	
	public Node(int v, int w) {
		this.v = v;
		this.w = w;
	}
	
	
	@Override
	public int compareTo(Node o) {
		return w-o.w;
	}
	
}

public class BJ1753_최단경로 {

	static int v,e,k;
	static int INF = Integer.MAX_VALUE;
	static List<Node>[] list;
	static int[] dist;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(br.readLine());
		
		list = new ArrayList[v+1];
		dist = new int[v+1];
		
		for (int i = 1; i <=v; i++) {
			list[i] = new ArrayList<>();
		}
		
		//무제한 값으로 일괄 초기화
		Arrays.fill(dist, INF);
		
		//시작값의 자기자신의 경로는 0으로 초기화
		dist[k] = 0; 
		
		//각 정점간의 가중치값 초기화
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
		
			list[start].add(new Node(end, w));
		}
		
		getDist();
		
		for (int i = 1; i <=v; i++) {
			System.out.println( (dist[i] == INF)? "INF" : dist[i] );
		}
		
	}

	private static void getDist() {
		PriorityQueue<Node> q = new PriorityQueue<Node>();
		q.add(new Node(k, 0));
		
		while (!q.isEmpty()) {
			//노드의 최소값 꺼내기
			Node node = q.poll();
			int vv = node.v;
			int ww = node.w;
			
			//값이 클 경우는 배제
			if(dist[vv] < ww) {
				continue;
			}
			//각 간선마다 연결되어 있는 정점 탐색
			for (int i = 0; i < list[vv].size(); i++) {
				int v2 = list[vv].get(i).v;
				int w2 = list[vv].get(i).w + ww;
				
				//가중치가 작으면 갱신
				if(dist[v2] > w2) {
					dist[v2] = w2;
					q.add(new Node(v2, w2));
				}
				
			}
			
		}
		
	}

}
