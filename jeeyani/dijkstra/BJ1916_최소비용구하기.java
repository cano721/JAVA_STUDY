package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class BusNode implements Comparable<BusNode>{
	int vertex;
	int cost;
	
	public BusNode(int vertex, int cost) {
		this.vertex = vertex;
		this.cost = cost;
	}
	
	
	@Override
	public int compareTo(BusNode o) {
		return cost-o.cost;
	}
	
}

public class BJ1916_최소비용구하기 {

	static int n,m,start,end;
	static int INF = Integer.MAX_VALUE;
	static List<BusNode>[] list;
	static int[] dist;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		
		list = new ArrayList[n+1];
		dist = new int[n+1];
		
		for (int i = 1; i <=n; i++) {
			list[i] = new ArrayList<>();
		}
		
		//무제한 값으로 일괄 초기화
		Arrays.fill(dist, INF);
		
		//각 도시간의 비용 초기화
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int startN = Integer.parseInt(st.nextToken());
			int endN = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
		
			list[startN].add(new BusNode(endN, cost));
		}
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		
		//시작값의 자기자신의 경로는 0으로 초기화
		dist[start] = 0; 
		
		getDist();
		
		System.out.println(dist[end]);
	}

	private static void getDist() {
		PriorityQueue<BusNode> q = new PriorityQueue<BusNode>();
		q.add(new BusNode(start, 0));
		
		while (!q.isEmpty()) {
			//노드의 최소값 꺼내기
			BusNode node = q.poll();
			int vv = node.vertex;
			int cc = node.cost;
			
			//비용이 클 경우는 배제
			if(dist[vv] < cc) {
				continue;
			}
			//각 도시마다 연결되어 있는 도시 탐색
			for (int i = 0; i < list[vv].size(); i++) {
				int v2 = list[vv].get(i).vertex;
				int c2 = list[vv].get(i).cost + cc;
				
				//비용이 작으면 갱신
				if(dist[v2] > c2) {
					dist[v2] = c2;
					q.add(new BusNode(v2, c2));
				}
				
			}
			
		}
		
	}

}
