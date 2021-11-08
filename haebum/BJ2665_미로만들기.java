/*
    맵의 범위 50*50

    bfs 통해 이동

    bfs 돌건데 visited에 현재깨고 온 검은방의 개수를 저장해둘거임.
    그래서 지금까지 깨고온게 이미 저장되어있는 개수보다 많으면 굳이 안돌거임!
    
    visited 원소를 처음에 맥스값으로 설정해둘것.
*/
import java.util.*;
import java.io.*;

public class BJ2665_미로만들기 {

    public static int n;
    public static int[][] arr;
    public static int[][] visited;
    public static int[] dirX = {0,1,0,-1};
    public static int[] dirY = {1,0,-1,0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        
        // 배열크기 생성
        arr = new int[n][n];
        visited = new int[n][n];

        // 맵 저장
        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < n; j++){
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        // 초기 visited 맥스값으로 설정
        for(int i = 0; i <n; i++){
            Arrays.fill(visited[i],Integer.MAX_VALUE);
        }
        // bfs 돌기
        bfs();
        // 최종 목표에 도달했을때 검은방을 깬 개수
        System.out.println(visited[n-1][n-1]);
    }

    // dfs
    public static void bfs(){

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {0,0});
        visited[0][0] = 0;

        while(!q.isEmpty()){
            int[] cur = q.poll();

            int x = cur[0];
            int y = cur[1];
            
            for(int i = 0; i < 4; i++){
                int newX = x + dirX[i];
                int newY = y + dirY[i];
    
                // 맵에 있으면서 방문할 곳이 현재 검은방을 깨고온 숫자보다 많을때(적은값으로 바꿔줄거임)
                if(0 <= newX && newX < n && 0 <= newY && newY < n && visited[newX][newY] > visited[x][y]){
                    // 검은방이면
                    if(arr[newX][newY] == 0){
                        // 현재깨고온 개수에 +1
                        visited[newX][newY] = visited[x][y]+1;
                    // 흰방이면
                    }else{
                        // 현재깨고 온것으로 바꿔주기
                        visited[newX][newY] = visited[x][y];
                    }
                    q.offer(new int[] {newX,newY});
                }
            }
        }
    }
}
