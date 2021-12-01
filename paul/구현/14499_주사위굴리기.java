import java.util.*;
import java.io.*;
import java.nio.Buffer;


public class Main {
    
    static int n, m, y, x, k;
    static int[][] board = new int[21][21];
    static int[] command= new int[1005];
    static int[] dy = {0, 0,0,-1,1};
    static int[] dx = {0,1,-1,0,0};

    static class Dice{
        //주사위의 현재 위치.
        public int y, x;
        public int[] state = {0, 0, 0, 0, 0, 0, 0};
        Dice(int yy, int xx){
            y = yy;
            x = xx;
        }

        public void setX_Y(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void forward(int n){
            int[] tmp = new int[7];
            switch(n){
                case 1:
                    tmp[1] = state[4];
                    tmp[2] = state[2];
                    tmp[3] = state[1];
                    tmp[4] = state[6];
                    tmp[5] = state[5];
                    tmp[6] = state[3];
                break;
                case 2:
                    tmp[1] = state[3];
                    tmp[2] = state[2];
                    tmp[3] = state[6];
                    tmp[4] = state[1];
                    tmp[5] = state[5];
                    tmp[6] = state[4];
                break;
                case 3:
                    tmp[1] = state[5];
                    tmp[2] = state[1];
                    tmp[3] = state[3];
                    tmp[4] = state[4];
                    tmp[5] = state[6];
                    tmp[6] = state[2];
                break;
                case 4:
                    tmp[1] = state[2];
                    tmp[2] = state[6];
                    tmp[3] = state[3];
                    tmp[4] = state[4];
                    tmp[5] = state[1];
                    tmp[6] = state[5];
                break;                
            }

            for(int i=1; i<7; i++){
                state[i] = tmp[i];
            }
        }

    }

    public static void main(String[] args) throws Exception {
        
        input();
        pro();
    }

    public static void input() throws Exception{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       n = Integer.parseInt(st.nextToken());
       m = Integer.parseInt(st.nextToken());
       y = Integer.parseInt(st.nextToken());
       x = Integer.parseInt(st.nextToken());
       k = Integer.parseInt(st.nextToken());
       
       for(int i =0; i<n; i++){
           st = new StringTokenizer(br.readLine());
           for(int j=0; j< m; j++){
               board[i][j] = Integer.parseInt(st.nextToken());
           }
       }

       st = new StringTokenizer(br.readLine());
       for(int i =0; i<k; i++){
           command[i] = Integer.parseInt(st.nextToken());
       }
    }

    public static void pro(){
        
        //주사위의 위치 구현,
        Dice dice = new Dice(y, x);
        StringBuilder sb = new StringBuilder();
        for(int i =0; i< k; i++){
            int cy = dy[command[i]] + dice.y;
            int cx = dx[command[i]] + dice.x;

            if(cy < 0 || cy >= n || cx <0 || cx>=m) continue;
            dice.forward(command[i]);
            if(board[cy][cx] == 0) board[cy][cx] = dice.state[6];
            else {
                dice.state[6] = board[cy][cx];
                board[cy][cx] = 0;
            }

            dice.setX_Y(cx, cy);
            sb.append(dice.state[1]).append('\n');

        }
        
        System.out.println(sb);

    }
    
    
}

