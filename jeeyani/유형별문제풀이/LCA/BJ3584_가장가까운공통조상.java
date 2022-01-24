package 유형별문제풀이.LCA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/*
 * 
 * LCA 알고리즘 문제
 * 1. 모든 노드에 대한 깊이 계산(DFS)
 * 2. 최소 공통 조상을 찾을 두 노드 확인
 *  2-1. 두 노드의 깊이가 동일하도록 거슬러 올라가
 *  2-2. 이후 부모가 같아질때까지 반복적으로 두 노드의 부모 방향으로 거슬러 올라감
 *  
 * 3. 모든 LAC(a,b) 연산에 대해 2번과과정 반복
 * 
 @author Jeeyani
 */

public class BJ3584_가장가까운공통조상 {

	static int[] parent;
	static List<List<Integer>> list;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		
		while(tc-- > 0) {
			int n = Integer.parseInt(br.readLine());
			list = new ArrayList<List<Integer>>();
			
			for (int i = 0; i <= n; i++) {
				list.add(new ArrayList<Integer>());
			}
			
			parent = new int[n+1];
			
			//각 부모와 자식관계 초기화
			for (int i = 0; i < n-1; i++) {
				st = new StringTokenizer(br.readLine());
				int p = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				parent[c] = p;
				list.get(p).add(c);
			}
		
			//공통 조상을 찾을 두 노드
			st = new StringTokenizer(br.readLine());
			int node = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			//모든 노드에 대한 깊이 계산
			int nDepth = getDepth(node);
			int vDepth = getDepth(v);
			
			int samParent = LCA(node,nDepth,v,vDepth);
			
			
			sb.append(samParent+"\n");
			
		}
			
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		

	}

	private static int LCA(int node, int nDepth, int v, int vDepth) {
		
		// 두 노드가 같은 level이 될때까지 거슬러 올라가기
		if(nDepth > vDepth) {
			
			while(nDepth != vDepth) {
				nDepth--;
				node = parent[node];
			}
		}
		else if(nDepth < vDepth){
			while(nDepth != vDepth) {
				vDepth--;
				v = parent[v];
			}
		}
		
		//만약 거슬러 올라가는 중에 동시에 만나는 정점이 가장 가까운 공통조상임
		while(node !=v) {
			node = parent[node];
			v = parent[v];
		}
		
		return node;
	}

	private static int getDepth(int k) {
		int level = 0;
		int current = k;
		
		while( current != 0) {
			level++;
			current = parent[current];
		}
		
		return level-1;
		
	}

}
