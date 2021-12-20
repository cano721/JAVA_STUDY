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
 * 1. 각 좌표를 입력받는다. 입력받을때 각 별의 번호도 함께 부여
 * 2. 모든 별들 사이의 거리를 구하여 간선 list에 저장
 * 
 * [프림알고리즘 사용]
 * 
 @author Jeeyani
 */

public class BJ4386_별자리만들기 {

	//각 별들의 좌표와 번호를 저장
	static class node{
		double x;
		double y;
		int num;
		
		public node(double x, double y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}
		
	}
	
	//각 별들간의 거리를 계산하는 객체
	static class StarNode implements Comparable<StarNode>{
        int idx;
        double dist;

        public StarNode(int idx,double dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(StarNode o) {
            return (int)(this.dist - o.dist);
        }
    }
	
	static int n,m;
	static List<StarNode>[] starlist; //각 별들 간의 거리를 저장하는 list
	static List<node> list; //별의 좌표와 번호를 저장하는 list
	static boolean[] visited;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		
		list = new ArrayList<>();
		starlist = new ArrayList[n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			list.add(new node(x, y, i));
			
			starlist[i] = new ArrayList<>();
		}
		
		//모든간선에 대한 거리계산
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				
				double dist = Math.sqrt(Math.pow(list.get(i).x - list.get(j).x, 2) + Math.pow(list.get(i).y - list.get(j).y, 2));
				starlist[j].add(new StarNode(i, dist));
				starlist[i].add(new StarNode(j, dist));
				
			}
		}
		
		visited = new boolean[list.size()];
		double result = getMinCost();

		StringBuilder sb = new StringBuilder();
		sb.append(result);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}


	private static double getMinCost() {
		PriorityQueue<StarNode> pq = new PriorityQueue<>();
		
		pq.add(new StarNode(0, 0));
		double dist = 0;
		while (!pq.isEmpty()) {
			StarNode node = pq.poll();
			
			//이미 방문 했으면 넘어가기
			if(visited[node.idx]) continue;
			
			visited[node.idx] = true; //방문처리
			dist += node.dist;
			
			
			for (int i = 0; i < starlist[node.idx].size(); i++) {
				StarNode next = starlist[node.idx].get(i);
				if(!visited[next.idx]) {
					pq.add(new StarNode(next.idx, next.dist));
				}
			}
			
		}
		
		return dist;
		
	}

}
