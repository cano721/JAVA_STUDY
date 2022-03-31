/**
 * 1. 맵 생성
 * 
 * 2. 각 모듈화를 통해 구현할 예정
 */

import java.util.*;
import java.io.*;

public class 마법사상어와비바라기 {
    public static int n,m;
    public static int[][] map;
    public static Queue<int[]> cloud;
    public static int[][] dirXY = {{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
    public static boolean[][] disappear;
    public static int result;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        startRain();
        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken())-1; // 0부터 시작하기위해 -1
            int s = Integer.parseInt(st.nextToken());
            moveCloud(d,s); // 구름 이동
            plusWater(); // 물 증가
            copyWater(); // 물복사 마법
            createCloud(); // 구름 생성
        }
        System.out.println(result);
    }

    public static void startRain(){
        cloud = new LinkedList<>();

        // 0,0으로 맞춰줘야해서 -1씩 해줌
        cloud.offer(new int[] {n-1,0});
        cloud.offer(new int[] {n-1,1});
        cloud.offer(new int[] {n-2,0});
        cloud.offer(new int[] {n-2,1});
    }

    public static void moveCloud(int d, int s){
        int size = cloud.size();

        for(int i = 0; i < size; i++){
            int[] cur = cloud.poll();
            int x = cur[0];
            int y = cur[1];

            int nx = (x + dirXY[d][0]*(s%n) + n) %n; // 음수나 n범위 넘어가는것 처리
            int ny = (y + dirXY[d][1]*(s%n) + n) %n;

            cloud.offer(new int[] {nx,ny});
        }
    }

    public static void plusWater(){
        int size = cloud.size();
        disappear = new boolean[n][n]; // 사라지는 것들 체크
        for(int i = 0; i < size; i++){
            int[] cur = cloud.poll();
            int x = cur[0];
            int y = cur[1];

            map[x][y]++;
            cloud.offer(new int[] {x,y});
            disappear[x][y] = true;
        }
    }

    public static void copyWater(){
        while(!cloud.isEmpty()){
            int[] cur = cloud.poll();
            int x = cur[0];
            int y = cur[1];

            int cnt = 0;

            for(int i = 1; i < 8; i += 2){ // 대각선 돌기
                int nx = x + dirXY[i][0];
                int ny = y + dirXY[i][1];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n){ // 맵 벗어나면 무시
                    continue;
                }
                if(map[nx][ny] > 0){ // 물 있으면 카운트증가
                    cnt++;
                }
            }

            map[x][y] += cnt; // 카운트만큼 물 증가
        }
    }

    public static void createCloud(){
        result = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(map[i][j] >= 2 && !disappear[i][j]){ // 이전에 사라진 자리가 아니고 물이 2이상이면 생성
                    cloud.offer(new int[]{i,j});
                    map[i][j] -= 2;
                }
                result += map[i][j];
            }
        }
    }
}
