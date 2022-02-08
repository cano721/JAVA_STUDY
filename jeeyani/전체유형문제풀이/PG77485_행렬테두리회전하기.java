package 전체유형문제풀이;

import java.util.ArrayList;
import java.util.Collections;

/*구현*/

public class PG77485_행렬테두리회전하기 {

	public static void main(String[] args) {
		int rows=6;
		int columns=6;
		int[][] queries = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};

		int[] result = solution(rows,columns,queries);
		for(int res : result) {
			System.out.println(res+" ");
		}
		

	}

	private static int[] solution(int rows, int columns, int[][] queries) {
		 int resCnt = queries.length;
			
			int[] answer = new int[resCnt] ;
	        
	        //행렬사이즈에 맞춰 값 넣어두기
	        int[][] map = new int[rows][columns];
	        int num = 1;
	        for(int i=0; i<rows; i++){
	            for(int j=0; j<columns; j++){
	                map[i][j] = num++; 
	            }
	        }
	        
	        for(int i=0; i<rows; i++){
	            for(int j=0; j<columns; j++){
	                System.out.print(map[i][j]+" ");
	            }
	            System.out.println();
	        }
	        
	        for(int k = 0; k<queries.length; k++){
	        	int x1 = queries[k][0] - 1;
	            int y1 = queries[k][1] - 1;
	            int x2 = queries[k][2] - 1;
	            int y2 = queries[k][3] - 1;
	            
	            int temp = map[x1][y1];
	            //int numMin = Integer.MAX_VALUE;
	            int numMin = temp;
	            
	            for(int i = x1; i < x2; i++){
	                map[i][y1] = map[i+1][y1];
	                numMin = Math.min(numMin,map[i][y1]);
	            }
	            
	            for(int i = y1; i < y2; i++){
	                map[x2][i] = map[x2][i+1];
	                numMin = Math.min(numMin,map[x2][i]);
	            }
	            
	            for(int i = x2; i > x1; i--){
	                map[i][y2] = map[i-1][y2];
	                numMin = Math.min(numMin,map[i][y2]);
	            }
	            
	            for(int i = y2; i > y1; i--){
	                map[x1][i] = map[x1][i-1];
	                numMin = Math.min(numMin,map[x1][i]);
	            }
	            //마지막은 맨 처음 저장해둔 값 넣어주기 
	            map[x1][y1+1] = temp;
	            
	            answer[k] = numMin;
	        }
	        
	        return answer;
	}

}
