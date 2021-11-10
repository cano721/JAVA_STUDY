package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class n2665_미로만들기 {
	
	static int n;
	static int[][] maze;
	static int[][] dijkstra;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static int answer;
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st = new StringTokenizer(br.readLine()); 

		n = Integer.parseInt(st.nextToken());
		maze = new int[n][n];
		dijkstra = new int[n][n];
		
        for (int i = 0; i < n; i++) {
            maze[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            Arrays.fill(dijkstra[i], Integer.MAX_VALUE);
        }
        
        br.close();
        
        solution();
        System.out.println(answer);
	}

	private static void solution() {
        Queue<int[]> q = new LinkedList<>();
        
        q.offer(new int[]{0, 0}); 
        dijkstra[0][0] = 0; 
        
        while(!q.isEmpty()) {
            int[] xy = q.poll();
            int x = xy[0], y = xy[1];

            for (int i = 0; i < 4; i++) {
                int r = x + dx[i], c = y + dy[i];
                
                if(0 <= r && r < n && 0 <= c && c < n && dijkstra[x][y] < dijkstra[r][c]){
                    switch (maze[r][c]){
                        case 0: // 이 지점이 까만방인 경우 + 1
                            dijkstra[r][c] = dijkstra[x][y] + 1;
                            break;
                        case 1: // 이 지점이 하얀방일 경우 이전과 같은 횟수로 세팅
                            dijkstra[r][c] = dijkstra[x][y];
                            break;
                    }
                    
                    q.offer(new int[]{r, c});
                }
            }
        }
        
        answer = dijkstra[n-1][n-1];
		
	}

}
