import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] map = new int[rows+1][columns+1];
        
        int num = 1;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                map[i][j] = num++;
            }
        }

        for (int q = 0; q < queries.length; q++) {
            int x1 = queries[q][0];
            int y1 = queries[q][1];
            int x2 = queries[q][2];
            int y2 = queries[q][3];

            int min = Integer.MAX_VALUE;
            int[][] tmp = new int[rows + 1][columns + 1];

            for(int i = 1; i <= rows; i++){
                for(int j = 1; j <= columns; j++){
                    tmp[i][j] = map[i][j];
                }
            }

            for(int i = y1 + 1; i <= y2; i++){
                map[x1][i] = tmp[x1][i - 1];
                min = Math.min(min, map[x1][i]);
            }            
            for(int i = x1 + 1; i <= x2; i++){
                map[i][y2] = tmp[i - 1][y2];
                min = Math.min(min, map[i][y2]);
            }
            for(int i = y1; i < y2; i++){
                map[x2][i] = tmp[x2][i + 1];
                min = Math.min(min, map[x2][i]);
            }
            for(int i = x1; i < x2; i++){
                map[i][y1] = tmp[i + 1][y1];
                min = Math.min(min, map[i][y1]);
            }
            answer[q] = min;   
        }
        return answer;
    }
}
