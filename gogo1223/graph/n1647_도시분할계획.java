package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Route implements Comparable<Route>{
	int a;
	int b;
	int cost;
	public Route(int a, int b, int cost) {
		this.a = a;
		this.b = b;
		this.cost = cost;
	}
	@Override
	public int compareTo(Route o) {
		return cost - o.cost;
	}
}
public class n1647_도시분할계획 {
	
	static int n, m;
	static int[] parent;
	static ArrayList<Route> routeList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        parent = new int[n+1];
        for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
        
        routeList = new ArrayList<Route>();
        for (int i = 0; i < m; i++) {
        	st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			routeList.add(new Route(a, b, cost));
		}
        Collections.sort(routeList);
        int answer = 0, lastCost = 0;
        for (int i = 0; i < routeList.size(); i++) {
			Route route = routeList.get(i);
			if(find(route.a) != find(route.b)) {
				answer += route.cost;
				union(route.a, route.b);
				
				lastCost = route.cost;
			}
		}
        System.out.println(answer-lastCost);
	}

	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x > y) {
			parent[x] = y;
		}else if(y > x) {
			parent[y] = x;
		}
		
	}

	private static int find(int x) {
		if(x == parent[x]) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
	

}
