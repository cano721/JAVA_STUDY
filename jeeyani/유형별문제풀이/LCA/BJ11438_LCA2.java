package 유형별문제풀이.LCA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

import com.sun.tools.classfile.StackMapTable_attribute.append_frame;

/*
 * 
 * parent[k][v] : 정점v의 2^k 번째 조상 정점 번호
 * 
 * parent[K][V] = parent[K-1][parent[K-1][N]] 점화식을 이용
 * 
 * 1. depth[u] > depth[v]일 때, u를 parent[u]로 대체하는 것을 반복
 * 2. 깊이를 맞춘 이후 노드가 같다면 종료
 * 3. K를 큰 값부터 순회하여 parent[K][u]!=parent[K][v]라면 u,v를 동시에 2^K만큼 위로 이동
 * 
 * 
 * 출저 : https://velog.io/@jeewoo1025/%EB%B0%B1%EC%A4%80-11438%EB%B2%88-LCA-2
 @author Jeeyani
 */

public class BJ11438_LCA2 {

	static int n,m,k;
	static int[][] parent;
	static int[] depth;
	static List[] list;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		
		k=0;
		// 2^K > N인 K찾기
		for (int i = 1; i <=n; i*=2) {
			k++;
		}
		
        depth = new int[n+1];
        parent = new int[k][n+1];
        list = new ArrayList[n+1];
        
        for (int i = 1; i <=n; i++) {
			list[i] = new ArrayList<Integer>();
		}


		//각 부모와 자식관계 초기화
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			list[a].add(b);
			list[b].add(a);
		}

		getDepth(1,1);
		
		//부모채우기
		for (int i = 1; i < k; i++) {
			for (int j = 1; j <=n; j++) {
				parent[i][j] = parent[i-1][parent[i-1][j]];
			}
		}
		
		int m = Integer.parseInt(br.readLine());
		for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 공통 조상 출력
            sb.append(LCA(a, b)+"\n");
        }

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();

	}

	private static void getParentFill() {
		// TODO Auto-generated method stub
		
	}

	private static int LCA(int a,int b) {

		//depth[a] >= depth[b] 조건을 맞추도록 변경
		if (depth[a] < depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}
		
		//2^K만 큼 올라가서 depth 맞추기
		for (int i = k-1; i >=0; i--) {
			if(Math.pow(2, i) <= depth[a]-depth[b]) {
				a = parent[i][a];
			}
		}
		
		//같은 깊이일 경우 종료
		if(a==b) return a;
		
		// a,b는 같은 depth이므로 2^K만큼 점프하며 공통부모 바로 아래까지 올라가기
		for(int i = k-1; i >= 0; i--) {
            if(parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        return parent[0][a];

	}

	private static void getDepth(int idx, int cnt) {
		depth[idx] = cnt;
		
		//자식들의 레벨을 기록
		for (int i = 0; i < list[idx].size(); i++) {
			int next = (Integer) list[idx].get(i);
			
			if(depth[next] == 0) {
				getDepth(next, cnt+1);
				parent[0][next] = idx;
			}
		}

	}

}
