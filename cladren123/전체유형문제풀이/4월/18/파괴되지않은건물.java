package studyGroup.april.april18;

public class 파괴되지않은건물 {

    public static void main(String[] args) {

        int[][] board = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] skill = {{1,1,1,2,2,4},{1,0,0,1,1,2},{2,2,0,2,0,100}};

        System.out.println(solution(board, skill));

    }

    public static int solution(int[][] board, int[][] skill) {
        int answer = 0;

        int n = board.length;
        int m = board[0].length;

        int[][] sumBoard = new int[n+1][m+1];


        // 구간 지정
        for(int[] one : skill)
        {
            int type = one[0];
            int r1 = one[1];
            int c1 = one[2];
            int r2 = one[3];
            int c2 = one[4];
            int degree = one[5] * (type == 1 ? -1 : 1);

            sumBoard[r1][c1] += degree;
            sumBoard[r1][c2+1] -= degree;
            sumBoard[r2+1][c1] -= degree;
            sumBoard[r2+1][c2+1] += degree;
        }

        // 좌우 계산
        for(int i = 0; i < n; i++)
        {
            for(int j = 1; j < m; j++)
            {
                sumBoard[i][j] += sumBoard[i][j-1];
            }
        }

        // 상하 계산
        for(int j = 0; j < m; j++)
        {
            for(int i = 1; i < n; i++)
            {
                sumBoard[i][j] += sumBoard[i-1][j];
            }
        }

        // 건물 확인
        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                if(board[i][j] + sumBoard[i][j] >= 1)
                {
                    answer++;
                }
            }
        }


        return answer;
    }

}
