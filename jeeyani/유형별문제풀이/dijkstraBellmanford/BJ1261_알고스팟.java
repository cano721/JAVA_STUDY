package 유형별문제풀이.dijkstraBellmanford;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 
 * BFS로 접근가능하지만, "가중치"가 존재함으로 다익스트라로 접근 가능
 * 
 * 1인 값을 지날때에는 무조건 1(벽)을 부수고 지나가야함. 따라서 해당 1인 곳의 값을 변경시켜나아가야함
 * 
 * 
 */

public class BJ1261_알고스팟 {

	static int n,m;
	static int INF = Integer.MAX_VALUE;
	static int[][] map;
	static int[][] dist;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		
		map = new int[n+1][m+1];
		dist = new int[n+1][m+1];
		
		//무제한 값으로 일괄 초기화
		for(int t[] : dist) {
			Arrays.fill(t, INF);
		}
		
		
		//각 도시간의 비용 초기화
		for (int i = 1; i <= n; i++) {
			String[] temp = br.readLine().split("");
			for (int j = 1; j <= m; j++) {
				map[i][j] = Integer.parseInt(temp[j-1]);
			}
		}
		
		
		//시작값의 자기자신의 경로는 0으로 초기화
		dist[1][1] = 0; 
		
		getWallCnt();
		
		System.out.println(dist[n][m]);
	}

	private static void getWallCnt() {
		PriorityQueue<mapNode> q = new PriorityQueue<mapNode>();
		q.add(new mapNode(1,1, 0));
		
		while (!q.isEmpty()) {
			//노드의 최소값 꺼내기
			mapNode node = q.poll();
			
			//(n,m)에 도착하면 종료
			if(node.x ==n && node.y == m) {
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				
				//범위안
				if(nx > 0 && ny > 0 && nx <=n && ny <=m) {
					
					//가중치값 갱신
					if(dist[nx][ny] > dist[node.x][node.y] + map[nx][ny]) {
						dist[nx][ny] = dist[node.x][node.y] + map[nx][ny];
						q.add(new mapNode(nx, ny, dist[nx][ny]));
					}
					
				}
				
			}
			
		}
		
	}

}


class mapNode implements Comparable<mapNode> {
    int y;
    int x;
    int value;

    public mapNode(int x, int y,int value) {
        this.y = y;
        this.x = x;
        this.value = value;
    }

    @Override
    public int compareTo(mapNode o) {
        return value - o.value;
    }
}

