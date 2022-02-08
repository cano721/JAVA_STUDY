import java.util.*;
class Solution {

    static int[][] board;
    static int[] dy = {1,0,-1,0};
    static int[] dx=  {0,1,0,-1};

    public static int rotate(int y1, int x1, int y2, int x2){
        int tmp = board[y1][x1];
        int idx = 0;
        int min = tmp;
        int y= y1, x = x1;
        while(idx < 4){
            int yy = dy[idx] + y, xx = dx[idx] +x;
            if(yy < y1 || yy > y2 || xx<x1 || xx>x2){
                idx++;
            }else{
                board[y][x] = board[yy][xx];
                y = yy;
                x = xx;
                min = Math.min(min, board[y][x]);
            }
        }
        board[y1][x1+1] = tmp;

        return min;
    }

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        board = new int[rows][columns];
        int idx =1;
        for(int i=0; i< rows; i++){
            for(int j=0; j< columns; j++){
                board[i][j] = idx++; 
            }
        }

        for(int i=0; i<queries.length; i++){
            answer[i] = rotate(queries[i][0]-1, queries[i][1]-1,
                               queries[i][2]-1, queries[i][3]-1);

        }

        return answer;
    }
}
  