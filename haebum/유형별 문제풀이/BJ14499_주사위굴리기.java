/*
    구현

    1. 명령이 맵내의 위치로 이동할 경우 bfs 이동

    2. 굴린거에따른 주사위 면 숫자 변경

    3. 현재 위치의 맵이 0이면 주사위 바닥면 맵에 전송.
        아니면 맵 숫자를 주사위 바닥면으로 변경. 맵은 0으로 처리
    
    4. 3번 해당내용 할때마다 주사위 상단면 출력
*/

import java.util.*;
import java.io.*;

public class BJ14499_주사위굴리기 {

    public static int[][] map;
    public static int n,m;
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
        // 주사위 위치
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        // 명령어 개수
        int k = Integer.parseInt(st.nextToken());


        // 맵 채우기
        map = new int[n][m];

        for(int i = 0; i < n; i++){
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st2.nextToken());
            }
        }

        // 명령어 실행
        StringTokenizer st3 = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++){
            int command = Integer.parseInt(st3.nextToken());

            // 명령어 이동 주사위 위치
            int newX = dirX[command] + x;
            int newY = dirY[command] + y;
            // 주사위가 맵 안에 있을때
            if(0 <= newX && newX < n && 0 <= newY && newY < m){
                // 주사위 위치 변동
                x = newX;
                y = newY;
                // 주사위 굴린대로 면 변경
                roll(command);
                // 현재 맵 숫자에 따른 로직 처리
                if(map[x][y] != 0){
                    dice[5] = map[x][y];
                    map[x][y] = 0;
                }else{
                    map[x][y] = dice[5];
                }
                // 윗면 출력
                bw.write(dice[4] + "\n");
            }
        }
        bw.flush();
        bw.close();
    }

    public static void roll(int c){
        //주사위(상단,하단,오른쪽,왼쪽,윗면,아랫면)
        // 오른쪽 굴리기(기존 상하단 동일, 윗면 -> 오른쪽면, 아랫면 -> 왼쪽면, 왼쪽면 -> 윗면, 오른쪽면 -> 아랫면)
        if(c == 1){
            int temp = dice[2];
            dice[2] = dice[4];
            dice[4] = dice[3];
            dice[3] = dice[5];
            dice[5] = temp;
        // 왼쪽
        }else if(c == 2){
            int temp = dice[2];
            dice[2] = dice[5];
            dice[5] = dice[3];
            dice[3] = dice[4];
            dice[4] = temp;
        // 위로
        }else if(c == 3){
            int temp = dice[0];
            dice[0] = dice[4];
            dice[4] = dice[1];
            dice[1] = dice[5];
            dice[5] = temp;
        // 아래로
        }else{
            int temp = dice[0];
            dice[0] = dice[5];
            dice[5] = dice[1];
            dice[1] = dice[4];
            dice[4] = temp;
        }
    }
}
