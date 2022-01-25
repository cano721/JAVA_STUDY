import java.util.*;
import java.io.*;

/*
MST - 전력난
*/

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
		while(true) {  
			st= new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
            
			if(m==0 && n==0) break;
            
			int total = 0;
			PriorityQueue<Node> pq = new PriorityQueue<>();
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				pq.add(new Node(a, b, c));
				total += c;
			}
			
			parent = new int[m];
			for(int i=0; i<m; i++) {
				parent[i] = i;
			}
            
			int result = 0;
            
			while(!pq.isEmpty()) {
				Node node = pq.poll();
				if(union(node.from, node.to)) {
					//true = union success
					result += node.cost;
				}
			}
			System.out.println(total - result);
			
		}
		
	}
	static class Node implements Comparable<Node> {
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