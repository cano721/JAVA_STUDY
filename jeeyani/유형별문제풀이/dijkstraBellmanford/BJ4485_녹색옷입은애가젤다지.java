package 유형별문제풀이.dijkstraBellmanford;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BJ4485_녹색옷입은애가젤다지 {

	static int n,k;
	static int INF = Integer.MAX_VALUE;
	static int[][] map;
	static int[][] dist;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int cnt = 0;
		
		while (true) {
			cnt++;

			n = Integer.parseInt(br.readLine());
			
			if(n == 0) break;
					
			map = new int[n][n];
			dist = new int[n][n];
			
			//무제한 값으로 일괄 초기화
			for(int t[] : dist) {
				Arrays.fill(t, INF);
			}
			
			
			//도둑루피
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			//시작값의 링크의 경로는 (0,0)위치의 루피값으로 초기화
			dist[0][0] = map[0][0]; 
			
			getRupee();
			
			System.out.println("Problem "+cnt+": "+dist[n-1][n-1]);
			
			
		}
		
		
	}

	private static void getRupee() {
		PriorityQueue<rupeeNode> q = new PriorityQueue<rupeeNode>();
		q.add(new rupeeNode(0,0,dist[0][0]));
		
		while (!q.isEmpty()) {
			//노드의 최소값 꺼내기
			rupeeNode node = q.poll();
			
			//현재 위치의 값이 이전값보다 큰 경우는 무시
			if(node.value > dist[node.x][node.y]) {
				continue;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				
				//범위안
				if(nx >= 0 && ny >= 0 && nx <n && ny <n) {
					
					//루피값이 더 작은 경우 갱신
					if(dist[nx][ny] > dist[node.x][node.y] + map[nx][ny]) {
						dist[nx][ny] = dist[node.x][node.y] + map[nx][ny];
						q.add(new rupeeNode(nx, ny, dist[nx][ny]));
					}
					
				}
				
			}
			
		}
		
	}

}


class rupeeNode implements Comparable<rupeeNode> {
    int y;
    int x;
    int value;

    public rupeeNode(int x, int y,int value) {
        this.y = y;
        this.x = x;
        this.value = value;
    }

    @Override
    public int compareTo(rupeeNode o) {
        return value - o.value;
    }
}

