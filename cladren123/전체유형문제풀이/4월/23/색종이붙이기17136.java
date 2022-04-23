package studyGroup.april.april23;

/*

dfs로 1~5까지의 순서를 정한다.
백트래킹을 붙었다 뗏다를 한다.

도움을 얻은 반례
1 1 1 1 1 1 1 0 0 0
1 1 1 1 1 1 1 0 0 0
1 1 1 1 1 1 1 0 0 0
1 1 1 1 1 1 1 0 0 0
1 1 1 1 1 1 1 0 0 0
1 1 1 1 1 0 0 0 0 0
1 1 1 1 1 0 0 0 0 0
1 1 1 1 1 0 0 0 0 0
1 1 1 1 1 0 0 0 0 0
0 0 0 0 0 0 0 0 0 0

 */

import java.nio.Buffer;
import java.util.*;
import java.lang.*;
import java.io.*;

public class 색종이붙이기17136 {

    static int[][] board;
    static int[] paper = {0, 5, 5, 5, 5, 5};;
    static int answer;

    public static void main(String[] args) throws IOException {
        board = new int[10][10];
        answer= Integer.MAX_VALUE;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 10; i++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int j = 0; j < 10; j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }



        sequence(0, 0, 0);

        if(answer == Integer.MAX_VALUE)
        {
            answer = -1;
        }

        System.out.println(answer);

    }

    // 색종이 붙이는 순서
    public static void sequence(int y, int x, int count)
    {
        if(y >= 9 && x > 9) {
            answer = Math.min(answer, count);
            return;
        }

        if(answer <= count)
        {
            return;
        }

        if(x > 9)
        {
            sequence(y+1, 0, count);
            return;
        }

        if(board[y][x] == 1)
        {
            for(int i = 5; i >= 1; i--)
            {
                if(paper[i] > 0 && searchSquare(y, x, i))
                {
                    post(y,x,i,0);
                    paper[i] -= 1;
                    sequence(y, x+1, count+1);
                    post(y,x,i,1);
                    paper[i] += 1;
                }
            }
        }
        else
        {
            sequence(y,x+1,count);
        }
    }

    // 색종이를 붙이는 함수
    public static void post(int y, int x, int size, int number)
    {
        for(int i = y; i < y + size; i++)
        {
            for(int j = x; j < x + size; j++)
            {
                board[i][j] = number;
            }
        }
    }

    // 색종이를 붙일 수 있는지 확인하는 함수
    public static boolean searchSquare(int y, int x, int size)
    {
        for(int i = y; i < y+size; i++)
        {
            for(int j = x; j < x+size; j++)
            {
                if(i < 0 || i >= 10 || j < 0 || j >= 10)
                {
                    return false;
                }

                if(board[i][j] == 0)
                {
                    return false;
                }
            }
        }

        return true;

    }


}
