package dijkstraBellmanford;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class BusTimeNode{
	int start;
	int end;
	int time;
	
	public BusTimeNode(int start, int end, int time) {
		this.start = start;
		this.end = end;
		this.time = time;
	}
	
}

public class BJ11657_타임머신 {

	static int n,m;
	static int INF = Integer.MAX_VALUE;
	static BusTimeNode[] list;
	static long[] dist;//자료형 long
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		
		list = new BusTimeNode[m];
		dist = new long[n+1];
		
		//무제한 값으로 일괄 초기화
		Arrays.fill(dist, INF);
		
		//각 도시간의 비용 초기화
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int startN = Integer.parseInt(st.nextToken());
			int endN = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());
		
			list[i] = new BusTimeNode(startN, endN, time);
		}
	
		
		//시작값의 자기자신의 경로는 0으로 초기화
		dist[1] = 0; 
		
		//음의 순환 확인하기
		if(getBellmanford()) {
			System.out.println(-1);
		}
		else {
			for (int i = 2; i <=n; i++) {
				
				if(dist[i] == INF) {
					System.out.println("-1");
				}
				else {
					System.out.println(dist[i]);
				}
			}
		}
		
	
	}

	private static boolean getBellmanford() {
		
		for (int i = 1; i <=n; i++) {
			
			//모든 간선 확인하기(음의 순환)
			for (int j = 0; j <m; j++) {
				
				int s = list[j].start;
				int e = list[j].end;
				int t = list[j].time;

				if(dist[s] == INF ) continue;
				//이동시간이 짧을 경우 값 갱신
				if(dist[e] > (dist[s] + t)) {
					dist[e] = dist[s]+t;
					
					//n번째에서 값이 갱신된다면 음의 순환인 존재하는 것!!
					if(i ==n) return true;
					
				}
				
			}
			
		}
		return false;
	}



}
