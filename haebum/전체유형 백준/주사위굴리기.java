
/**
 * 1. 처음 주사위는 모든 면이 0
 * 
 * 2. 지도를 이동하며 지도 위의 숫자가 0이 아니면 주사위 바닥면 복사.
 * 
 * 3. 숫자가 0이면 주사위 바닥면이 지도로 복사.
 * 
 * 4. 이동을 진행했을때마다 윗면에 쓰인 값을 출력
 */

import java.io.*;
import java.util.*;

public class 주사위굴리기 {
    public static class Dice{
        int x;
        int y;
        // 주사위(윗면,바닥면,오른쪽면,왼쪽면,상단면,하단면)
        int[] stat = new int[6];
        public Dice(int x, int y){
            this.x = x;
            this.y = y;
        }

        public void change(int command){
            if(command == 1){
                int temp = this.stat[0];
                this.stat[0] = this.stat[3];
                this.stat[3] = this.stat[1];
                this.stat[1] = this.stat[2];
                this.stat[2] = temp;
            }else if(command == 2){
                int temp = this.stat[0];
                this.stat[0] = this.stat[2];
                this.stat[2] = this.stat[1];
                this.stat[1] = this.stat[3];
                this.stat[3] = temp;
            }else if(command == 3){
                int temp = this.stat[0];
                this.stat[0] = this.stat[4];
                this.stat[4] = this.stat[1];
                this.stat[1] = this.stat[5];
                this.stat[5] = temp;
            }else{
                int temp = this.stat[0];
                this.stat[0] = this.stat[5];
                this.stat[5] = this.stat[1];
                this.stat[1] = this.stat[4];
                this.stat[4] = temp;
            }
        }

        public void mapCopy(int num){
            this.stat[1] = num;
        }

        public int bottomface(){
            return this.stat[1];
        }

        public int topface(){
            return this.stat[0];
        }
    }

    public static int[][] map;
    public static int n,m;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        n =Integer.parseInt(st.nextToken());
        m =Integer.parseInt(st.nextToken());
        int x =Integer.parseInt(st.nextToken());
        int y =Integer.parseInt(st.nextToken());
        int k =Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Dice dice = new Dice(x,y);

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < k; i++){
            int command = Integer.parseInt(st.nextToken());
            if(move(dice,command)){
                bw.write(dice.topface() +"\n");
            }
        }
        bw.flush();
        bw.close();
    }


    public static boolean move(Dice dice, int command){
        // 1~4 동,서,북,남 으로 돔.
        int[][] dirXY = {{0,0},{0,1},{0,-1},{-1,0},{1,0}};

        int nx = dice.x + dirXY[command][0];
        int ny = dice.y + dirXY[command][1];

        if(nx < 0 || nx >= n || ny < 0 || ny >= m){
            return false;
        }

        dice.change(command);
        dice.x = nx;
        dice.y = ny;

        if(map[nx][ny] == 0){
            map[nx][ny] = dice.bottomface();
            
        }else{
            dice.mapCopy(map[nx][ny]);
            map[nx][ny] = 0;
        }

        return true;
    }
}
