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
 * 모든집이 최소미터로 연결되어 있어야함
 * 프림알고리즘
 * 
 @author Jeeyani
 */

class meterNode implements Comparable<meterNode> {
    int idx;
    int z;

    public meterNode(int idx, int z) {
        this.idx = idx;
        this.z = z;
    }

	@Override
    public int compareTo(meterNode o) {
        return z - o.z;
    }
}

public class BJ6497_전력난 {
	
	static int n,m,sum,minCost;
	static List<ArrayList<meterNode>> list;
	static boolean[] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			
			if(n==0 && m==0) break;
			
			list = new ArrayList<>();
			visited = new boolean[m];
			
			for (int i = 0; i <m; i++) {
				list.add(new ArrayList<>());
			}
			
			sum = 0;
			
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int z = Integer.parseInt(st.nextToken());
				
				list.get(x).add(new meterNode(y, z));
				list.get(y).add(new meterNode(x, z));
				
				sum +=z;
			}

			
			getMaxCost();
			sb.append(sum-minCost+"\n");
			
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}

	private static void getMaxCost() {
		PriorityQueue<meterNode> pq = new PriorityQueue<>();
		
		for(meterNode node : list.get(0)) pq.add(node);

		visited[0] = true;
		
		minCost = 0;
		while (!pq.isEmpty()) {
			meterNode node = pq.poll();
			
			//이미 방문 했으면 넘어가기
			if(visited[node.idx]) continue;
			
			visited[node.idx] = true; //방문처리
			minCost += node.z;

			for (meterNode v : list.get(node.idx)) {
				pq.add(v);
			}
		}
		
	}


}
