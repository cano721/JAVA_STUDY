import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class PG_delivery {
    class Node implements Comparable<Node>{
		int idx, cost;
		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
    int []dist;
	ArrayList<Node> []arr;
    public int solution(int n, int[][] road, int k) {

		arr = new ArrayList[n+1];
		dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for(int i=0; i<n+1; i++) {
			arr[i] = new ArrayList<>();
		}
		
		for(int i=0; i<road.length; i++) {
			int start = road[i][0];
			int end = road[i][1];
			int cost = road[i][2];
			
			arr[start].add(new Node(end, cost));
			arr[end].add(new Node(start, cost));
		}
		dijkstra(road);
		int cnt = 0;
		for(int i=1; i<=n; i++) {
			if(dist[i] <= k) cnt++;
		}
        
        return cnt;
    }
    public void dijkstra(int [][]road) {
		Queue<Node> q= new LinkedList<>();
		q.offer(new Node(1, 0));
		dist[1] = 0;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			
			for(int i=0; i<arr[node.idx].size(); i++) {
				Node next = arr[node.idx].get(i);
				if(dist[node.idx] + next.cost < dist[next.idx]) {
					dist[next.idx] = dist[node.idx] + next.cost;
					q.offer(new Node(next.idx, dist[next.idx]));
				}
			}
		}
		
	}
}
