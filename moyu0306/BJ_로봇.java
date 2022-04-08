import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dy = new int[]{-1,0,1,0};// 북 동 남 서
    static int[] dx = new int[]{0,1,0,-1};
    static int M,N;
    static int[][] map;
    static int [][][] isVisited;
    static int[][] inputs;
    static int min;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        M = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);
        min = Integer.MAX_VALUE;
        map = new int[M][N];
        isVisited = new int[M][N][4];
        inputs = new int[2][3];
        Queue<State> q = new LinkedList<>();
        for(int i=0; i<M; i++){
            for(int j=0; j<N;j++) Arrays.fill(isVisited[i][j],Integer.MAX_VALUE);
        }
        for(int i=0; i<M; i++){
            String[] nums = br.readLine().split(" ");
            for(int j=0; j<N;j++){
                map[i][j] = Integer.parseInt(nums[j]);
            }
        }
        for(int i=0; i<2; i++) { // 처음 위치 나중위치 설정, 주어진 방향을 사용하게 쉽도록 인덱스 변경
            String[] tmp = br.readLine().split(" ");
            inputs[i][0] = Integer.parseInt(tmp[0])-1;
            inputs[i][1] = Integer.parseInt(tmp[1])-1;
            int inputdir = Integer.parseInt(tmp[2]);
            if(inputdir == 1) inputs[i][2] = 1;
            else if(inputdir == 2)inputs[i][2] = 3;
            else if(inputdir == 3)inputs[i][2] = 2;
            else inputs[i][2] = 0;
        }
        q.offer(new State(inputs[0][0],inputs[0][1],inputs[0][2],0));

        while(!q.isEmpty()){
            State s = q.poll();
            if(s.y ==inputs[1][0] && s.x == inputs[1][1]){
                int addMove = Math.abs(inputs[1][2] - s.dir);
                if(addMove >2) addMove = 1;
                min = Integer.min(s.cnt+addMove,min);
            }
            int[] dirs = new int[4];
            dirs[0] = s.dir;
            dirs[1] = (s.dir+3)%4;
            dirs[2] = (s.dir+1)%4;
            dirs[3] = (s.dir+2)%4;

            for(int i=0; i<4; i++){
                for(int j=1; j<4; j++){
                    int d = dirs[i];
                    int orderCnt = (i==0) ? 1
                            :(i == 3) ? 3
                            : 2;
                    int posY = s.y + j * dy[d];
                    int posX = s.x + j * dx[d];
                    if(posY<0||posX<0||posY>=M||posX>=N||map[posY][posX] == 1) break;
                    if(isVisited[posY][posX][d]>s.cnt+orderCnt){
                        isVisited[posY][posX][d] = s.cnt+orderCnt;
                        q.offer(new State(posY,posX,d,s.cnt+orderCnt));
                    }
                }
            }
        }
        System.out.println(min);
    }

 }
 class State{
    int x;
    int y;
    int dir;
    int cnt;
    public State(int y, int x, int dir, int cnt){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.cnt = cnt;
    }
 }