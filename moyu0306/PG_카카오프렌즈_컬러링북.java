import java.util.*;

class Solution {
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static boolean[][] isVisited;
    public int[] solution(int m, int n, int[][] picture) {
        isVisited =new boolean[m][n];
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                int size = BFS(i,j,picture,m,n);
                if(size>0){
                    numberOfArea++;
                    maxSizeOfOneArea= Integer.max(size,maxSizeOfOneArea);
                }
            }
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public int BFS(int y, int x,int[][] picture,int m, int n){
        if(isVisited[y][x]) return -1;
        if(picture[y][x] == 0){
            isVisited[y][x] = true;
            return -1;
        }
        
        Queue<int[]> q = new LinkedList<>();
        int size = 1;
        int color = picture[y][x];
        isVisited[y][x] = true;
        q.offer(new int[]{y,x});
        
        while(!q.isEmpty()){
            int[] point = q.poll();
            
            for(int i=0; i<4; i++){
                int posy = point[0] + dy[i];
                int posx = point[1] + dx[i];
                if(posy<0||posy>m-1||posx<0||posx>n-1) continue;
                if(isVisited[posy][posx]) continue;
                if(picture[posy][posx] == color){
                    size++;
                    q.offer(new int[]{posy,posx});
                    isVisited[posy][posx] = true;
                }
            }
        }
        return size;
        
    }
}