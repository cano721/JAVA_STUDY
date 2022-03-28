package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
        static int[][] likes;
        static int[] dy = new int[]{-1,1,0,0};
        static int[] dx = new int[]{0,0,-1,1};
        static int[][] map;
        static int[] list;
        static int N;
      public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = N*N;
        likes = new int[M+1][4];
        map = new int[N][N];
        list = new int[M];
        for(int i=0; i<M; i++){
            String[] input = br.readLine().split(" ");
            list[i] = Integer.parseInt(input[0]);
            for(int j=0; j<4; j++){
                likes[list[i]][j] = Integer.parseInt(input[j+1]);
            }
        }

        for(int i=0; i<M; i++){
           int[][][] tmp = checkPos(list[i]);
           choosePos(list[i],tmp);
        }

        System.out.println(cnt());

    }

    public static int[][][] checkPos(int std){
          int[][][] tmp = new int[N][N][2];
          for(int i=0; i<N; i++){
              for(int j=0; j<N;j++){
                  if(map[i][j] == 0) {
                      for (int l = 0; l < 4; l++) {
                          int posY = i + dy[l];
                          int posX = j + dx[l];
                          if (posY >= N || posY < 0 || posX >= N || posX < 0) continue;
                          tmp[posY][posX][1]++;
                      }
                  }

                for(int k : likes[std]){
                    if(map[i][j]==k){
                        for(int l=0; l<4; l++){
                            int posY = i + dy[l];
                            int posX = j + dx[l];
                            if(posY>=N||posY<0||posX>=N||posX<0) continue;
                            tmp[posY][posX][0]++;
                        }
                    }

                }

              }
          }

          return tmp;
    }

    public static void choosePos(int std, int[][][] tmp){
          int r = 0;
          int c = 0;
          int friends = -1;
          int empty = -1;
//          System.out.println(std);
          for(int i=0; i<N; i++){
              for(int j=0; j<N; j++){
                 // System.out.print(tmp[i][j][0]+" "+tmp[i][j][1]+"  ");
                  if(map[i][j]>0) continue;
                  if(tmp[i][j][0]>friends||(tmp[i][j][0]==friends)&&(tmp[i][j][1]>empty)){
                      r =i; c =j; friends = tmp[i][j][0]; empty = tmp[i][j][1];
                  }
              }
//              System.out.println("");
          }
          //System.out.println(r+" "+c);
          //System.out.println("");
          map[r][c] = std;
    }


    public static int cnt(){
          int tot = 0;
          for(int i=0; i<N; i++){
              for(int j=0; j<N; j++){
                  int std = map[i][j];
                  int cnt =0;
                  for(int l=0; l<4; l++){
                      int posY = i + dy[l];
                      int posX = j + dx[l];
                      if(posY>=N||posY<0||posX>=N||posX<0) continue;
                      for(int k=0; k<4; k++){
                          if(map[posY][posX] == likes[std][k]) {cnt++; break;}
                      }
                  }
                  if(cnt == 1) tot+= 1;
                  else if(cnt == 2) tot+=10;
                  else if(cnt == 3) tot+=100;
                  else if(cnt==4) tot+=1000;
              }
          }
          return tot;
    }


}

