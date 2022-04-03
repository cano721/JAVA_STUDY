package 전체유형문제풀이.프로그래머스;

import java.util.*;


/*
 * 
 * (i,j) 위치의 값 = Math.min((i,j-1), (i-1,j), (i-1,j-1)) + 1
 * 
 * but, (i,j) = 0이라면 제외
 * 
 * 
 * https://soobarkbar.tistory.com/164
 * 
 * */

public class PG12905_가장큰정사각형찾기 {

	public static void main(String[] args) {

		int[][] board = {{0,1,1,1},{1,1,1,1},{1,1,1,1},{0,0,1,0}};

		int result = solution(board);

		System.out.println(result);

	}

	private static int solution(int[][] board) {
		int answer = 0;
        int n = board.length;
        int m = board[0].length;
        
        
        int[][] map = new int[n+1][m+1];
        for(int i = 0; i< n; i++){
            for(int j = 0; j< m; j++){
                map[i+1][j+1] = board[i][j];
            }
        }
        
        for(int i = 1; i<= n; i++){
            for(int j = 1; j<= m; j++){
                if(map[i][j] != 0){
                    map[i][j] = Math.min(Math.min(map[i-1][j], map[i][j-1]), map[i-1][j-1]) +1;
                    answer = Math.max(answer, map[i][j]);
                }
            }
        }
        

        return answer*answer;
	}
	
	
}