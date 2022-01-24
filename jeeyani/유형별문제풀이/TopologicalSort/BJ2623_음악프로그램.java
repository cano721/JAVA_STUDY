package 유형별문제풀이.TopologicalSort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;


public class BJ2623_음악프로그램 {

	static int n,m,cnt=0;
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
			int num = Integer.parseInt(st.nextToken());
			
			int[] tempList = new int[num];
			tempList[0] = Integer.parseInt(st.nextToken());
			
			for (int j = 1; j < num; j++) {
				tempList[j] = Integer.parseInt(st.nextToken());
				list.get(tempList[j-1]).add(tempList[j]);
				nodeLinelist[tempList[j]]++;
			}	
		}
		
		getOrder();
		
		if(cnt >= n) {
			bw.write(sb.toString());
			bw.flush();
		}
		else { //순서 정하는 것이 불가능 할 경우!
			bw.write("0");
			bw.flush();
		}
		
		bw.close();
		br.close();

	}

	private static void getOrder() {
		Queue<Integer> q = new LinkedList<Integer>();
		
		for (int i = 1; i <=n; i++) {
			if(nodeLinelist[i] == 0) {
				q.add(i);
			}
		}
		
		while(!q.isEmpty()) {
			cnt++;
			int v = q.remove();
			sb.append(v+"\n");
			
			for(int j = 0; j < list.get(v).size(); j++) {
				int next = list.get(v).get(j);
				nodeLinelist[next]--;
				
				if(nodeLinelist[next] == 0) {
					q.add(next);
				}
				
			}
		}
		
	}
}
