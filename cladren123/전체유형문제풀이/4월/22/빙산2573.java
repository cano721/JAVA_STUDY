package studyGroup.april.april22;

/*
*
* 녹는 함수 구현
* 덩어리 구하는 함수 구현
*
*
* */

import java.util.*;
import java.lang.*;
import java.io.*;

public class 빙산2573 {

    static int n; // 행
    static int m; // 열
    static int[][] board;

    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,-1,0,1};
    static int[][] visited;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];

        for(int i = 0; i < n; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++)
            {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int bingsan = 1;
        int year = 0;

        while(bingsan == 1)
        {
            year += 1;
            melt();
            visited = new int[n][m];
            int count = count();

            if(count >= 2)
            {
                System.out.println(year);
                return;
            }

            bingsan = count;

        }

        System.out.println(0);
    }

    // 얼음 덩어리를 세는 함수
    public static int count()
    {
        int count = 0;


        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                if(board[i][j] != 0 && visited[i][j] == 0)
                {
                    count += 1;
                    visited[i][j] = 1;
                    bfs(i, j, count);
                }
            }
        }

        return count;
    }

    // 얼음 덩어리의 범위 탐색
    public static void bfs(int y, int x, int count)
    {
        Queue<dot> que = new LinkedList<>();
        que.add(new dot(y,x));

        while(!que.isEmpty())
        {
            dot d = que.poll();

            for(int i = 0; i < 4; i++)
            {
                int ny = d.y + dy[i];
                int nx = d.x + dx[i];

                if(0 <= ny && ny < n && 0 <= nx && nx < m)
                {
                    if(board[ny][nx] != 0 && visited[ny][nx] == 0)
                    {
                        visited[ny][nx] = count;
                        que.add(new dot(ny,nx));
                    }
                }
            }
        }

    }

    public static class dot
    {
        int y;
        int x;

        public dot(int y, int x)
        {
            this.y = y;
            this.x = x;
        }
    }

    // 녹는 함수
    public static void melt()
    {
        int[][] simul = new int[n][m];

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                if(board[i][j] != 0)
                {
                    simul[i][j] = oneMelt(i, j);
                }
            }
        }

        for(int i = 0; i < n; i++)
        {
            for(int j = 0; j < m; j++)
            {
                board[i][j] = simul[i][j];
            }
        }
    }

    public static int oneMelt(int y, int x)
    {
        int ice = board[y][x];
        for(int i = 0; i < 4; i++)
        {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(0 <= ny && ny < n && 0 <= nx && nx < m)
            {
                if(board[ny][nx] == 0)
                {
                    ice -= 1;
                    ice = Math.max(ice, 0);
                }
            }
        }

        return ice;

    }



}
