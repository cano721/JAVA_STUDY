package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Point{
	int val;
	double x;
	double y;
	public Point(int i, double x, double y) {
		this.val = i;
		this.x = x;
		this.y = y;
	}
}
class Routes implements Comparable<Routes>{
	int a;
	int b;
	double cost;
	public Routes(int a, int b, double cost) {
		this.a = a;
		this.b = b;
		this.cost = cost;
	}
	@Override
	public int compareTo(Routes o) {
		if(cost - o.cost < 0) return -1;
		return 1;
	}
}
public class n4386_별자리만들기 {
	static int n;
	static int[] parent;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		
		parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
		
		Point[] point = new Point[n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			
			point[i] = new Point(i, x, y);
		}
		
		ArrayList<Routes> disList = new ArrayList<Routes>();
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				double cost = distance(point[i], point[j]);
				disList.add(new Routes(point[i].val, point[j].val, cost));
			}
		}
		Collections.sort(disList);
		
		//크루스칼알고리즘
		double answer = 0;
		for (int i = 0; i < disList.size(); i++) {
			Routes r = disList.get(i);
			if(find(r.a) != find(r.b)) {
				answer += r.cost;
				union(r.a, r.b);
			}
		}
		
		System.out.println(answer);

	}
	public static double distance(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	}
	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x != y) {
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
