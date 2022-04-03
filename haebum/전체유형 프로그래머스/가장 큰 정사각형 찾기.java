/**
    1. 1000*1000에서 가장 큰 정사각형 찾기
    
    2. 완탐은 불가..(10^3*10^3*10^3)
    
    3. dp..? 
    
    4. dp로 사각형 모양(현재위치,위,좌측,대각선) 전부 존재할때 가장 적은 값 +1로 설정
    
    5. dp[세로][가로] = 길이
    
    1000*1000으로 해결! 10^6
**/

class Solution
{
    public int solution(int [][]board)
    {
        int result = 0;
        
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                
                if(i > 0 && j > 0 && board[i][j] == 1){ // 현재 좌표가 1일때
                    board[i][j] = Math.min(board[i-1][j-1],Math.min(board[i-1][j],board[i][j-1]))+1; // 정사각형 중 가장 작은 숫자+1
                }
                result = Math.max(result,board[i][j]); // 최대길이 체크
            }
        }

        return result*result; // 넓이 반환
    }
}