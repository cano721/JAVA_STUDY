/*
    1. bfs를 통해 인접국가 확인
    2. 모든나라를 돌면서 연합확인(총합계 및 나라개수도 카운트)
    3. 연합국을 돌면서 총합/나라수 숫자로 다시 집어넣기
    4. 다음날 실행

*/

import java.util.*;
import java.io.*;

public class 인구이동 {

    // 북동남서
    public static int[] dirX = {-1,0,1,0};
    public static int[] dirY = {0,1,0,-1};
    public static int n,l,r,p_sum,c_num;
    public static int[][] map, union;
    public static boolean check;
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 맵 크기
        n = Integer.parseInt(st.nextToken());
        // 인구수비교
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        // 맵초기화
        map = new int[n][n];
        for(int i = 0; i < n; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st2.nextToken());
            }
        }
        // 인구이동
        int cnt = 0;
        while(true){
            union = new int[n][n]; // 연합맵
            int c_name = 0; // 연합이름
            check = false; // 한번이라도 연합했는지
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    // 아직 연합을 안한 국가면
                    if(union[i][j] == 0){
                        // 연합이름 생성
                        p_sum = map[i][j]; // 연합인구수 합계
                        c_num = 1; // 연합국가수
                        union[i][j] = ++c_name;
                        unions(i,j); // 연합국 확인
                        if(c_num > 1){
                            int n_p = p_sum/c_num; // 새로운 인구수
                            move(union[i][j],n_p); // 새로운 인구로 넣기
                        }
                   }
                }
            }
            // 한곳이라도 연합 이루어진곳 없으면 탈출
            if(check == false) break;
            cnt++; // 날짜 증가
        }
        System.out.println(cnt);
    }

    // 연합국확인 함수
    static void unions(int i, int j){
        Queue<int[]> q = new LinkedList<>();
        
        q.offer(new int[] {i,j});

        // bfs 돌기(같은 연합구하기)
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for(int idx = 0; idx < 4; idx++){
                int nx = x + dirX[idx];
                int ny = y + dirY[idx];
                // 맵안에 있고 연합하지 않은 국가면
                if(0 <= nx && nx < n && 0 <= ny && ny < n && union[nx][ny] == 0){
                    int population_gap = Math.abs(map[x][y] - map[nx][ny]);
                    // 인구수차가 원하는 조건안이면
                    if(l <= population_gap && population_gap <= r){
                        // 연합 한번이라도 했는지 체크
                        check = true;
                        // 연합으로 추가
                        union[nx][ny] = union[x][y];
                        p_sum += map[nx][ny];
                        c_num++;
                        q.offer(new int[] {nx,ny});
                    }
                }
            }
        }
    }

    // 새로운 인구수 넣기 함수
    static void move(int c_name,int n_p){
        for(int a = 0; a < n; a++){
            for(int b = 0; b < n; b++){
                // 같은 연합국이면 새로운 인구로 변동
                if(union[a][b] == c_name){
                    map[a][b] = n_p;
                }
            }
        }
    }
}
