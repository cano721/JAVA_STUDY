package dfsbfs;

import java.util.*;

class Node {

    private int x;
    private int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
}

public class BJ2178_미로찾기 {

	static int n;
	static int m;
	static int[][] map = new int[201][201];
	static boolean[][] visited;
	static int min = Integer.MAX_VALUE;
	
	// 이동할 네 가지 방향 정의 (상, 하, 좌, 우) 
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		sc.nextLine();
		
		//map = new int[n][m];
		visited = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			String temp = sc.nextLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = temp.charAt(j)-'0';
			}
		}
		
		//getMinCourseDFS(0,0,1);
		//System.out.println(min);
		
		System.out.println(getMinCourseBFS(0,0));
		
	}

	private static int getMinCourseBFS(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
       
        while(!q.isEmpty()) {
            Node node = q.poll();
            x = node.getX();
            y = node.getY();
            
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                //공간을 벗어난 경우 무시
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                //벽인 경우 무시
                if (map[nx][ny] == 0) continue;
                //해당 노드를 처음 방문하는 경우에만 최단 거리 기록
                if (map[nx][ny] == 1) {
                    map[nx][ny] = map[x][y] + 1;
                    q.offer(new Node(nx, ny));
                } 
            } 
        }
        // 가장 오른쪽 아래까지의 최단 거리 반환
        return map[n - 1][m - 1];
	}

	private static void getMinCourseDFS(int i, int j, int sum) {
		//공간을 벗어난 경우 무시
		if( i < 0 || i >=n || j<0 || j >= n) {
			return;
		}
		//벽인 경우 무시
		if(map[i][j] == 0) {
			return;
		}
		//마지막 위치에 도착했을 경우 결과 출력
		else {
			if(i == n-1 && j == m-1) {
				min = Math.min(sum, min);
				return;
			}
		}
		
		if(!visited[i][j]) {
			visited[i][j] = true;
			getMinCourseDFS(i-1, j, sum+map[i][j]);
			getMinCourseDFS(i+1, j, sum+map[i][j]);
			getMinCourseDFS(i, j-1, sum+map[i][j]);
			getMinCourseDFS(i, j+1, sum+map[i][j]);
			visited[i][j] = false;
		}
		
	}

}
