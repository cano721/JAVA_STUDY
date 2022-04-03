class Solution {
    public int[] solution(int[][] arr) {
        int len = arr.length;
        int[] answer = checkQuarter(arr,0,0,len,len);
        return answer;
    }
    
    public int[] checkQuarter(int[][] arr,int r1, int c1, int r2, int c2){
        int size = r2-r1;
        int half = size/2;
        int[][] partition = new int[][]{
            {0,0,half,half},
            {0,half,half,size},
            {half,0,size,half},
            {half,half,size,size}
        };
        int[] result = new int[]{0,0};
        
        int init = arr[r1][c1];
        boolean equals = true;
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(arr[r1+i][c1+j]!= init) { equals = false; break;}
            }
        }
        
        if(equals){
            result[init] = 1;
        }else{
            for(int i=0; i<4; i++){
                int[] tmp = checkQuarter(arr,r1+partition[i][0],c1+partition[i][1],r1+partition[i][2],c1+partition[i][3]);
                result[0]+= tmp[0];
                result[1]+= tmp[1];
        }
            
        }
        return result;
    }
}