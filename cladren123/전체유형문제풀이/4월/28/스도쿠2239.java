package studyGroup.april.april28;

/*

체크해야할 것
1. 가로줄
2. 세로줄
3. 3X3



 */

import java.util.*;
import java.lang.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class 스도쿠2239 {

    static int[][] board;


    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new int[9][9];

        for(int i = 0; i < 9; i++)
        {
            String s = br.readLine();
            for(int j = 0; j < 9; j++)
            {
                board[i][j] = (int)(s.charAt(j) - '0');
            }
        }

        dfs(0, 0);


    }

    public static void dfs(int y, int x)
    {

        // x를 다채웟으면 다음칸으로
        if(x == 9)
        {
            dfs(y+1, 0);
            return;
        }

        // y를 다 채웠으면 종료
        if(y == 9)
        {
            for(int i = 0; i < 9; i++)
            {
                for(int j = 0; j < 9; j++)
                {
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }



            // 시스템 종료..
            System.exit(0);
        }

        if(board[y][x] == 0)
        {
            for(int i = 1; i <= 9; i++)
            {
                if(check(y,x,i))
                {
                    board[y][x] = i;
                    dfs(y,x+1);
                }
            }

            board[y][x] = 0;

            // 왜 return 처리를 할까?
            return;

        }

        dfs(y,x+1);


    }


    // 3개의 조건 검사
    public static boolean check(int y, int x, int number)
    {
        // 가로 검사
        for(int i = 0; i < 9; i++)
        {
            if(board[y][i] == number)
            {
                return false;
            }
        }

        // 세로 검사
        for(int i = 0; i < 9; i++)
        {
            if(board[i][x] == number)
            {
                return false;
            }
        }

        // 3X3 중에 첫 행열의 위치
        int setY = (y / 3) * 3;
        int setX = (x / 3) * 3;

        for(int i = setY; i < setY + 3; i++)
        {
            for(int j = setX; j < setX + 3; j++)
            {
                if(board[i][j] == number)
                {
                    return false;
                }
            }
        }


        return true;

    }







}
