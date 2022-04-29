package studyGroup.april.april29;

import java.util.*;
import java.lang.*;
import java.io.*;

/*

회전 연산의 순서 정하기
순서에 따른 회전 연산 수행
각 행을 비교해 최소값을 출력

*/

public class 배열돌리기4구분17406 {

    static int n,m,k;
    static int[][] board;
    static int[][] originBoard;
    static int[][] rotate;

    static int[][] newBoard;

    static int[] seq;
    static int[] visited;

    static int answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n+1][m+1]; // 판
        originBoard = new int[n+1][m+1]; // 원래의 판
        rotate = new int[k][3]; // 회전

        answer = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++)
            {
                originBoard[i+1][j+1] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < k; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++)
            {
                rotate[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        seq = new int[k];
        visited = new int[k];

        dfs(0);

        System.out.println(answer);

    }

    // 순서를 정하는 함수
    public static void dfs(int stage)
    {
        if(stage == k)
        {
            board = new int[n+1][m+1];
            for(int i = 0; i < n+1; i++)
            {
                for(int j = 0; j < m+1; j++)
                {
                    int temp = originBoard[i][j];
                    board[i][j] = temp;
                }
            }

            for(int i = 0; i < k; i++)
            {
                int se = seq[i];
                int r = rotate[se][0];
                int c = rotate[se][1];
                int s = rotate[se][2];

                rotation(r,c,s);
            }


            score();

            return;
        }

        for(int i = 0; i < k; i++)
        {
            if(visited[i] == 0)
            {
                visited[i] = 1;
                seq[stage] = i;
                dfs(stage+1);
                visited[i] = 0;
            }
        }


    }



    // 선택한 구역을 회전하는 함수
    public static void rotation(int r, int c, int s)
    {
        // 가장 왼쪽 윗칸
        int sy = r - s;
        int sx = c - s;

        // 가장 아래쪽 아래칸
        int ey = r + s;
        int ex = c + s;

        // newBoard 에 Board를 깊은 복사
        newBoard = new int[n+1][m+1];
        for(int i = 0; i < n+1; i++)
        {
            for(int j = 0; j < m+1; j++)
            {
                int temp = board[i][j];
                newBoard[i][j] = temp;
            }
        }

        // 회전수행
        for(int i = 0; i < s; i++)
        {
            oneRotation(sy,sx,ey,ex);
            sy++;
            sx++;
            ey--;
            ex--;
        }

        // newBoard에서 board로 깊은 복사
        for(int i = 0; i < n+1; i++)
        {
            for(int j = 0; j < m+1; j++)
            {
                int temp = newBoard[i][j];
                board[i][j] = temp;
            }

        }
    }

    // 낱개 회전
    public static void oneRotation(int sy, int sx, int ey, int ex)
    {
        // 윗줄
        for(int i = sx+1; i <= ex; i++)
        {
            int temp = board[sy][i-1];
            newBoard[sy][i] = temp;
        }

        // 아랫줄
        for(int i = sx; i <= ex-1; i++)
        {
            int temp = board[ey][i+1];
            newBoard[ey][i] = temp;
        }

        // 왼쪽줄
        for(int i = sy; i <= ey-1; i++)
        {
            int temp = board[i+1][sx];
            newBoard[i][sx] = temp;
        }

        // 오른쪽줄
        for(int i = sy+1; i <= ey; i++)
        {
            int temp = board[i-1][ex];
            newBoard[i][ex] = temp;
        }
    }



    // 점수를 계산하는 함수
    public static void score()
    {
        int result = 0;
        for(int i = 1; i < m+1; i++)
        {
            result += board[1][i];
        }

        for(int i = 1; i < n+1; i++)
        {
            int score = 0;
            for(int j = 1; j < m+1; j++)
            {
                score += board[i][j];
            }
            result = Math.min(result, score);
        }

        answer = Math.min(answer, result);

    }





}
