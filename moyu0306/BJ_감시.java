package com.company;
import javax.naming.InitialContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
    static char[][] map;
    static int N;
    static int M;
    static ArrayList<int[]> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        list = new ArrayList<>();
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new char[N][M];

        for(int i=0; i<N; i++){
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<M; j++){
                if(map[i][j]!='0'&&map[i][j]!='6'){
                    list.add(new int{i,j});
                }
            }
        }

    }

    public char[][] copyMap(char[][] map){
        char[][] copy = new char[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                copy[i][j] = map[i][j];
            }
        }
        return copy;
    }

    public int NumBlindSpot(int num ,char[][] map, int dir){
        char[][] cmap = copyMap(map);
    }


}