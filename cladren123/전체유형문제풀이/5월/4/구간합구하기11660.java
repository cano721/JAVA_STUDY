package studyGroup.may.may4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 구간합구하기11660 {

    static int n;
    static int m;
    static int[][] board;
    static int[][] numbers;
    static int[][] calcBoard;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];
        numbers = new int[m][4];

        for(int i = 1; i < n+1; i++)
        {
            st = new StringTokenizer(br.readLine());

            for(int j = 1; j < n+1; j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < m; i++)
        {
            st = new StringTokenizer(br.readLine());

            for(int j = 0; j < 4; j++)
            {
                numbers[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        calcBoard = new int[n+1][n+1];
        for(int i = 1; i < n+1; i++)
        {
            for(int j = 1; j < n+1; j++)
            {
                calcBoard[i][j] = board[i][j] + calcBoard[i-1][j] + calcBoard[i][j-1] - calcBoard[i-1][j-1];
            }
        }

        for(int i = 0; i < m; i++)
        {
            System.out.println(calc(numbers[i]));
        }


    }

    public static int calc(int[] number)
    {
        int result = 0;

        int sy = number[0];
        int sx = number[1];
        int ey = number[2];
        int ex = number[3];

        result = calcBoard[ey][ex] - calcBoard[ey][sx-1] - calcBoard[sy-1][ex] + calcBoard[sy-1][sx-1];

        return result;
    }



}
