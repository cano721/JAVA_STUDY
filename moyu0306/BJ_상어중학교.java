package com.company;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N,M; // static 사용할거면 main에 선언하지마...ㅠㅠ
    static int[][] map;
    static int[] dx = new int[]{-1,1,0,0};
    static int[] dy = new int[]{0,0,-1,1};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][N];
        for(int i= 0 ;i <N; i++){
            String[] nums = br.readLine().split(" ");
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(nums[j]);
            }
        }

        int points =0;
        while(true){
            PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) ->{ // block 수, 무지개수, color
                return x[0] != y[0] ? y[0] - x[0] :
                        x[1] == y[1] ? y[1] -x[1] :
                        x[3] == y[3] ? x[4] - y[4] :
                                       y[3] - x[3];

            });
            int[][] isVisited = new int[N][N];
            int idx = 1;
            for(int i=0; i<N; i++){
                for(int j=0 ;j<N; j++){
                     cnt(idx++,i,j,isVisited, pq);
                    }
                }

            if(!pq.isEmpty()){
                int[] cand = pq.poll();
                if(cand[0]<=1) break;
                int point =remove(isVisited,cand[2]);
                System.out.println(point);
                points+=point*point;
                debug();
                gravity();
                rotateCounter();
                gravity();
                debug();

        }else break;

        }


        System.out.println(points);




    }



    public static void cnt(int idx,int row, int col, int[][] isVisited, PriorityQueue<int[]> pq){ // -2일 때 확인
        if(isVisited[row][col]>0|| map[row][col]<=0) return;
        int cnt =1;
        int rainbow = 0;
        int color = map[row][col];

        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{row,col});
        isVisited[row][col]= idx;
        while(!q.isEmpty()){

            int[] pos = q.poll();
            int y = pos[0];
            int x = pos[1];
            for(int i= 0; i<4; i++){
                int posY = y + dy[i];
                int posX = x + dx[i];
                if(posY>=N||posX>=N||posY<0||posX<0) continue;
                if(isVisited[posY][posX] == idx) continue;
                if(map[posY][posX] == 0 || map[posY][posX] == color){
//                    System.out.println(idx+" "+ map[posY][posX]);
                    isVisited[posY][posX] = idx;
                    q.offer(new int[]{posY,posX});
                    cnt++;
                    if(map[posY][posX]==0) rainbow++;
                }

            }
        }

       pq.offer(new int[]{cnt,rainbow,idx,row,col});
    }

    public static int remove(int[][] isVisited, int idx){
        int cnt =0;
        for(int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {
//                System.out.println("out");

                if(isVisited[i][j] != idx ) continue;
                for (int k = 0; k < 4; k++) {
                    int posY = i + dy[k];
                    int posX = j + dx[k];
                    if (posY >= N || posX >= N || posY < 0 || posX < 0) continue;
                    if (map[posY][posX] == 0) isVisited[posY][posX] = idx;
                }
            }
        }
        for(int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                    if (isVisited[i][j] == idx) {
                    System.out.println(i+" "+j+" "+idx);
                    map[i][j] = -2;
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void gravity(){
        for(int j=0; j<N; j++){
            for(int i=N-2; i>=0; i--){
                if(map[i][j]>=0){
                    for(int k =i+1; k<N; k++){
                        if(map[k][j]== -1 ||map[k][j]>=0){ swap(i,j,k-1,j); break;}
                        if(k == N-1) swap(i,j,k,j);

                    }
                }
            }
        }
    }

    public static void rotateCounter(){
        int[][] cmap = new int[N][N];
        for(int i=0; i< N; i++){
            for(int j=0; j<N;j++){
                cmap[N-1-j][i]= map[i][j];
            }
        }
        map = cmap;
    }


    public static void swap(int r1, int c1, int r2, int c2){
        int tmp = map[r1][c1];
        map[r1][c1] = map[r2][c2];
        map[r2][c2] = tmp;
    }


    public static void debug(){
        for(int i=0; i<N; i++){
            for(int j=0; j<N;j++){
                System.out.print(map[i][j]+ " ");
            }
            System.out.println("");

        }
        System.out.println("");
    }
}
