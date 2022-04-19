class Solution {
    
    int[][] board;
    int[][] temp;
    
    public int solution(int[][] board, int[][] skill) {
        this.board = board;
        
        this.temp = new int[board.length+1][board[0].length+1];
        
        //1. 공격 or 회복
        for (int i = 0, length = skill.length; i < length; i++) {
            int[] arr = skill[i];
            if (arr[0] == 1) attack(arr[1], arr[2], arr[3], arr[4], arr[5]);
            if (arr[0] == 2) recovery(arr[1], arr[2], arr[3], arr[4], arr[5]);
        }
        
        //2. 누적합
        for (int j = 0, length2 = temp[0].length; j < length2; j++) {
            int n = 0;
            for (int i = 0, length = temp.length; i < length; i++) {
                temp[i][j] += n;
                n = temp[i][j];
            }
        }
        
        for (int i = 0, length = temp.length; i < length; i++) {
            int n = 0;
            for (int j = 0, length2 = temp[0].length; j < length2; j++) {
                temp[i][j] += n;
                n = temp[i][j];
            }
        }
        
        for (int i = 0, length = board.length; i < length; i++) {
            for (int j = 0, length2 = board[0].length; j < length2; j++) {
                board[i][j] += temp[i][j];
            }
        }
        
        int answer = 0;
    
        //2. 남은 건물 찾기
        for (int i = 0, length = board.length; i < length; i++) {
            for (int j = 0, length2 = board[0].length; j < length2; j++) {
                if (board[i][j] > 0) answer++;
            }
        }
        
        return answer;
    }
    
    public void attack(int r1, int c1, int r2, int c2, int degree) {
        temp[r1][c1] -= degree;
        temp[r1][c2+1] += degree;
        temp[r2+1][c1] += degree;
        temp[r2+1][c2+1] -= degree;
    }
    
    public void recovery(int r1, int c1, int r2, int c2, int degree) {
        temp[r1][c1] += degree;
        temp[r1][c2+1] -= degree;
        temp[r2+1][c1] -= degree;
        temp[r2+1][c2+1] += degree;
    }
}