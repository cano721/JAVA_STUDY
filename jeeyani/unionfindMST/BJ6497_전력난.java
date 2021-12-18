package unionfindMST;

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
 *
 * 
 * 
 @author Jeeyani
 */

class meterNode implements Comparable<meterNode> {
    int y;
    int x;
    int z;

    public meterNode(int x, int y,int z) {
        this.y = y;
        this.x = x;
        this.z = z;
    }

    @Override
    public int compareTo(meterNode o) {
        return z - o.z;
    }
}

public class BJ6497_전력난 {
	
	static int n,m;
	static List<meterNode> list;
	static int[] parent;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		parent = new int[m];
		
		//자기 자신의 노드번호로 초기화
		for (int i = 0; i <m; i++) {
			parent[i] = i;
		}
		
		list = new ArrayList<meterNode>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			if(x ==0 && y ==0) break;
			
			list.add(new meterNode(x, y, z));
		}
		
		Collections.sort(list);
		
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			meterNode node = list.get(i);
			
			if(!isCheck(node.x, node.y)) {
				sum += node.z;
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
