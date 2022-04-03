class Solution
{
    public int solution(int [][]board)
    {   
        int rlen = board.length;
        int clen = board[0].length;
        int max =0;
        int minVal =0;
        for(int i=0; i<rlen; i++){
            for(int j=0; j<clen; j++){
                if(i>0&&j>0){
                    minVal = Integer.min(board[i-1][j-1],board[i][j-1]);
                    minVal = Integer.min(minVal,board[i-1][j]); // 세 값 중 최소
                }                
                if(board[i][j]!= 0 && minVal!=0){
                    board[i][j] =Integer.max(minVal+1,board[i][j]); // 최소 +1 과 현재 값 비교
                }

                max = Integer.max(max,board[i][j]);
            }
        }
        
        return max*max;
    }
}