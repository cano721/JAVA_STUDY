import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Point {
		int x, y, cnt;
		Point(int x, int y, int cnt){
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	static char arr[][];
	static Queue<Point> q;
	static int dx[]= {-1,0,1,0};
	static int dy[]= {0,1,0,-1};
	static int n, m;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new char[n][m];
		q = new LinkedList<Point>();
		
		for(int i = 0 ; i < n ; ++i) {
			char[] c = br.readLine().toCharArray();
            
			for(int j = 0 ; j < m ; ++j) {
				arr[i][j] = c[j];
				if(arr[i][j] == 'o') {
					q.offer(new Point(i, j, 0));
				}
			}
		}
		
		System.out.println(bfs());
	}

	private static int bfs() {
        
		while(!q.isEmpty()) {
			Point co1 = q.poll();
			Point co2 = q.poll();
			
			if(co1.cnt >= 10) return -1;
	
			for(int d = 0 ; d < 4 ; ++d) {
				boolean drop1 = false;
				boolean drop2 = false;
				
				int nx1 = co1.x + dx[d];
				int ny1 = co1.y + dy[d];
				int nx2 = co2.x + dx[d];
				int ny2 = co2.y + dy[d];

				if(nx1 < 0 || ny1 < 0 || nx1 >= n || ny1 >= m)	drop1 = true;	
				if(nx2 < 0 || ny2 < 0 || nx2 >= n || ny2 >= m) 	drop2 = true;
                
				if(drop1 && drop2) 	continue;			//두 동전 드랍
				if(drop1 || drop2)	return co1.cnt + 1; //한 동전 드랍
				
				if(arr[nx1][ny1] == '#' && arr[nx2][ny2] == '#') continue;
                
				if(arr[nx1][ny1] == '#') {
					nx1 = co1.x;
					ny1 = co1.y;
				}
                
				if(arr[nx2][ny2] == '#') {
					nx2 = co2.x;
					ny2 = co2.y;
				}
                
				q.offer(new Point(nx1,ny1,co1.cnt+1));
				q.offer(new Point(nx2,ny2,co2.cnt+1));
			}
		}
		return -1;
	}
}