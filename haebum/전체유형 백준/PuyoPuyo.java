
/**
 * 1. bfs 구현.
 * 
 * 2. 주어진 상황에서 몇연쇄가 되는지 파악하는 것.
 * 
 * 3. 각 연쇄마다 터질 뿌요들을 체크.
 * 
 * 4. 그 후에 없애고, 위의 뿌요 내리기.
 * 
 * 5. 다음 연쇄 체크.
 * 
 * 6. 터질 뿌요가 없을 때 종료하고 현재까지의 연쇄 회수 출력
 * 
 */

import java.util.*;
import java.io.*;

public class PuyoPuyo {

    public static char[][] map;
    public static int n,m,answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = 12;
        m = 6;
        map = new char[n][m];


        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < m; j++){
                map[i][j] = str.charAt(j);
            }
        }

        while(true){
            if(bfs()){
                answer++;
            }else{
                break;
            }
        }
    }

    public static boolean bfs(){
        boolean check = false;
        int[][] dirXY = {{1,0},{-1,0},{0,1},{0,-1}};
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] != '.' && visited[i][j] == false){
                    visited[i][j] = true;
                }
            }
        }
        return check;
    }

    public static void boom(){

    }
}
