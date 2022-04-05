
/**
 * 1. 맵 크기 500*500
 * 
 * 2. 4칸짜리 테트로미노를 둬서, 최대 정수 합의 크기 출력
 * 
 * 3. 각 칸마다 4칸을 갈 수 있는 모든 경우의 수 구해보기?
 *      500*500 * 4*4*4*4 가능..
 * 
 * 4. 2중 포문으로 각 칸을 넣고, dfs를 통해 4칸 가보기로 코드 진행
 * 
 * 5. 들렸던 칸은 방문하지 않아야하므로 체크할 visited 필요
 * 
 * 6. 0       0                0
 *    0 0   0 0 0   0 0 0    0 0      이런형태가 해당풀이에선 문제..
 *    0               0        0
 * 
 * 7. 해당 조건들은 따로 구해줄것. dfs를 다음 이동 좌표가 아닌 현재좌표로 보내기
 */

import java.util.*;
import java.io.*;

public class 테트로미노 {

    public static int n,m,answer;
    public static int[][] map;
    public static int[][] dirXY = {{0,1},{0,-1},{1,0},{-1,0}};
    public static boolean[][] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                visited[i][j] = true;
                dfs(1,i,j,map[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(answer);
    }

    public static void dfs(int stage, int i, int j, int temp){
        if(stage == 4){
            answer = Math.max(answer,temp);
            return;
        }

        for(int idx = 0; idx < 4; idx++){
            int ni = i + dirXY[idx][0];
            int nj = j + dirXY[idx][1];

            if(ni < 0 || ni >= n || nj < 0 || nj >= m || visited[ni][nj] == true){
                continue;
            }
            visited[ni][nj] = true;
            dfs(stage+1,ni,nj,temp+map[ni][nj]);
            dfs(stage+1,i,j,temp+map[ni][nj]); // 위에서 특수조건 처리
            visited[ni][nj] = false;
        }
    }
}
