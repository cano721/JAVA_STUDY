package com.company;
import javax.naming.InitialContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C ,M;
    static int[] dy = new int[]{-1,0,1,0};
    static int[] dx = new int[]{0,1,0,-1};
    static Shark[][] map;
    static int cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        M = Integer.parseInt(input[2]);
        map = new Shark[R][C];
        cnt = 0;
        for(int i=0; i<M; i++){
            String[] sInfo = br.readLine().split(" ");
            int r = Integer.parseInt(sInfo[0]) -1;
            int c = Integer.parseInt(sInfo[1]) -1;
            int s = Integer.parseInt(sInfo[2]);
            int d = Integer.parseInt(sInfo[3]);
            int z = Integer.parseInt(sInfo[4]);
            if(d == 1) d = 0;
            else if(d==2) d=2;
            else if(d==3) d=1;
            else if(d==4) d=3;
            map[r][c] = new Shark(s,z,d);
        }
        debug();
        for(int i=0; i<C; i++){
            Shark[][] tmap = new Shark[R][C];
            catchShark(i);
            for(int l=0; l<R; l++){
                for(int k = 0; k<C; k++){
                    if(map[l][k]!= null) moveShark(l, k, tmap);
                }
            }
            map = tmap;
            debug();
        }

        System.out.println(cnt);

    }
    public static void catchShark(int c){
        for(int i=0; i<R; i++){
            if(map[i][c]!=null){
                cnt += map[i][c].z;
                map[i][c] = null;
                break;
            }
        }
    }
    public static void moveShark(int r, int c, Shark[][] tmap){
        Shark shark = map[r][c];
        int posY = r + dy[shark.d] * shark.s;
        int posX = c + dx[shark.d] * shark.s;


//        int aPosY = Math.abs(posY);
//        int yWall = aPosY/(R-1);
//        if(posY<0) yWall+=1;
//        int yLeft = aPosY%(R-1);
//        if(yWall%2 == 1) shark.d = (shark.d+2)%4;
//        if(shark.d == 2) posY = yLeft;
//        else if(shark.d == 0) posY = (R-1)- yLeft;
//
//        int aPosX = Math.abs(posX);
//        int xWall = aPosX/(C-1);
//        if(posX<0) xWall+=1;
//        int xLeft = aPosX%(C-1);
//        if(xWall%2 == 1) shark.d = (shark.d+2)%4;
//        if(shark.d == 1) posX = xLeft;
//        else if(shark.d == 3) posX = (C-1)- xLeft;

        if(tmap[posY][posX]!= null){
            if(tmap[posY][posX].z <shark.z) tmap[posY][posX] = shark;
        }else{
            tmap[posY][posX] = shark;
        }
        tmap[r][c] =null;
    }

    public static void debug(){
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(map[i][j]!=null) System.out.print(map[i][j].z+" ");
                else System.out.print(-1 +" ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

}
class Shark{
    int s;
    int z;
    int d;
    public Shark(int s, int z, int d){
//        super();
        this.s = s;
        this.z = z;
        this.d = d;
    }
}