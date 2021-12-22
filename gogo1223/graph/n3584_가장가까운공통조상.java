package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class n3584_가장가까운공통조상 {
	static int n, m;
	static int[] parent, answer;
	static List<List<Integer>> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        answer = new int[n];
        for (int i = 0; i < n; i++) {
        	m = Integer.parseInt(br.readLine());
            list = new ArrayList<>();
            
            for (int j = 0; j <= m; j++) {
				list.add(new ArrayList<Integer>());
			}
            
            parent = new int[m+1];
            for (int j = 1; j < m; j++) {
            	st = new StringTokenizer(br.readLine());
            	int p = Integer.parseInt(st.nextToken());
            	int c = Integer.parseInt(st.nextToken());
            	
            	parent[c] = p;
            	list.get(p).add(c);
			}
            st = new StringTokenizer(br.readLine());
        	int p = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	
        	int depthP = getDepth(p);
        	int depthC = getDepth(c);
        	int same = lca(p, depthP, c, depthC);
        	answer[i] = same;
		}
        
        for (int i = 0; i < n; i++) {
			System.out.println(answer[i]);
		}

	}
	private static int lca(int p, int depthP, int c, int depthC) {
		if(depthP > depthC) {
			while(depthP != depthC) {
				depthP--;
				p = parent[p];
			}
		}else if(depthP < depthC) {
			while(depthP != depthC) {
				depthC--;
				c = parent[c];
			}
		}
		
		while(p != c) {
			p = parent[p];
			c = parent[c];
		}
		return p;
	}
	private static int getDepth(int p) {
		int ret = 0;
		int cur = p;
		while(cur != 0) {
			ret++;
			cur = parent[cur];
		}
		return ret - 1;
	}

}
