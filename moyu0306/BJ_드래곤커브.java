package com.company;
import javax.naming.InitialContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static int N;
    static boolean[][] map;
    static int[] dy = new int[]{0,-1,0,1};
    static int[] dx = new int[]{1,0,-1,0};
    static Stack<Integer> st;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new boolean[101][101];
        for(int i=0; i<N; i++){
            String[] nums = br.readLine().split(" ");
            makeDragon(Integer.parseInt(nums[0]),
                    Integer.parseInt(nums[1]),
                    Integer.parseInt(nums[2]),
                    0,
                    Integer.parseInt(nums[3])
                    );
        }
        System.out.println(countSquare());
    }

    public static int countSquare(){
        int count = 0;
        for(int i=0; i<100;i++){
            for(int j=0; j<100; j++){
                if(map[i][j]&&map[i][j+1]&&map[i+1][j]&&map[i+1][j+1]) count++;
            }
        }
        return count;
    }



    public static void makeDragon(int epcol,int eprow,int dir, int currentG, int objG){

        if(currentG == 0){
            st= new Stack<>();
            map[eprow][epcol] = true;
            st.push((dir+3)%4);
        }

        if(currentG == objG+1) return;

       int newEpRow = eprow;
       int newEpCol = epcol;
       int size = st.size();
       for(int i= size -1; i>=0; i--){
           int d = (st.get(i)+1)%4;
           newEpCol += dx[d];
           newEpRow += dy[d];
           map[newEpRow][newEpCol] = true;
           if(currentG==0) st.pop();
           st.push(d);
       }

       makeDragon(newEpCol,newEpRow,dir,currentG +1, objG);

    }
}