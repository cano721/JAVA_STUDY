package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class d220206_게임맵최단거리 {

	public static void main(String[] args) {
		int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
		int answer = solution(maps);
		System.out.println(answer);
	}
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	
	private static int solution(int[][] maps) {
		int[][] visited = new int[maps.length][maps[0].length];
		bfs(maps, visited);
        int answer = visited[maps.length-1][maps[0].length-1];
        
        if(answer == 0){
            answer = -1;
        }
        return answer;
	}

	private static void bfs(int[][] maps, int[][] visited) {
		int x = 0;
        int y = 0;
        visited[x][y] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        
        while(!queue.isEmpty()){
            int[] current = queue.remove();
            int cX = current[0];
            int cY = current[1];
            
            for(int i = 0; i < 4; i++){
                int nX = cX + dx[i];
                int nY = cY + dy[i];
                
                if(nX < 0 || nX > maps.length-1 || nY < 0 || nY > maps[0].length-1)
                    continue;
                
                if(visited[nX][nY] == 0 && maps[nX][nY] == 1){
                    visited[nX][nY] = visited[cX][cY] + 1;
                    queue.add(new int[]{nX, nY});
                }
            }
        }
	}
}
