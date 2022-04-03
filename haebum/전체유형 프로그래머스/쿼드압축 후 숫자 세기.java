/**
    1. 분할정복으로 2^n * 2^n이 같은 숫자로 안되어있으면 분할
    
    2. 최종분할로 들어간게 같은 숫자로 이루어져있으면 체크
    
    3. 0과 1의 개수 반환
**/

class Solution {
    public int[] answer = new int[2];
    
    public int[] solution(int[][] arr) {
        
        slice(arr,0,0,arr.length);
        
        return answer;
    }
    
    public void slice(int[][] arr, int x, int y, int size){
        
        // 1개로 이루어져있으면 체크
        if(size == 1){
            int num = arr[x][y];
            answer[num]++;
            return;
        }
        
        // 전체 같은 숫자로 이루어져있는지 확인
        if(check(arr,x,y,size)){
            return;
        }
        
        int newSize = size/2;
        
        // 4등분
        slice(arr,x,y,newSize); // 1사분면
        slice(arr,x,y+newSize,newSize); // 2사분면
        slice(arr,x+newSize,y,newSize); // 3사분면
        slice(arr,x+newSize,y+newSize,newSize); // 4사분면
    }
    
    public boolean check(int[][] arr, int x, int y, int size){
        
        boolean temp = true;
        int num = arr[x][y];
        
        for(int i = x; i < x+size; i++){
            for(int j = y; j < y+size; j++){
                if(num != arr[i][j]){
                    temp = false;
                }
            }
        }
        
        if(temp){
            answer[num]++;
        }
        
        return temp;
    }
}