package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
class Bus{
	int u, v, cost;
	public Bus(int u, int v, int cost) {
		this.u = u;
		this.v = v;
		this.cost = cost;
	}
}
public class n11657_타임머신 {
	static int n, m;
	static Bus[] e;
	static long[] dist;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        e = new Bus[m];
        //거리배열 무한대 초기화
        dist = new long[n+1];
        for (int i = 1; i <= n; i++) {
			dist[i] = Integer.MAX_VALUE;
		}

        // 인접리스트 입력
        for (int i = 0; i < m; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int c = Integer.parseInt(st.nextToken());
        	
        	e[i] = new Bus(a, b, c);
		}
        
        if(bellmanford(1)) { //음수 순환 존재
        	System.out.println("-1");
        } else {
        	for (int i = 2; i <= n; i++) {
				if(dist[i] == Integer.MAX_VALUE) { //도달할 수 없다.
					System.out.println("-1");
				}else {
					System.out.println(dist[i]);
				}
			}
        }
	}
	static boolean bellmanford(int start){
		dist[start] = 0;
		
		// n번 반복 
		for(int i=1; i<n+1; i++) {
			// 매 반복마다 모든 간선을 확인 
			for(int j=0; j<m; j++) {
				int cur = e[j].u;
				int next = e[j].v;
				int cost = e[j].cost;
						
				if(dist[cur] == Integer.MAX_VALUE) continue;

				if(dist[next] > (dist[cur] + cost)) { // 최단경로
					dist[next] = dist[cur] + cost;
							
					// n번째 라운드에서 값이 갱신된다면 음수 순환 존재 
					if (i == n) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
