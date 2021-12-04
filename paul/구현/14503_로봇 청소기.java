import java.util.*;
import java.io.*;

public class Main {

    static class Robot{
        int y, x, d, cnt;
        public Robot(int yy, int xx, int dd){
            y = yy;
            x =xx;
            d= dd;
            cnt = 0;
        }

        public void move(int yy, int xx){
            y +=yy;
            x +=xx;
        }

    }

    static int n, m;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int[][] board = new int[51][51];

    static Robot robot;
    public static void main(String[] args) throws Exception {
       input();
       pro();
    }

    public static void input() throws Exception{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        robot = new Robot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        for(int i =0; i<n; i++){
            st= new StringTokenizer(br.readLine());
            for(int j =0; j<m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
    }

    public static void pro(){
        while(true){

            // 현재 위치 청소  
            cleanArea(robot.y, robot.x);

            //showBoard();

            // 현재 방향 기준으로 왼쪽부터 차례대로 인접한 칸 탐색
            int check_dir  = serachArea();

            //탐색할 방향 못찾으면 뒤로 후진.
            if(check_dir == -1){
                int dd = moveBack();
                
                //후진 불가하면 동작 멈춤.
                if(dd == -1) break;

                //가능하면 로봇 움직임.
                robot.move(dy[dd],dx[dd]);

            }else{
                //탐색한 방향으로 로봇 방향 틀고 움직이기.
                robot.d = check_dir;
                robot.move(dy[robot.d], dx[robot.d]);
            }
        }

        System.out.println(robot.cnt);
    }
    
    
    public static void cleanArea(int y, int x){
        //y,x 청소..
        if(board[y][x] == 0) {
            board[y][x] = -1;
            robot.cnt += 1;
        }
    }

    public static int serachArea(){

        /**
         *  청소 가능한 구역이 있는지 탐색.. 
         *  청소 가능한 구역의 방향 (현재 방향 기준으로) return.
         *  모든 방향 불가능하면 -1 return.
         */
        int d = robot.d;
        for(int i =0; i<4; i++){
            d = d == 0? 3 : d-1;
            int yy = dy[d] + robot.y;
            int xx = dx[d] + robot.x;
            if(yy < 0 || xx<0 || yy>=n ||xx >=m) continue;
            if(board[yy][xx] == 0){
                return d;
            }
        }
        return -1;
    }

    public static int moveBack(){
        /**
         *  후진하는 함수. 
         *  뒤로 갈 수 있으면 뒷방향 return , 불가하면 -1 return.,
         */
        int dd = (robot.d+2)%4; // 0 <->2 , 1 <->3
        int y = robot.y + dy[dd], x = robot.x +dx[dd];
        if(y < 0 || x < 0 || y>=n || x>=m) return -1;
        if( board[y][x] == 1) {
            return -1;
        }{
            return dd;
        }
    }

    public static void showBoard(){
    
        for(int i =0; i<n; i++){
            for(int j =0; j<m; j++){
                System.out.print(board[i][j]+ " ");
            }
            System.out.println("");
        }
    }

}

