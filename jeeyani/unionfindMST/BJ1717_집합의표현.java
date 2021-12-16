package unionfindMST;

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

public class BJ1717_집합의표현 {
	
	static int n,m;
	static int[] parent;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		parent = new int[n+1];
		
		//각 정점마다 분리되어 있는 것을 가정하여 자기 자신의 노드번호로 초기화
		for (int i = 1; i <=n; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i <m; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			int signal = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(signal==0) {
				union(a, b);
			}
			else {
				
				if(isCheck(a,b)) {
					sb.append("YES"+"\n");
				}
				else sb.append("NO"+"\n");
				
			}
			
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}

	private static boolean isCheck(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a==b) return true;
		else return false;
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
