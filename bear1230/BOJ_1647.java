import java.util.*;
import java.io.*;
/*
MST - 도시 분할 계획
*/
public class Main{
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		parent = new int[n];
		for(int i=0;i<n;i++) {
			parent[i] = i;
		}
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			pq.add(new Node(a, b, c));
		}
        
		int cnt = 0;
		int result = 0;
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			if(union(node.from, node.to)) {
				cnt++;
				result += node.cost;
			}
			if(cnt == n-2) break;
		}
		System.out.println(result);
	}
    
    public static boolean union (int a, int b){		
        a = find(a);
        b = find(b);
        
        if(a == b) return false;
        parent[b] = a;
        
        return true;
	}
	public static int find (int a){
		if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
	}
    static class Node implements Comparable <Node> {
		int from, to, cost;

		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
		
	}
}

