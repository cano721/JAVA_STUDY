package 유형별문제풀이.unionfindMST;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


/*
 * 
 * 유니온파인드 정수타입 -> 문자열타입
 * 
 * 대표값(부모)의 size배열 값이 관계가 형성될때 증가
 * 
 @author Jeeyani
 */

public class BJ4195_친구네트워크 {
	
	static int n,m;
	static int[] parent;
	static int[] level;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < tc; i++) {
			
			n = Integer.parseInt(br.readLine());
			
			parent = new int[n*2];
			level = new int[n*2];
			
			//초기화
			for (int j = 0; j <n*2; j++) {
				parent[j]=j;
				level[j]=1;
			}
			
			int idx=0;
			Map<String, Integer> map = new HashMap<String, Integer>();
			
			for (int j = 0; j < n; j++) {
				
				st = new StringTokenizer(br.readLine());
				String a= st.nextToken();
				String b = st.nextToken();
				
				if(!map.containsKey(a)) {
					map.put(a,idx++);
				}
				
				if(!map.containsKey(b)) {
					map.put(b, idx++);
				}
				
				sb.append(union(map.get(a),map.get(b))+"\n");
			}
	
		}
		
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
	private static int union(int x, int y) {
		x = find(x);
		y= find(y);
		
		//같은 부모가 아닌 경우
		if( x != y) {
			parent[y] = x;
			level[x] += level[y];
			
			level[y] = 1;
		}
		return level[x];
	}

}
