import java.util.*;
import java.io.*;

/*
  MST(kruskal) - 별자리 만들기 
 */
public class Main {
	static int[] parent;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
        
		Point[] points = new Point[n];
		parent = new int[n];
        
		for(int i = 0 ; i < n; i++) {
			parent[i] = i;
		}
        
		PriorityQueue<Node> pq = new PriorityQueue<>();
        
		for(int i = 0 ; i < n ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			points[i] = new Point(Float.parseFloat(st.nextToken()), Float.parseFloat(st.nextToken()));

			for(int j = 0 ; j < i ; j++) {
				float k = distance(points[i].x, points[i].y, points[j].x, points[j].y);
				pq.offer(new Node(i, j, k));
			}
		}
        
		float ans = 0f;
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			if(find(node.from) == find(node.to)) continue;
			union(node.from , node.to);
			ans += node.cost;
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
    
	static float distance(float x, float y, float x2, float y2) {
		return (float) Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2));
	}
    
	static class Point{
		float x;
		float y;
		Point(float x, float y){
			this.x = x;
			this.y = y;
		}
	}
	static class Node implements Comparable<Node>{
		int from;
		int to;
		float cost;
		Node(int from, int to, float cost){
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			if (this.cost < o.cost) return -1;
            return 1;
		}
	}

}