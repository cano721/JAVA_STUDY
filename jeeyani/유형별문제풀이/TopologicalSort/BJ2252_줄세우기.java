package 유형별문제풀이.TopologicalSort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/*
 * 위상정렬알고리즘 
 * 1. 후행 정점을 기준으로 연결되어 있는 간선의 수를 저장
 * 2. 선행 간선이 없는 정점을 큐에 집어 넣기
 * 3. 큐에서 하나씩 값을 빼면서 그 다음 값을 큐에 다시 넣는다.
 * 
 * 
 @author Jeeyani
 */

public class BJ2252_줄세우기 {

	static int n,m;
	static List<ArrayList<Integer>> list;
	static int[] nodeLinelist;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<ArrayList<Integer>>();
		nodeLinelist = new int[n+1];
		
		for (int i = 1; i <=n+1; i++) {
			list.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list.get(a).add(b);
			nodeLinelist[b]++; //후행 정점에 대한 간선 수 증가
			
		}
		
		getOrder();
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	private static void getOrder() {
		Queue<Integer> q = new LinkedList<Integer>();
		
		//1. 선행 정점을 가지고 있지 않는 정점을 큐에 삽입
		for (int i = 1; i <=n; i++) {
			if(nodeLinelist[i] == 0) {
				q.add(i);
			}
		}
		
		//2. 정점을 하나씩 제거하면서 간선의 수도 함께 삭제해준다.
		while(!q.isEmpty()) {
			int v = q.remove();
			sb.append(v+" ");
			
			for(int j = 0; j < list.get(v).size(); j++) {
				int next = list.get(v).get(j);
				nodeLinelist[next]--;
				
				//간석 중에 선행 정점을 가지고 있는 않는 경우 다시 큐에 삽입
				if(nodeLinelist[next] == 0) {
					q.add(next);
				}
				
			}
		}
		
	}
}
