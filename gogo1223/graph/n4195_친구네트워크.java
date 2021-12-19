package graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class n4195_친구네트워크 {
	static int n;
	static int[] parent, level;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
			int m = Integer.parseInt(br.readLine());
			parent = new int[m * 2];
            level = new int[m * 2];
            for (int k = 0; k < m * 2; k++) {
                parent[k] = k;
                level[k] = 1;
            }
			int idx = 0;
			Map<String, Integer> map = new HashMap<>();
			for (int j = 0; j < m; j++) {
				st = new StringTokenizer(br.readLine());
				String x = st.nextToken();
				String y = st.nextToken();
				
				if(!map.containsKey(x)) {
					map.put(x, idx++);
				}
				if(!map.containsKey(y)) {
					map.put(y, idx++);
				}
				sb.append(union(map.get(x), map.get(y)) + "\n");
				
			}
		}
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

	}
	public static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
 
        return parent[x] = find(parent[x]);
    }
	private static int union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x != y) {
			parent[y] = x;
			level[x] += level[y];			
		}
		return level[x];
	}

}
