import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class n16234_인구이동 {
	static int[][] arr;
	static boolean[][] visited;
	static int n, l, r, answer = 0;
	static int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
        	st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
        while(true) {
        	boolean flag = false;
        	
        	visited = new boolean[n][n];
        	for (int i = 0; i < n; i++) {
    			for (int j = 0; j < n; j++) {
    				if(!visited[i][j]) 
    					if(bfs(i, j)) flag = true;
    			}
    		}
        	if(!flag) break;
        	else answer++;
        }
        System.out.println(answer);
	}

	private static boolean bfs(int x, int y) {
		boolean isUnion = false;
        ArrayList<Pair> unionList = new ArrayList<>(); // 연합에 속하는 나라의 좌표 리스트
        unionList.add(new Pair(x, y));
        int count = 1;
        int sum = arr[x][y];

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(x, y));
        visited[x][y] = true;

        while(!queue.isEmpty()) {
            Pair p = queue.poll();
            int curX = p.x;
            int curY = p.y;

            for(int i = 0; i < 4; i++) { // 상좌하우 검사
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n) // 범위 체크
                    continue;

                if(visited[nx][ny])
                    continue;

                // 두 나라의 인구 차이가 L명 이상, R명 이하라면 연합 가능
                int val = Math.abs(arr[curX][curY] - arr[nx][ny]);

                if(val >= l && val <= r && !visited[nx][ny]) {
                    unionList.add(new Pair(nx, ny));
                    count++;
                    sum += arr[nx][ny];

                    visited[nx][ny] = true;
                    queue.add(new Pair(nx, ny));
                }
            }
        }

        if(unionList.size() > 1) {
            isUnion = true;
            int result = sum / count;

            for(int i = 0; i < unionList.size(); i++) { // 연합 인구수 업데이트
                Pair p = unionList.get(i);
                arr[p.x][p.y] = result;
            }
        }
        return isUnion;
	}
	
    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
	
	

}
