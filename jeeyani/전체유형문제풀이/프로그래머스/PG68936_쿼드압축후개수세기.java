package 전체유형문제풀이.프로그래머스;

import java.util.*;


public class PG68936_쿼드압축후개수세기 {

	public static void main(String[] args) {

		int[][] arr= {{1,1,0,0},{1,0,0,0,},{1,0,0,1},{1,1,1,1}};

		int[] result = solution(arr);

		System.out.println(result);

	}

	static int[] answer;
	private static int[] solution(int[][] arr) {
		answer = new int[2];
        
        
        Quadtree(arr.length, 0,0,arr);
        
        return answer;
	}
	
	public static void Quadtree(int num, int x, int y, int[][] arr){
       
        if(num == 1){
            answer[arr[x][y]]++;
            return;
        }
        //압축할 수 있는지 확인
        if(isBlock(num, x, y, arr)) return;
        
        //분할하여 확인
        Quadtree(num/2, x,y,arr);
        Quadtree(num/2, x+num/2 , y, arr);
        Quadtree(num/2, x, y+num/2, arr);
        Quadtree(num/2, x+num/2 ,y+num/2, arr);
    }
    
    public static boolean isBlock(int num, int x, int y, int[][] arr){
        for(int i =x; i<x+num; i++){
            for(int j = y; j<y+num; j++){
                if(arr[x][y] != arr[i][j]) return false;
            }
        }
        answer[arr[x][y]]++;
        return true;
    }
	

}