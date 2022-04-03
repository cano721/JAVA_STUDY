class Solution {
    int rSize, cSize; //행 크기, 열 크기
    int[][] board;
    int answer = 0;
    
    public int solution(int [][]board) {
        this.rSize = board.length;
        this.cSize = board[0].length;
        this.board = board;
        //solve(); 완탐했었다가 실패,, 효율성 빵쩜 나왔다!
        //dp로 해결
        for (int i = 0; i < cSize; i++) {
            if (board[0][i] == 1) answer = 1;
        }
        
        for (int i = 0; i < rSize; i++) {
            if (board[i][0] == 1) answer = 1;
        }
        
        for (int i = 1; i < rSize; i++) {
            for (int j = 1; j < cSize; j++) {
                if (board[i][j] == 0) continue;
                int min = Math.min(board[i-1][j], board[i][j-1]);
                min = Math.min(board[i-1][j-1], min);
                min++;
                board[i][j] = min;
                answer = Math.max(min, answer);
            }
        }
        return answer*answer;
    }
    
    void solve() {
        for (int r = 0; r < rSize; r++) {
            for (int c = 0; c < cSize; c++) {
                if (board[r][c] == 0) continue;
                int idx = 1;
                while (r+idx < rSize && c+idx < cSize) {
                    boolean flag = true;
                    for (int i = c; i <= c + idx; i++) {
                        if (board[r+idx][i] == 0) {
                            flag = false;
                            break;
                        }
                    }

                    for (int i = r; i <= r + idx; i++) {
                        if (board[i][c+idx] == 0) {
                            flag = false;
                            break;
                        }
                    }

                    if (flag == false) break;
                    else idx++;
                }

                answer = Math.max(answer, (int)Math.pow(idx, 2));
            }
        }
    }
}