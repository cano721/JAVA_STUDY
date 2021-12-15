package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class n1976_여행가자 {
	static int n, m;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        
        parent = new int[n];
        for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
        
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int temp = Integer.parseInt(st.nextToken());
				if(temp == 1) union(i, j);
			}
		}
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        start = find(start);
        
        String answer = "YES";
        for (int i = 0; i < m - 1; i++) {
        	int temp = Integer.parseInt(st.nextToken()) - 1;
			if(start != find(temp)) {
				answer = "NO";
				break;
			}
		}
        System.out.println(answer);
	}

	public static void union(int i, int j) {
		i = find(i);
		j = find(j);
		
		if(i > j) {
			parent[i] = j;
		}else if(i < j) {
			parent[j] = i;
		}
	}
	
	public static int find(int x) {
		if(x == parent[x]) return x;
		
		return parent[x] = find(parent[x]);
	}

}
