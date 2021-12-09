import java.io.*;
import java.util.*;

public class Main {

    static int INF = Integer.MAX_VALUE;
    static int n,m;
	static List<Node>[] list;
	static int[] dist;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		list = new ArrayList[n+1];
		dist = new int[n+1];
        
        Arrays.fill(dist, INF);
		
		for(int i=1; i<=n; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			list[from].add(new Node(to,cost));
		}
		
		st = new StringTokenizer(br.readLine());
		int start= Integer.parseInt(st.nextToken());
		int destination = Integer.parseInt(st.nextToken());
		
		
		dijkstra(start);
		System.out.println(dist[destination]);

		
	}
	
	static void dijkstra(int start) {
		Queue<Node> queue = new PriorityQueue<>();
		boolean[] check = new boolean[n+1];
		
		queue.add(new Node(start,0));
		dist[start] =0;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			int to = node.to;
			 
            if(check[to] == true) continue;
            check[to] = true;

			for(Node next : list[to]) {
				if(dist[next.to] > dist[to] + next.cost) {
					dist[next.to] = dist[to] + next.cost;
					queue.add(new Node(next.to, dist[next.to]));
				}
			}
		}
	}
}

class Node implements Comparable<Node>{
	int to;
	int cost;
	
	public Node(int to, int cost) {
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}
}