package studyGroup.april.april9;

import java.awt.print.Pageable;
import java.util.*;
import java.lang.*;
import java.io.*;

/*

1. 기준점, 경계의 길이 설정
기준점(x,y) 경계의 길이 d1, d2
1 <= x < x+ d1 + d2 < n
1 <= y-d1 < y < y+d2 <= n

4개의 경계점
(x,y)  (x+d1, y-d1)  (x+d2, y+d2)  (x+d1+d2, y-d1+d2)

 */


public class 개리맨더링2구분17779번 {

    static int n; // 제현시의 크기
    static int[][] city;
    static int[][] board;
    static int answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        city = new int[n + 1][n + 1];
        board = new int[n + 1][n + 1];

        answer = Integer.MAX_VALUE;

        for(int i = 1; i < n+1; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < n+1; j++)
            {
                city[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 4중 포문으로 경우의 수 찾기
        for(int x = 0; x < n; x++)
        {
            for(int y = 0; y < n; y++)
            {
                for(int d2 = 1; d2 < n; d2++)
                {
                    for(int d1 = 1; d1 < n; d1++)
                    {
                        if(x + d1 + d2 >= n) continue;
                        if(y - d1 < 0 || y + d2 >= n) continue;

                        makeBoard(x,y,d1,d2);
                        countBoard();
                    }
                }
            }
        }

//        makeBoard(3,5,2,1);
//        System.out.println();
//        for (int[] ints : board) {
//            System.out.println(Arrays.toString(ints));
//        }

        System.out.println(answer);

    }

    // 나누어진 구역의 인원을 구하는 함수
    public static void countBoard()
    {
        int[] arr = new int[5];

        for(int i = 1; i < n+1; i++)
        {
            for(int j = 1; j <n+1; j++)
            {
                if(board[i][j] == 1) arr[0] += city[i][j];
                else if(board[i][j] == 2) arr[1] += city[i][j];
                else if(board[i][j] == 3) arr[2] += city[i][j];
                else if(board[i][j] == 4) arr[3] += city[i][j];
                else if(board[i][j] == 5 || board[i][j] == 0) arr[4] += city[i][j];
            }
        }

        Arrays.sort(arr);
        int count = arr[4] - arr[0];
        answer = Math.min(answer, count);
    }

    // 경계선을 나누는 함수
    // (x,y)  (x+d1, y-d1)  (x+d2, y+d2)  (x+d1+d2, y-d1+d2)
    public static void makeBoard(int x, int y, int d1, int d2)
    {
        // 서
        int x1 = x;
        int y1 = y;

        // 북
        int x2 = x+d1;
        int y2 = y-d1;

        // 남
        int x3 = x+d2;
        int y3 = y+d2;

        // 동
        int x4 = x+d1+d2;
        int y4 = y-d1+d2;

        board = new int[n+1][n+1];

        int drawX1 = x;
        int drawY1 = y;
        int drawX2 = x3;
        int drawY2 = y3;

        for(int i = 0; i <= d1; i++)
        {
            board[drawY1][drawX1] = 5;
            drawY1--;
            drawX1++;
            board[drawY2][drawX2] = 5;
            drawY2--;
            drawX2++;
        }

        drawX1 = x2;
        drawY1 = y2;
        drawX2 = x1;
        drawY2 = y1;
        for(int i = 0; i <= d2; i++)
        {
            board[drawY1][drawX1] = 5;
            drawY1++;
            drawX1++;
            board[drawY2][drawX2] = 5;
            drawY2++;
            drawX2++;
        }

        // 1구역 표시
        for(int i = 1; i < y1; i++)
        {
            for(int j = 1; j <= x2; j++)
            {
                if(board[i][j] == 5) break;
                board[i][j] = 1;
            }
        }

        // 2구역 표시
        for(int i = 1; i <= y4; i++)
        {
            for(int j = n; j > x2; j--)
            {
                if(board[i][j] == 5) break;
                board[i][j] = 2;
            }
        }

        // 3구역 표시
        for(int i = y1; i < n+1; i++)
        {
            for(int j = 1; j < x3; j++)
            {
                if( board[i][j] == 5) break;
                board[i][j] = 3;
            }
        }

        // 4구역 표시
        for(int i = y4+1; i < n+1; i++)
        {
            for(int j = n; j >= x3; j--)
            {
                if(board[i][j] == 5) break;
                board[i][j] = 4;
            }
        }


    }





}
