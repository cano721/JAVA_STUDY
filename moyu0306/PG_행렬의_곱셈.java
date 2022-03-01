class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int row = arr1.length;
        int col = arr2[0].length;
        int tmp = arr1[0].length;
        int[][] answer = new int[row][col];
        for(int k=0; k<tmp;k++){
            for(int i=0;i<row;i++){
                int temp = arr1[i][k];
                for(int j=0;j<col;j++){
                    answer[i][j] += temp*arr2[k][j];
                }
            }  
        }

        return answer;
    }
}