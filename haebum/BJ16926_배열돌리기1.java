/*
    구현

    1. 겉에서부터 안쪽으로 집합이 돌아감 (0,0 .. 1,1 ..)

    2. 집합의 개수는 행과 열중 작은것의 반임.(겉에 한바퀴씩 도는거이므로 두줄씩) Math.min(n,m)/2

    2. 첫열은 좌측으로 이동, 첫행은 아래로, 마지막열은 우측, 마지막행은 위로 이동

    3. R번만큼 이동 시킨 후 출력

*/

import java.util.*;
import java.io.*;

public class BJ16926_배열돌리기1 {

    public static int[][] map;
    public static int n,m,r,num;
    // 동서남북 이동
    public static int[] dirX = {0,0,0,-1,1};
    public static int[] dirY = {0,1,-1,0,0};
    // 초기 주사위(상단,하단,오른쪽,왼쪽,윗면,아랫면)
    public static int[] dice = {0,0,0,0,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 맵 크기
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        // 회전 수
        r = Integer.parseInt(st.nextToken());

        // 맵 채우기
        map = new int[n][m];

        for(int i = 0; i < n; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st2.nextToken());
            }
        }

        // 집합그룹의 수는 가로행과 세로행 중 작은것의 절반
        num = Math.min(n,m) / 2;

        // 회전수만큼 돌기
        for(int rc = 0; rc < r; rc++){
            move();
        }
        // 출력
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                bw.write(map[i][j]+" ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    public static void move(){
        // 집합수만큼 돌기(첫번째집합은 0,0 두번째집합은 1,1 이렇게 돔)
        for(int i = 0; i < num; i++){
            // 처음 원소값 빼두기
            int temp = map[i][i];
            // 첫열 돌기
            for(int a = i; a < m-i-1; a++){
                map[i][a] = map[i][a+1];
            }
            // 마지막행 돌기
            for(int a = i; a < n-i-1; a++){
                map[a][m-i-1] = map[a+1][m-i-1];
            }
            // 마지막열 돌기
            for(int a = m-i-1; a >= i+1; a--){
                map[n-i-1][a] = map[n-i-1][a-1];
            }
            // 첫행 돌기
            for(int a = n-i-1; a >= i+1; a--){
                map[a][i] = map[a-1][i];
            }
            // 첫번째원소는 덮어서 값이 변경되었기에 다시 제대로된 값을 넣어주기
            map[i+1][i] = temp;
        }
    }
}
