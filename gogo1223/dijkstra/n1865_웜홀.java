package dijkstra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Road{
	int e;
	int t;
	public Road(int e, int t){
		this.e = e;
		this.t = t;
	}
	
}
public class n1865_웜홀 {
	static int TC, N, M, W;
	static int[] dist;
	static ArrayList<ArrayList<Road>> a;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringBuilder sb = new StringBuilder();
        TC = Integer.parseInt(st.nextToken());
        
        while(TC-- > 0) {
        	st = new StringTokenizer(br.readLine());
        	N = Integer.parseInt(st.nextToken());
        	M = Integer.parseInt(st.nextToken());
        	W = Integer.parseInt(st.nextToken());
        	dist = new int[N+1];
        	a = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                a.add(new ArrayList<>());
            }
            
            for (int i = 0; i < M+W; i++) {
            	st = new StringTokenizer(br.readLine());
            	int s = Integer.parseInt(st.nextToken());
            	int e = Integer.parseInt(st.nextToken());
            	int t = Integer.parseInt(st.nextToken());
            	
            	if(i < M) {
            		a.get(s).add(new Road(e, t));
            		a.get(e).add(new Road(s, t));
            	}
            	else {
            		a.get(s).add(new Road(e, -t));
            	}
            }
            boolean isCycle = false;
            for (int i = 1; i <= N; i++) {
				if(bellmanFord(i)) {
					isCycle = true;
					sb.append("YES\n");
					break;
				}
			}
            if(!isCycle) {
            	sb.append("NO\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
	}

	private static boolean bellmanFord(int i) {
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[i] = 0;
		boolean updt = false;
		
		for (int j = 1; j < N; j++) {
			updt = false;
			for (int j2 = 1; j2 <= N; j2++) {
				for(Road road : a.get(j2)) {
					if(dist[j2] != Integer.MAX_VALUE && dist[road.e] > dist[j2] + road.t) {
						dist[road.e] = dist[j2] + road.t;
						updt = true;
					}
				}
			}
			if(!updt) break;
		}
		
		if(updt) {
			for (int j = 1; j <= N; j++) {
				for(Road road : a.get(j)) {
					if(dist[j] != Integer.MAX_VALUE && dist[road.e] > dist[j] + road.t) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
