package dijkstraBellmanford;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class WormholeNode{
	int start;
	int end;
	int worm;
	
	public WormholeNode(int start, int end, int worm) {
		this.start = start;
		this.end = end;
		this.worm = worm;
	}
	
}

public class BJ1865_웜홀 {

	static int t,n,m,w;
	static int INF = Integer.MAX_VALUE;
	static WormholeNode[] list;
	static long[] dist;//자료형 long
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		t = Integer.parseInt(br.readLine());
		
		
		for (int k = 0; k < t; k++) {
			
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			
			list = new WormholeNode[2*m+w];
			dist = new long[n+1];
			
			//무제한 값으로 일괄 초기화
			Arrays.fill(dist, INF);

			//시작값의 자기자신의 경로는 0으로 초기화
			dist[1] = 0; 
			
			int idx = 0;
			
			//각 도시간의 비용 초기화
			for (int i = 0; i < m+w; i++) {
				st = new StringTokenizer(br.readLine());
				int startN = Integer.parseInt(st.nextToken());
				int endN = Integer.parseInt(st.nextToken());
				int worm = Integer.parseInt(st.nextToken());

				//방향이 없음으로 반대방향도 추가
				if(i < m) {
					list[idx++] = new WormholeNode(startN, endN, worm);
					list[idx++] = new WormholeNode(endN, startN, worm);
				}
				
				//웜홀은 방향이 있음으로 -1 추가
				else {
					list[idx++] = new WormholeNode(startN, endN, worm*-1);
				}
			
				list[i] = new WormholeNode(startN, endN, worm);
			}
		
			
			//음의 순환 확인하기
			if(getBellmanford()) {
				System.out.println("YES");
			}
			else {
				System.out.println("NO");
			}
		}
	}

	private static boolean getBellmanford() {
		
		for (int i = 1; i <= n; i++) {
			
			//모든 간선 확인하기(음의 순환)
			for (int j = 0; j <m; j++) {
				
				int s = list[j].start;
				int e = list[j].end;
				int w = list[j].worm;

				if(dist[s] == INF ) continue;
				//이동시간이 짧을 경우 값 갱신
				if(dist[e] > (dist[s] + w)) {
					dist[e] = dist[s]+w;
					
					//n번째에서 값이 갱신된다면 음의 순환인 존재하는 것!!
					if(i ==n) return true;
					
				}
				
			}
			
		}
		return false;
	}



}
