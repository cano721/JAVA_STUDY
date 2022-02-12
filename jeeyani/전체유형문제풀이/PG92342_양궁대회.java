package 전체유형문제풀이;

import java.util.*;

/*
 * [완전탐색 - 백트래킹]
 * 
 * https://taehoung0102.tistory.com/160
 * */

public class PG92342_양궁대회 {

	public static void main(String[] args) {

		int n = 5;
		int[] info = { 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0 };

		int result[] = solution(n, info);

		for (int ans : result) {
			System.out.print(ans + " ");
		}

	}
	static boolean[] visited = new boolean[11];
    static int sumApeach = 0;
    static int diffMax = 0;
    
    static int[] needArrow = new int[11];
    static int[] result = new int[11];
    
	public static int[] solution(int n, int[] info) {
		//어피치가 맞힌 화살 갯수보다 +1씩 추가해주기
        for(int i = 0; i<11; i++){
        	needArrow[i] = info[i]+1;
            
            //어차피가 획득한 점수 계산
            if(info[i] != 0) sumApeach += 10-i;
        }
        
        needArrow[10] = 0;
        
        getRyan(0,11,n);
        
        //이기는 방법이 없는 경우 -1 반환
        if(diffMax == 0) return new int[]{-1};
        
        return result;

	}
	
	public static void getRyan(int start, int arrow, int n){
        
        //탈출조건: 화살이 남았을 경우
        if(arrow == 0){
            //라이언 점수 계산하기
            int sum = 0;
            int[] temp = new int[11];
            for(int i=0; i<11; i++){
                if(visited[i]){
                    temp[i] = needArrow[i];
                    
                    if(temp[i] == 1){
                        sum += 10-i;
                    }
                    else{
                        sum += (10-i)*2;
                    }
                }
            }
            temp[10] = n; //남은 화살 n은 0점에 맞추기
            
            if(diffMax == sum-sumApeach){
                for(int i=10;i>=0;i--){
                    if(result[i] > temp[i]){
                        break;
                    }
                    else if(result[i] == temp[i]){
                        continue;
                    }
                    else{
                        result = temp;
                    }
                }
            }
            else if(diffMax < sum-sumApeach){
               diffMax=Math.max(sum-sumApeach,diffMax);
               result = temp;
            }
            return;
        }
        
        //탈출조건: 화살을 모두 사용했을 경우
        if(n==0){
            int sum=0;
            int[] temp = new int[11];
            
            //라이언 점수 계산하기
            for(int i=0;i<11;i++){
                if(visited[i]){               
                    temp[i]=needArrow[i];
                    
                    if(temp[i]==1){
                        sum += 10-i;
                    }
                    else{
                        sum += (10-i)*2;
                    }
                }              
            }
            //점수차가 같은 경우
            if(diffMax == sum-sumApeach){
                for(int i=10;i>=0;i--){
                    if(result[i]>temp[i]){
                        break;
                    }
                    else if(result[i]==temp[i]){
                        continue;
                    }
                    else{
                        result=temp;
                    }
                }
            }
            //점수차가 작을 경우
            else if(diffMax < sum-sumApeach){
               diffMax = Math.max(sum-sumApeach,diffMax);
               result = temp;
            }
            return;
        }
        
    
        for(int i = start; i< 11; i++){
            
            //화살갯수 확인하면서 쏘기
            if(n >= needArrow[i]){
                visited[i] = true;
                n -= needArrow[i];
                
                getRyan(start+1, arrow-1, n);
                
                visited[i] = false;
                n += needArrow[i];
            }
            
        }
        
        
    }

}