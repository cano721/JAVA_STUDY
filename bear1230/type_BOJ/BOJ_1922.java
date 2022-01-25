import java.util.*;
import java.io.*;

/*
백준 1922 MST
*/
public class Main {
	static class Node implements Comparable<Node> {
		int from, to, cost;
		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost-o.cost;
		}
	}
	static int n;
	static int m;
	static int[] parent;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		parent = new int[n+1];
		for (int i=1; i<=n; i++) {
			parent[i] = i;
		}
        
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			pq.offer(new Node(a, b, c));
		}
		int ans = 0;
		int j = 0;
        
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			if (node.from == node.to) continue;
			if (find(node.from) != find(node.to)) {
				j++;
				union(node.from, node.to);
				ans += node.cost;
			}
		}
		System.out.println(ans);
	}
	
	static int find(int a) {
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
	}
	
	static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        
        if(a == b) return false;
        parent[b] = a;
        
        return true;


    }
}
