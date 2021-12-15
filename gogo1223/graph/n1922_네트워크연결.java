package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int start;
	int end;
	int weight;

	Edge(int start, int end, int weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		return weight - o.weight;
	}

}
public class n1922_네트워크연결 {
	static int n, m;
	static ArrayList<Edge> arr;
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        
        arr = new ArrayList<Edge>();
       
        for (int i = 0; i < m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int start = Integer.parseInt(st.nextToken()); 
        	int end = Integer.parseInt(st.nextToken());
        	int weight = Integer.parseInt(st.nextToken());

        	arr.add(new Edge(start, end, weight));
		}
        
        parent = new int[n+1];
        for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
        
        Collections.sort(arr);
        int answer = 0;
        for (int i = 0; i < m; i++) {
			Edge e = arr.get(i);
			if(find(e.start) != find(e.end)) {
				answer += e.weight;
				union(e.start, e.end);
			}
			
		}
        System.out.println(answer);
        

	}
	public static int find(int x) {
		if (x == parent[x]) {
			return x;
		}

		return parent[x] = find(parent[x]);
	}

	public static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y) {
			parent[y] = x;
		}
	}

}
