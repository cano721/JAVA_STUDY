package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class n2638_치즈 {
	static int n, m, cnt, answer;
	static int[][] arr;
	static int[] dx = {0,1,0,-1}, dy = {1,0,-1,0};
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine()); 
			
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 1) cnt++;
			}
		}
		
		while(cnt != 0) {
			visited = new boolean[n][m];
			bfs();
			answer++;
		}
		
		System.out.println(answer);

	}

	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		visited[0][0] = true;
        q.offer(new int[] {0,0});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            // 방향 돌기
            for(int idx = 0; idx < 4; idx++){
                int newX = x+dx[idx];
                int newY = y+dy[idx];
                // 맵 안에 있으면서
                if(0 <= newX && newX < n && 0 <= newY && newY < m){
                    // 공기일때
                    if(arr[newX][newY] == 0 && visited[newX][newY] == false){
                        visited[newX][newY] = true;
                        q.offer(new int[] {newX,newY});
                    // 치즈일땐 visited 숫자 증가
                    }else if(arr[newX][newY] != 0){
                        arr[newX][newY]++;
                    }
                }
            }
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(arr[i][j] > 2){
                    arr[i][j] = 0;
                    cnt--;
                }else if(arr[i][j] != 0){
                	arr[i][j] = 1;
                }
            }
        }
		
	}

}
