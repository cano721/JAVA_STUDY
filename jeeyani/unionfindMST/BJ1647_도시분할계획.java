package unionfindMST;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * MST알고리즘 문제
 * 
 * 각 집을 두개의 도시로 나눈다.
 * 최소비용만으로 각 도시안에 집은 연결되어 있어야함.
 * 
 * 따라서 모든 집을 연결한 상태에서 가장 큰 비용이 드는 간선 1개만 없애면 가능하다.
 * 
 * [프림알고리즘 사용]
 * 
 @author Jeeyani
 */

public class BJ1647_도시분할계획 {

	static class CityNode implements Comparable<CityNode>{
        int idx;
        int cost;

        public CityNode(int idx,int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(CityNode o) {
            return this.cost - o.cost;
        }
    }
	
	static int n,m,cnt=0,result=0,maxCost=0;
	static List<CityNode>[] list;
	static boolean[] visited;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[n+1];
		visited = new boolean[n+1];
		
		for (int i = 1; i <= n; i++) {
			list[i] =new ArrayList<>();
		}
		
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			list[a].add(new CityNode(b, c));
			list[b].add(new CityNode(a, c));
		}
		

		getCityCost();
		StringBuilder sb = new StringBuilder();
		sb.append(result-maxCost);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}


	private static void getCityCost() {
		PriorityQueue<CityNode> pq = new PriorityQueue<>();
		
		pq.add(new CityNode(1, 0));
		
		while (true) {
			CityNode node = pq.poll();
			
			//이미 방문 했으면 넘어가기
			if(visited[node.idx]) continue;
			
			visited[node.idx] = true; //방문처리
			result += node.cost;
			maxCost = Math.max(maxCost, node.cost);
			cnt++;
			
			if(cnt == n) break; //모두 확인했으면 종료
			
			for (CityNode v : list[node.idx]) {
				if(!visited[v.idx]) {
					pq.add(new CityNode(v.idx, v.cost));
				}
			}
			
		}
		
		
		
	}

}
