package 유형별문제풀이.unionfindMST;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

class NetworkNode implements Comparable<NetworkNode> {
    int y;
    int x;
    int cost;

    public NetworkNode(int x, int y,int cost) {
        this.y = y;
        this.x = x;
        this.cost = cost;
    }

    @Override
    public int compareTo(NetworkNode o) {
        return cost - o.cost;
    }
}

public class BJ1922_네트워크연결 {
	
	static int n,m;
	static List<NetworkNode> list;
	static int[] parent;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		parent = new int[n+1];
		
		//자기 자신의 노드번호로 초기화
		for (int i = 1; i <=n; i++) {
			parent[i] = i;
		}
		
		list = new ArrayList<NetworkNode>();
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			list.add(new NetworkNode(x, y, cost));
		}
		
		Collections.sort(list);
		
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			NetworkNode node = list.get(i);
			
			if(!isCheck(node.x, node.y)) {
				sum += node.cost;
				union(node.x, node.y);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(sum);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}

	//선택한 간선의 두 정점이 연결되어 있지 않으면 두 정점을 연결시켜준다.
	private static boolean isCheck(int x, int y) {
		x = find(x);
		y= find(y);
		
		if( x == y) return true;
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
