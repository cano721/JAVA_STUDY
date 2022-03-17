/*
    1. 벽 3개 건설 (dfs)

    2. 바이러스 퍼트리기

    3. 안전 영역 개수 파악

    4. 모든 경우의 수에서 제일 큰 안전영역의 개수 반환

    시간복잡도: 3개벽짓기(8^2^3) * 탐색(8^2) == 8^8
*/


import java.io.*;
import java.util.*;

public class 연구소 {

    public static int n,m, result;
    public static int[][] map;
    public static int[][] dirXY = {{0,1},{0,-1},{1,0},{-1,0}};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        createWall(0);

        bw.write(result +"\n");
        bw.flush();
        bw.close();
    }

    public static void createWall(int stage){

        if(stage == 3){
            spreadVirus();
            return;
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] == 0){
                    map[i][j] = 1;
                    createWall(stage+1);
                    map[i][j] = 0;
                }
            }
        }
    }

    public static void spreadVirus(){
        Queue<int[]> q = new LinkedList<>();

        int safeZone = 0;
        // 원본 맵이 바뀌지 않기 위해 복사해서 씀
        int[][] newMap = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                newMap[i][j] = map[i][j];
                if(newMap[i][j] == 2){ // 바이러스 큐에 다 집어넣기
                    q.offer(new int[] {i,j});
                }

                if(newMap[i][j] == 0){ // 전체 안전개수 세기
                    safeZone++;
                }
            }
        }

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for(int i = 0; i < 4; i++){
                int nx = x + dirXY[i][0];
                int ny = y + dirXY[i][1];
                // 맵 벗어났으면 탈출
                if(nx < 0 || nx >= n || ny < 0 || ny >= m){
                    continue;
                }
                // 빈칸이면 바이러스 퍼트리기
                if(newMap[nx][ny] == 0){
                    newMap[nx][ny] = 2;
                    q.offer(new int[]{nx,ny});
                    safeZone--; // 안전영역 개수 감소
                }
            }
        }

        // 다 돌았을 때 현재 벽3개 지은 경우의 수의 안전개수 체크
        result = Math.max(result,safeZone);
    }

}
