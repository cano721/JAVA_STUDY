package 유형별문제풀이.unionfindMST;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 * 
 * 1. 연결된 도시가 있다면(1) union연산
 * 2. 여행계획에서의 첫 번째 도시를 find하여 부모 노드를 찾는다.
 * 3. 여행계획도시의 2~N번째 도시에 대해 find연산을 하여 한 루트 노드의 2에서 수행한 루트노드를 비교한다. (서로 루트노드가 다를 경우 NO)
 * 
 * 
 @author Jeeyani
 */

public class BJ1976_여행가자 {
	
	static int n,m;
	static int[][] metrix;
	static int[] parent;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		metrix = new int[n+1][n+1];
		parent = new int[n+1];
		
		//각 정점마다 분리되어 있는 것을 가정하여 자기 자신의 노드번호로 초기화
		for (int i = 1; i <=n; i++) {
			parent[i] = i;
		}
		
		for (int i = 1; i <=n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <=n; j++) {
				metrix[i][j] = Integer.parseInt(st.nextToken());
				
				//두 도시가 연결되어 있다면 하나의 집합으로 합쳐준다.
				if(metrix[i][j] ==1) {
					union(i,j);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int city = 0;
		int root = 0;
		boolean flag = true;
		
		for (int i = 1; i <=m; i++) {
			city = Integer.parseInt(st.nextToken());
			
			if(i==1) {
				root = find(city);
			}
			else {
				
				if(root != find(city)) {
					flag = false;
					break;
				}
				
			}
		}
		
		StringBuilder sb = new StringBuilder();
		if(flag) sb.append("YES");
		else sb.append("NO");
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}

	private static int find(int x) {
		if(x == parent[x]) return x;
		
		return parent[x] = find(parent[x]);
	}

	//정점 연결
	private static void union(int x, int y) {
		x = find(x);
		y= find(y);
		
		//같은 부모가 아닌 경우
		if( x != y) {
			if(x > y) parent[x] = y;
			
			else parent[y] = x;
		}
		
	}

}
